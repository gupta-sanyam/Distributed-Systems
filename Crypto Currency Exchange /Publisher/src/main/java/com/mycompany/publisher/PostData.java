/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.publisher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

/**
 *
 * @author yoges
 */
public class PostData {
    
    private HttpURLConnection con;
    
    private void makeConnection(URL url){     
     try{
      con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json; utf-8");
      con.setRequestProperty("Accept", "application/json");
      con.setDoOutput(true);
      con.connect();
     }
     catch(Exception e){
      System.out.println("Connection Exception Occured: "+ e);
     }
    }
    
    
    void post(URL url, JSONObject obj){
        makeConnection(url);
        try{
            
            OutputStream os = con.getOutputStream();
            byte[] input = obj.toString().getBytes("utf-8");
            os.write(input, 0, input.length);			
       
            BufferedReader br = new BufferedReader( new InputStreamReader(con.getInputStream(), "utf-8"));
    
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
                  }
            
             //System.out.println(response.toString());
            }
    catch(Exception e)
    {
       System.out.println("error"+e);
    }
        
   

    
}}
