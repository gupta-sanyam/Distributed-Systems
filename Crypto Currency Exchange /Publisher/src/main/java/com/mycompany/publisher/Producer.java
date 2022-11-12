/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.publisher;


import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author yoges
 */
public class Producer {
    
    private Properties properties;
    private ProducerRecord producerRecord;
    private KafkaProducer kafkaProducer ;
    
    public Producer(){
        //properties to connect to kafka server
        properties = new Properties();
        //1st property---bootstrap : location of kafka server
        properties.put("bootstrap.servers","kafka1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }
    public void produce(String topic, String key, String value){
        
       producerRecord = new ProducerRecord(topic,  key , value);
       
       kafkaProducer = new KafkaProducer(properties);
       
       kafkaProducer.send(producerRecord);
       
       kafkaProducer.close();
    }
    
    
    
    
            //send data to kafkabroker using key-value pairs
            //to send data we need searlization and desearilizatiion
    
}
