package com.chinwe.kafkademos;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092,localhost:9093");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer myConsumer = new KafkaConsumer(props);

        ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
        TopicPartition myTopicPart0 = new TopicPartition("mytopic",0);
        partitions.add(myTopicPart0);
        myConsumer.assign(partitions);

//        myConsumer.subscribe(Arrays.asList("mytopic"));
        // alternatively, use regular expression:
//        myConsumer.subscribe("my*");

        // Better for incremental topic subscription management:
//        ArrayList<String> topics = new ArrayList<String>();
//        topics.add("myTopic");
//        topics.add("myOtherTopic");
//        myConsumer.subscribe(topics);

//        myConsumer.unsubscribe();

        try{
            while(true){
                ConsumerRecords<String, String> records = myConsumer.poll(10);
                for(ConsumerRecord<String, String> record : records){
                    // Process each record:
                    System.out.println(String.format("Topic:%s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            myConsumer.close();
        }


    }

}
