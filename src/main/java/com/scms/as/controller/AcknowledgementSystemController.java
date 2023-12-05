package com.scms.as.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scms.as.entity.OrderHeader;
import com.scms.as.service.AS_Service;


@RestController
public class AcknowledgementSystemController {
	
	@Autowired
	private AS_Service asService;
	
	
	@GetMapping("/getOrder")
	public String getOrder(@RequestBody OrderHeader header , Model model) {
		
		model.addAttribute("header", header);
		
		return "saveOrder";
	}
	
	@PostMapping("/getOrder")
	public ResponseEntity<OrderHeader> saveOrder(@RequestBody OrderHeader header){
		
	
		OrderHeader oh= asService.getOrder(header);
		
		return new ResponseEntity<>(oh, HttpStatus.ACCEPTED);
		
	}

}
