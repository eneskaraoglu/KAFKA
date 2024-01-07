package com.kafka.secondKafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class KafkaConsumerService {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "first", groupId = "my-consumer-group")
    public void listen(String message) {
    	
    	Faker faker = new Faker();

    	String name = faker.name().fullName();
    	String firstName = faker.name().firstName();
    	String lastName = faker.name().lastName();

    	String streetAddress = faker.address().streetAddress();
        
    	System.out.println("Received Message in group my-consumer-group: " + message);    
        kafkaProducerService.sendMessage("second", "secondKafka:"+name+" "+firstName+" "+lastName+" " +streetAddress+" Note:" +message);
    }

}