from flask import Blueprint, jsonify, abort, make_response, request, Flask
import os
from tasks import *

# bp = Blueprint("operations", __name__)
app = Flask(__name__)

def isValid(request, check):
    # print(request)
    if request:
        for element in check:
            if element not in request:
                print('invalid input')
                return False
    else:
        print('invalid input')
        return False
    
    return True

@app.route('/publisher/publish', methods=['POST'])
def publish():
    if isValid(request.json, ["Exchange", "crypto", "Price"]):
        loadDataNotifySubs(request.json, "publisher")
        return("success")
    else:
        return 400

@app.route('/broker/publish', methods=['POST'])
def publishBroker():
    if isValid(request.json, ["Exchange", "crypto", "Price"]):
        loadDataNotifySubs(request.json, "broker")
        return("success")
    else:
        return 400

@app.route('/publisher/advertise', methods=['POST'])
def advertise():
    if isValid(request.json, ["topic_name"]):
        addAdvertiseInfo(request.json)
        return("success")
    else:
        return 400

@app.route('/publisher/deadvertise', methods=['POST'])
def deadvertise():
    if isValid(request.json, ["topic_name"]):
        removeAdvertiseInfo(request.json)
        return("success")
    else:
        return 400

@app.route('/middleware/show-advertised-topics', methods=['GET'])
def showAdvertisments():
    return getAdvertismentInfo()

@app.route('/suscriber/suscribe', methods=['POST'])
def suscribe():
    isValid(request.json, ['ID', 'IP', 'Topic'])
    updateSubInfo(request.json, True)
    return("success")

@app.route('/broker/suscribe', methods=['POST'])
def suscribeBroker():
    isValid(request.json, ['ID', 'IP', 'Topic'])
    updateSubInfo(request.json, False)
    return("success")

@app.route('/suscriber/unsuscribe', methods=['POST'])
def unsuscribe():
    isValid(request.json, ['ID', 'IP', 'Topic'])
    removeSubInfo(request.json, True)
    return("success")

@app.route('/broker/unsuscribe', methods=['POST'])
def unsuscribeBroker():
    isValid(request.json, ['ID', 'IP', 'Topic'])
    removeSubInfo(request.json, False)
    return("success")


# app = factory.create_app(celery=celery_app.celery)
# app.debug = True
# app.env ='Dev'
# app.run(host='0.0.0.0', port='20002')
# app.run()
