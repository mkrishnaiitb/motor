package motor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.google.gson.Gson;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EventConsumer {

    public static void main(String[] args) {
        // Kafka consumer configuration settings KrishnaTest.motor.heat
        String topicName = "health.motor.heat";
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "StaticValidator");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create the consumer using props
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topicName));

        Gson gson = new Gson();
        Event person = gson.fromJson(getSampleJsonString(), Event.class);
        System.out.println(person);

        // Infinite loop to read from topic
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    private static String getSampleJsonString() {
        Gson gson = new Gson();
         Event sourceEvent = new Event("source1", "23 Jan - 11pm", 40);         
         String jsonString = gson.toJson(sourceEvent);
         return jsonString;
    }

    static class Event {
        public String eventType; 
        public String timestamp;
        public int temperature;

        public Event(String eventType, String timestamp, int temperature) {
            this.eventType = eventType;
            this.timestamp = timestamp;
            this.temperature = temperature;
        }

        @Override
        public String toString() {
            return "Event {timestamp='" + timestamp + "', temperature=" + temperature + '}';
        }
    }
}
