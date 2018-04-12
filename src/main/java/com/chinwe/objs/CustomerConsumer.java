package com.chinwe.objs;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Properties;

public class CustomerConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","com.chinwe.objs.CustomerDeserializer");

        KafkaConsumer myConsumer = new KafkaConsumer(props);

        ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
        TopicPartition myTopicPart0 = new TopicPartition("mytopic2",0);
        partitions.add(myTopicPart0);
        myConsumer.assign(partitions);

        try{
            while(true){
                ConsumerRecords<String, Customer> records = myConsumer.poll(10);
                for(ConsumerRecord<String, Customer> record : records){
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
