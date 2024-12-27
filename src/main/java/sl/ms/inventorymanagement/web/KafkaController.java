package sl.ms.inventorymanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sl.ms.inventorymanagement.dto.Student;
import sl.ms.inventorymanagement.service.KafkaProducerService;

@RestController
@RequestMapping(value = {"kafka"})
public class KafkaController {

	@Autowired
	private KafkaProducerService service;
	
	@PostMapping("/publish")
	public void publishMessage(@RequestBody Student student) {
		service.sendMessage();
	}
	
}
