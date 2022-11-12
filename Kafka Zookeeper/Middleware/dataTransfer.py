from pymongo import collection
import requests
from base_variables import *

#push data to this sub
def notify(sub, data):
    collection = mng_db["sub_info"]
    info = collection.find_one({"ID" : sub})
    ip = info["IP"]
    url = f'http://{ip}:5000/sub/updateData'
    # print("**printing", url, data)
    requests.post(url, json=data)

def publish(data):
    for element in neighbours:
        url = f'http://{element}:5000/broker/publish'
        requests.post(url, json=data)

def subscribe(data):
    for element in neighbours:
        url = f'http://{element}:5000/broker/suscribe'
        requests.post(url, json=data)

def unsubscribe(data):
    for element in neighbours:
        url = f'http://{element}:5000/broker/unsuscribe'
        requests.post(url, json=data)