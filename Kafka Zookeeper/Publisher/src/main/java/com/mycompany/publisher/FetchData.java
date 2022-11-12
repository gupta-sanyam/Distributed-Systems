/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.publisher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author yoges
 */
public class FetchData {
    private HttpURLConnection conn;
    
    private void makeConnection(URL url){     
     try{
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
     }
     catch(Exception e){
      System.out.println("Connection Exception Occured: "+ e);
     }
    }
    
    public String recieveData(URL url, String exchange){ //we can return the Json object using this method
        
      makeConnection(url);
      StringBuffer response = null; 
      BufferedReader in;
      String price="";
     try{     
     JSONParser jsonParser = new JSONParser();
     JSONObject jsonObject = (JSONObject)jsonParser.parse(
      new InputStreamReader(conn.getInputStream(), "UTF-8"));
     if(exchange == "Coinbase"){
     jsonObject = (JSONObject)jsonObject.get("data");
     price = (String) jsonObject.get("amount");}
     
     if(exchange == "Binance"){
     price = (String)jsonObject.get("price");
     }
     
     if(exchange == "Gemini"){
     
     price = (String) jsonObject.get("ask");}
     
        // in.close();   
     }
     catch(Exception e){
      System.out.println("Connection Exception Occured: "+ e);
     }
     
//     System.out.println(price);

     return price;
    }
    
}
