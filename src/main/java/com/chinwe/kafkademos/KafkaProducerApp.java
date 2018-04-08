package com.chinwe.kafkademos;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","BROCKER-1:9092,BROCKER-2:9093");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer myProducer = new KafkaProducer(props);

        ProducerRecord myMessage = new ProducerRecord("my_topic","Course-001","My Message 1");

        try{
            myProducer.send(myMessage);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
