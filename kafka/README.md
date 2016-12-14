Example

http://www.aboutyun.com/thread-12882-1-1.html
Kafka+Storm+HDFS整合实践
http://shiyanjun.cn/archives/934.html


Real practice

cd /usr/local/Cellar/



./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
./kafka-topics.sh --list --zookeeper localhost:2181
./kafka-console-producer.sh --broker-list localhost:9092 --topic test
         input This is a messageThis is another message
./kafka-console-consumer.sh ----bootstrap-server localhost:2181 --topic test --from-beginning


Kafka cluster
server1.properties
broker.id=1
port=9093
log.dirs=/tmp/kafka-logs-1

server1.properties
broker.id=2
port=9094
log.dirs=/tmp/kafka-logs-2


./kafka-server-start.sh ../config/server1.properties

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic my-replicated-topic
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic my-replicated-topic

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 5 --topic  ahan-replicated
Amerys-MacBook-Pro:kafka_2.11-0.10.1.0 ahan$ bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic ahan-replicated
Topic:ahan-replicated	PartitionCount:5	ReplicationFactor:3	Configs:
	Topic: ahan-replicated	Partition: 0	Leader: 1	Replicas: 1,2,0	Isr: 1,2,0
	Topic: ahan-replicated	Partition: 1	Leader: 2	Replicas: 2,0,1	Isr: 2,0,1
	Topic: ahan-replicated	Partition: 2	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
	Topic: ahan-replicated	Partition: 3	Leader: 1	Replicas: 1,0,2	Isr: 1,0,2
	Topic: ahan-replicated	Partition: 4	Leader: 2	Replicas: 2,1,0	Isr: 2,1,0
