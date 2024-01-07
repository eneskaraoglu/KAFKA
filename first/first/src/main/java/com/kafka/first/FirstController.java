package com.kafka.first;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FirstController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService ;
	
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@GetMapping("/firstrt/{to}")
	public String  getFirst(@PathVariable String to) {
		
		System.out.println("First "+to);
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("to", to);
		ResponseEntity<String> responseEntity = new RestTemplate().
				getForEntity("http://localhost:8200/second/{to}", String.class, uriVariables);
		
		String returnVal = responseEntity.getBody();
		
		return returnVal;  
	}
	
	@GetMapping("/firstkafka/{to}")
	public String  getFirstKafka(@PathVariable String to) {
		
		System.out.println("First "+to);
		
		kafkaProducerService.sendMessage("first", to);
		
        long baslangicZamani = System.currentTimeMillis(); 
        while (System.currentTimeMillis() - baslangicZamani < 500) {
        }
        
		String returnVal = kafkaConsumerService.getReceivedMessage();
		
		System.out.println("FirstController returnVal:"+returnVal);
		
		return returnVal;  
	}

}
