package com.scms.as.service;

import org.springframework.stereotype.Service;

import com.scms.as.entity.OrderHeader;

@Service
public interface AS_Service {
	
	public OrderHeader getOrder(OrderHeader orderHeader);

}
