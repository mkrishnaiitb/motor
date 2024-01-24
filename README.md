# Motor Health events monitoring system. 

Data
https://www.kaggle.com/datasets/stephanmatzka/condition-monitoring-dataset-ai4i-2021?resource=download


## One Time Setup Kafka Pub/Sub environment
Visit 
https://kafka.apache.org/downloads

Download 
download kafka_2.13-3.6.1.tgz  to ~/code/softwares/.
cd ~/code/softwares/.
tar -xzf kafka_2.13-3.6.1.tgz 

cd ~/code/kafka-code


## Start Event Publishers & Subscribers
cd ~/code/kafka-code

### On Terminal1: (Zookeeper)
bin/zookeeper-server-start.sh config/zookeeper.properties

### On Terminal2: (Kafka Server)
bin/kafka-server-start.sh config/server.properties


## Start Producer & Consumers
### On Terminal3:
bin/kafka-console-producer.sh --topic health.motor.heat --bootstrap-server localhost:9092

### On Terminal4 (optional)
bin/kafka-console-consumer.sh --topic health.motor.heat --from-beginning --bootstrap-server localhost:9092

### On Terminal5 (Java Consumer)
mvn compile; mvn package; java -jar target/gs-maven-0.1.0.jar


## How to compile
mvn compile
mvn package

## How to run
java -jar target/gs-maven-0.1.0.jar
