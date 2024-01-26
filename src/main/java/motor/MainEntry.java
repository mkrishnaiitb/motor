package motor;

import motor.consumer.EventConsumer;
import motor.notifier.EmailServer;
import motor.notifier.SMSUtil;

public class MainEntry {

        public static void main(String[] args) {
            // EventConsumer.eventConsumerInitialise();
            SMSUtil.sendSMS();
        }
    
}
