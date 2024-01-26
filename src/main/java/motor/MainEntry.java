package motor;

import motor.consumer.EventConsumer;
import motor.notifier.EmailServer;

public class MainEntry {

        public static void main(String[] args) {
            // EventConsumer.eventConsumerInitialise();
            EmailServer.sendTestEmail();
        }
    
}
