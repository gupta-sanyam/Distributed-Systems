## This script is used to insert the data into the MongoDb

# import pymongo
import json
# import configparser
# import logging
from base_variables import mng_db

def insert(collection_name, data):

    db_cm = mng_db[collection_name]
    db_cm.insert(data)
    #print('Data inserted')
    return True

def getDataForTopic(topic):
    collection = mng_db[topic]
    return collection.find("")

def subsSuscribedToTopic(topicName):
    collection = mng_db['sub_info']
    data = collection.find({"Topic" : topicName})
    return [element["ID"] for element in data]
