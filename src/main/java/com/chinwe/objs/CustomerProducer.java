package com.chinwe.objs;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CustomerProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","com.chinwe.objs.CustomerSerializer");

        KafkaProducer<String,Customer> myProducer = new KafkaProducer<String,Customer>(props);


        try{
            for(int i=0;i<1;i++){
                myProducer.send(
                        new ProducerRecord<String,Customer>("mytopic2",Integer.toString(i),new Customer(i, "Name:" + i))
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            myProducer.close();
        }
    }
}
