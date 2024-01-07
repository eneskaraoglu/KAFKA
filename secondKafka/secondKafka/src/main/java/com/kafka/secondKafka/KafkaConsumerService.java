package com.kafka.secondKafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "first", groupId = "my-consumer-group")
    public void listen(String message) {
        System.out.println("Received Message in group my-consumer-group: " + message);
        kafkaProducerService.sendMessage("second", "secondKafka:"+message);
 
    }

}