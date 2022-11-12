/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.publisher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author yoges
 */
public class Parser {
    private JSONObject objExchange;
    private JSONArray jArr;
    private JSONObject objBTC;
    private JSONObject objETH;
    private JSONObject objDOGE;
    
    Parser(){
      // objExchange = new JSONObject(); 
      // jArr = new JSONArray();
      // objBTC = new JSONObject();
      // objETH = new JSONObject();
      // objDOGE = new JSONObject();
    }
    
   public JSONObject JSONparse(String price, String exchange, String crypto){
      objExchange = new JSONObject();
      if(exchange == "Coinbase")
        objExchange.put("Exchange","Coinbase");
      else if(exchange == "Binance")
        objExchange.put("Exchange","Binance");
      else if(exchange == "Gemini")
        objExchange.put("Exchange","Gemini");
      
      if(crypto =="BTC"){
        objExchange.put("crypto", crypto);
        objExchange.put("Price", price);
      }
      else if(crypto =="ETH"){
        objExchange.put("crypto", crypto);
         objExchange.put("Price", price);
      }
      else if(crypto =="DOGE"){
        objExchange.put("crypto", crypto);
        objExchange.put("Price", price);
      }
//      }
//      
//      if(exchange == "Binance"){
//      objExchange.put("Exchange","Binance");
//      if(crypto =="BTC"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }
//      else if(crypto =="ETH"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }
//      else if(crypto =="DOGE"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }}
//      
//      if(exchange == "Gemini"){
//      objExchange.put("Exchange","Gemini");
//      if(crypto =="BTC"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }
//      else if(crypto =="ETH"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }
//      else if(crypto =="DOGE"){
//      objExchange.put("crypto", crypto);
//      objExchange.put("Price", price);
//      }}
//      

//      objBTC.put("crypto", "BTC");
//      objBTC.put("Price", priceBTC);
//      
//      objETH.put("Price", priceETH);
//      objETH.put("crypto", "ETH");
//      
//      objDOGE.put("Price", priceDog);
//      objDOGE.put("crypto", "DOGE");
      
//      jArr.add(objBTC);      
//      jArr.add(objETH);
//      jArr.add(objDOGE);
      
//      objExchange.put("Data", jArr);

      return objExchange;
   }
    
}
