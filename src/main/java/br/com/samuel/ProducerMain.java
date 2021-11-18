package br.com.samuel;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ProducerMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (KafkaDispatcher dispatcher = new KafkaDispatcher()) {
            for (int i = 0; i < 10; i++) {
                dispatcher.send("TOPICO_SAMUEL", String.valueOf(i), UUID.randomUUID().toString());
            }
        }
    }

}
