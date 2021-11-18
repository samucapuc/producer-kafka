package br.com.samuel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDispatcher implements Closeable {
	
	private static Logger logger = LoggerFactory.getLogger(KafkaDispatcher.class);

    private final KafkaProducer<String, String> producer;

    KafkaDispatcher() {
        this.producer = new KafkaProducer<>(properties());
    }

    private static Properties properties() {
    	Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.81.202:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "GRUPO1");
        return properties;
    }

    void send(String topic, String key, String value) throws ExecutionException, InterruptedException {
    	ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            logger.info("Sucesso enviando {} :::partition {} / offset {} / timestamp {} key {} value {}", data.topic(), String.valueOf(data.partition()), String.valueOf(data.offset()),String.valueOf(data.timestamp()), key, value);
        };
        producer.send(record, callback).get();
    }

    @Override
    public void close() {
        producer.close();
    }
}
