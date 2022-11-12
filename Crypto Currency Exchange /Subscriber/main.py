from flask import Flask, request
import pandas as pd
import os
from flask_jsonpify import jsonpify
from kafka import KafkaConsumer
import sys
import time

app = Flask(__name__)
consumer = KafkaConsumer(bootstrap_servers=['kafka1:9092', 'kafka2:9092', 'kafka3:9092'], consumer_timeout_ms=1000, auto_offset_reset='earliest')
advInfo = {}
returnData = []

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route("/subscriber/subscribe", methods=['POST'])
def subscribe():
    data = request.json
    topic = data['Topic']
    prev = consumer.subscription()
    if prev == None:
        topicList = []
    else:
        topicList = list(consumer.subscription())

    topicList.append(topic)
    consumer.subscribe(topicList)
    return "subscribed !"

@app.route("/subscriber/unsubscribe", methods=['POST'])
def unsubscribe():
    data = request.json
    unSubFromTopic = data['Topic']
    currSub = consumer.subscription()
    newSubInfo = [element for element in currSub if element != unSubFromTopic]
    # consumer.unsubscribe()
    time.sleep(2)
    consumer.subscribe(newSubInfo)
    print(consumer.subscription(), file=sys.stderr)

    return 'unsubscribed'


@app.route('/publisher/advertise', methods=['POST'])
def advertise():
    data = request.json
    topicName = data['topic_name']
    advInfo[topicName] = True
    return("success")

@app.route('/publisher/deadvertise', methods=['POST'])
def deadvertise():
    data = request.json
    topicName = data['topic_name']
    advInfo[topicName] = False
    return("success")


@app.route('/show-advertised-topics', methods=['GET'])
def showInfo():
    myll = [ele for ele in advInfo if advInfo[ele] == True]
    return jsonpify(myll)

def fetchLatestData():
    data = {}
    for element in consumer:
        exchange = element.key.decode('ascii')
        if exchange not in data:
            data[exchange] = {}

        print(element, file=sys.stderr)

        topic = element.topic
        val = element.value.decode('ascii')
        if topic in data[exchange]:
            data[exchange][topic].append(val)
        else:
            data[exchange][topic] = [val]
    return data

@app.route("/sub/displayData", methods=['GET'])
def displayData():
    data = fetchLatestData()
    # data = {}
    # for element in consumer:
    #     exchange = element.key.decode('ascii')
    #     if exchange not in data:
    #         data[exchange] = {}

    #     print(element, file=sys.stderr)

    #     topic = element.topic
    #     val = element.value.decode('ascii')
    #     if topic in data[exchange]:
    #         data[exchange][topic].append(val)
    #     else:
    #         data[exchange][topic] = [val]
    # return data
    # for excg in data:
    #     if excg in displayData:

    #         for crypto in data[excg]:
    #             display[excg][crypto].append()
    #     else:
    #         displayData[excg] = data[excg]
    returnData.append(data)

    return jsonpify(returnData)