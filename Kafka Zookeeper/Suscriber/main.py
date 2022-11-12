from flask import Flask, request
import pandas as pd
import os
from flask_jsonpify import jsonpify


app = Flask(__name__)

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/sub/updateData", methods=['POST'])
def updateData():
    file_path = './data.csv'
    data = request.get_json()
    formatted = {element : [data[element]] for element in data}
    df = pd.DataFrame(formatted)
    if os.path.exists(file_path):
        base = pd.read_csv("./data.csv")
        final = base.append(df)
    else:
        final = df
    final.to_csv("./data.csv", index=False)
    return "inserted"

@app.route("/sub/displayData", methods=['GET'])
def displayData():
    df = pd.read_csv("./data.csv")
    df_list = df.values.tolist()
    JSONP_data = jsonpify(df_list)
    return JSONP_data