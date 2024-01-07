package com.kafka.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService ;
	
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
		
	@GetMapping("/sendkafka/{note}")
	public String  getFirstKafka(@PathVariable String note) {
		
		kafkaProducerService.sendMessage("first", note);
		
		//wait for 
        long baslangicZamani = System.currentTimeMillis(); 
        while (System.currentTimeMillis() - baslangicZamani < 500) {
        }
        
		String returnVal = kafkaConsumerService.getReceivedMessage();
		
		return returnVal;  
	}

}
