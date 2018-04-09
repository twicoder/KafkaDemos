package com.chinwe.kafkademos;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092,localhost:9093");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaConsumer myConsumer = new KafkaConsumer(props);
        myConsumer.subscribe(Arrays.asList("mytopic"));
        // alternatively, use regular expression:
//        myConsumer.subscribe("my*");

        // Better for incremental topic subscription management:
//        ArrayList<String> topics = new ArrayList<String>();
//        topics.add("myTopic");
//        topics.add("myOtherTopic");
//        myConsumer.subscribe(topics);

//        myConsumer.unsubscribe();

    }

}
