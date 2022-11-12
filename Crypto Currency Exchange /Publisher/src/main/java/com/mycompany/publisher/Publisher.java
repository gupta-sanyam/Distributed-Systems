package com.mycompany.publisher;

import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author yogesh
 */
public class Publisher {
    
    private URL coinbase_BTC;
    private URL coinbase_ETH;
    private URL coinbase_DOGE;
    private URL binance_BTC;
    private URL binance_ETH;
    private URL binance_DOGE;
    private URL coinlore_BTC;
    private URL coinlore_ETH;
    private URL coinlore_DOGE;
    private URL publish_url1; 
    private URL publish_url2; 
    private URL publish_url3; 
    private URL advertise_url;
    private URL deadvertise_url;
    private FetchData fd;
    private Producer producer1;
    private Producer producer2;
    private Producer producer3;
    private PostData pd;
    private PostData adTopic;
    private PostData deAdTopic;
    private Parser parser;
    private JSONObject objExchangeCoin;
    private JSONObject objAdvertiseTopic;
    private JSONObject objDeadvertiseTopic; 
    final private String publisher1="Coinbase";
    final private String publisher2="Binance";
    final private String publisher3="Gemini";
    
    Publisher(){
        
     try{
         
        fd = new FetchData(); 
//        parser = new Parser();
//        objExchangeCoin = new JSONObject();

        objAdvertiseTopic   = new JSONObject();
        objDeadvertiseTopic = new JSONObject();
        
        
       //pd = new PostData();
        producer1 = new Producer();
        producer2 = new Producer();
        producer3 = new Producer();
        
        
        adTopic = new PostData();
        deAdTopic = new PostData();
        
        coinbase_BTC = new URL("https://api.coinbase.com/v2/prices/BTC-USD/buy");
        coinbase_ETH = new URL("https://api.coinbase.com/v2/prices/ETH-USD/buy");
        coinbase_DOGE = new URL("https://api.coinbase.com/v2/prices/DOGE-USD/buy");
        
        binance_BTC = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        binance_ETH = new URL("https://api.binance.com/api/v3/ticker/price?symbol=ETHUSDT");
        binance_DOGE = new URL("https://api.binance.com/api/v3/ticker/price?symbol=DOGEUSDT");
        
        coinlore_BTC = new URL(" https://api.gemini.com/v1//pubticker/btcusd");
        coinlore_ETH = new URL(" https://api.gemini.com/v1//pubticker/ethusd");
        coinlore_DOGE = new URL("https://api.gemini.com/v1//pubticker/dogeusd");
        
        publish_url1 = new URL( "http://broker3:5000/publisher/publish");
        publish_url2 = new URL( "http://broker1:5000/publisher/publish");
        publish_url3 = new URL( "http://broker2:5000/publisher/publish");
        
//        publish_url1 = new URL( "http://0.0.0.0:20000/publisher/publish");
//        publish_url2 = new URL( "http://0.0.0.0:20001/publisher/publish");
//        publish_url3 = new URL( "http://0.0.0.0:20002/publisher/publish");
        
        advertise_url = new URL( "http://sub1:5000/publisher/advertise");
        
        deadvertise_url = new URL( "http://sub1:5000/publisher/deadvertise");
        } catch(Exception e){} 

    }
    
    public void publish(){  
        
         producer2.produce("ETH", publisher3, fd.recieveData(coinlore_ETH, publisher3));
         
         producer1.produce("BTC", publisher3, fd.recieveData(coinlore_BTC, publisher3));
         
         producer3.produce("DOGE", publisher3,fd.recieveData(coinlore_DOGE, publisher3));
         
//         producer2.produce("ETH", publisher1, fd.recieveData(coinbase_ETH, publisher1));
//         
//         producer1.produce("BTC", publisher1, fd.recieveData(coinbase_BTC, publisher1));
////         
//         producer3.produce("DOGE", publisher1,fd.recieveData(coinbase_DOGE, publisher1));
//         
//                  producer2.produce("ETH", publisher1, fd.recieveData(coinbase_ETH, publisher1));
//         
//         producer1.produce("BTC", publisher1, fd.recieveData(coinbase_BTC, publisher1));
////         
//         producer3.produce("DOGE", publisher1,fd.recieveData(coinbase_DOGE, publisher1));
//    
    }
       
    
    public void advertise(String topic){  
       

      
     objAdvertiseTopic.put("topic_name",topic);
     
     adTopic.post(advertise_url, objAdvertiseTopic);
   
     
     
       
     //System.out.println(objExchangeCoin.toString());
      
    
    }
    
    public void deadvertise(String topic){  
       

      
     objDeadvertiseTopic.put("topic_name",topic);
     
     deAdTopic.post(deadvertise_url, objDeadvertiseTopic);
   
     
     
       
     //System.out.println(objExchangeCoin.toString());
      
    
    }
    
}
