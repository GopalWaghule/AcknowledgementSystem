package com.scms.as.service.impl;

import com.scms.as.entity.OrderHeader;
import com.scms.as.repository.OrderHeaderDao;
import com.scms.as.service.AS_Service;



public class AcknowledgementSystemService implements AS_Service{
	
	private OrderHeaderDao orderHeaderDao;

	@Override
	public OrderHeader getOrder(OrderHeader orderHeader) {
		
		orderHeaderDao.save(orderHeader);
		
		return null;
	}

}
