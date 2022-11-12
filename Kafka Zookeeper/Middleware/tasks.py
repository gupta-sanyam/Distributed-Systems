# from celery_app import celery
from base_variables import *
import traceback
import time
import MongoOperation
import dataTransfer
import requests

pub_topic_collection = 'topics'

# @celery.task
def publisherAdvertise(data):
    MongoOperation.insert(pub_topic_collection, data)

#put data recieved and call the procedure to update suscribers
# @celery.task
def loadDataNotifySubs(data, entity):
    print("loadDataNotifySubs", data)
    topicPublished = data["crypto"]
    if topicPublished in topics:
        exchange = data['Exchange']
        raw = mng_db['raw_data']
        collection_name = topicPublished
        curr_price = data["Price"]
        collection = mng_db[collection_name]

        collection.delete_one({"Exchange" : exchange})
        insert_data = {"Exchange" : exchange, 'Price': curr_price}
        collection.insert_one(insert_data)
        raw.insert_one(data)
        pushToSuscribers(data)
        print("inserted")
    else:
        if entity == 'publisher':
            dataTransfer.publish(data)
            print("forwaded")

    # topicsPublished = [] #{["BTC", "Coinbase"]}

    # for element in values:
    #     collection_name = element["crypto"]
    #     curr_price = element["Price"]
    #     collection = mng_db[collection_name]
        
    #     collection.delete_one({"Exchange" : exchange})
    #     insert_data = {"Exchange" : exchange, 'Price': curr_price}
    #     collection.insert_one(insert_data)
    #     raw.insert_one(insert_data)

    #     topicsPublished.append([collection_name, exchange])
    # # print(topicsPublished)
    # pushToSuscribers(topicsPublished)
    print("inserted")

"""
afer data is recieved, push the data to subs who have suscribed to topics
"""
# @celery.task
def pushToSuscribers(originalData):
    collecion_name = "push_info"
    # print("--pushToSuscribers", element)
    currency = originalData["crypto"]
    exchange = originalData['Exchange']

    data = {"Exchange" : exchange}
    collection = mng_db[currency]
    currData = collection.find_one(data)
    # print(currData)
    data["Price"] = currData["Price"]
    data["Currency"] = currency
    # print("loop", data)
    # max_count = mng_db[element].find("currentCount")["currentCount"]
    for sub in MongoOperation.subsSuscribedToTopic(currency):
        # print('nested', sub, data)
        dataTransfer.notify(sub, data)

# @celery.task
def updateSubInfo(data, forward=False):
    if data["Topic"] in topics:
        collection_name = "sub_info"
        MongoOperation.insert(collection_name, data)
        print("inserted")
    else:
        if forward:
            dataTransfer.subscribe(data)

# @celery.task
def removeSubInfo(data, forward=False):
    if data["Topic"] in topics:
        collection_name = "sub_info"
        db_cm = mng_db[collection_name]
        db_cm.delete_many(data)
    else:
        if forward:
            dataTransfer.unsubscribe(data)

# @celery.task
def addAdvertiseInfo(data):
    collection_name='advertised_topics'
    MongoOperation.insert(collection_name, data)

# @celery.task
def removeAdvertiseInfo(data):
    collection_name='advertised_topics'
    db_cm = mng_db[collection_name]
    db_cm.delete_many(data)

# @celery.task
def getAdvertismentInfo():
    collection_name='advertised_topics'
    db_cm = mng_db[collection_name]
    data = db_cm.distinct('topic_name')
    info = ", ".join(data)
    return info