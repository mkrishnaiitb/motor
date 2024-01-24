# Motor Health events monitoring system. 

## Data Analyzer

Download [Important Data Set](https://www.kaggle.com/datasets/stephanmatzka/condition-monitoring-dataset-ai4i-2021?resource=download) to ~/code/

Download [Weka](https://prdownloads.sourceforge.net/weka/weka-3-9-6-azul-zulu-linux.zip) 
Extract it to ~/code/softwares

>cd ~/code/softwares

>./weka.sh



## One Time Setup Kafka Pub/Sub environment

Download kafka_2.13-3.6.1.tgz from  [Kafka Archieve](https://kafka.apache.org/downloads)

Save the tgz file at ~/code/softwares/.
> cd ~/code/softwares/.

> tar -xzf kafka_2.13-3.6.1.tgz 

> cd ~/code/motor


## Start Event Publishers & Subscribers
> cd ~/code/motor

### On Terminal1: (Zookeeper)
> bin/zookeeper-server-start.sh config/zookeeper.properties

### On Terminal2: (Kafka Server)
>bin/kafka-server-start.sh config/server.properties


## Start Producer & Consumers
### On Terminal3:
>bin/kafka-console-producer.sh --topic health.motor.heat --bootstrap-server localhost:9092

### On Terminal4 (optional)
>bin/kafka-console-consumer.sh --topic health.motor.heat --from-beginning --bootstrap-server localhost:9092

### On Terminal5 (Java Consumer)
> mvn compile; mvn package; java -jar target/gs-maven-0.1.0.jar

