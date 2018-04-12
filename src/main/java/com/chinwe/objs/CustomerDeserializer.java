package com.chinwe.objs;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer> {

    public void configure(Map<String, ?> map, boolean b) {

    }

    public Customer deserialize(String s, byte[] data) {
        int customerId;
        String customerName;
        int nameSize;

        try {
            if(data == null) {
                return null;
            }
            if(data.length < 8){
                throw new SerializationException("Size of data received" +
                        " by IntegerDeserializer is shorter than expected");
            }
            ByteBuffer buffer = ByteBuffer.wrap(data);

            customerId = buffer.getInt();
            nameSize = buffer.getInt();
            byte[] nameBytes = new byte[nameSize];
            buffer.get(nameBytes);
            customerName = new String(nameBytes, "UTF-8");
            return new Customer(customerId, customerName);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing " +
                    "Customer to byte[] " + e);
        }
    }

    public void close() {

    }
}
