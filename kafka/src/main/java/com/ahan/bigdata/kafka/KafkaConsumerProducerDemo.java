package com.ahan.bigdata.kafka;

/**
 * Created by ahan on 14/12/2016.
 */
public class KafkaConsumerProducerDemo {

	public static void main(String[] args)
	{
		KafkaProducer producerThread = new KafkaProducer(KafkaProperties.topic);
		producerThread.start();
		KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
		consumerThread.start();
	}
}
