package com.chinwe.objs;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer> {

    public void configure(Map<String, ?> map, boolean b) {

    }

    public Customer deserialize(String s, byte[] bytes) {
        for(byte b : bytes){
            System.out.print(b);
        }
        System.out.println();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int customerId = buffer.getInt();
        System.out.println(customerId);
        int stringSize = buffer.getInt();
        System.out.println(stringSize);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<stringSize;i++){
//            System.out.print(buffer.getChar());
//            sb.append(buffer.getChar());
        }
        String customerName = sb.toString();
        System.out.println(customerName);
        return new Customer(customerId, "123");
    }

    public void close() {

    }
}
