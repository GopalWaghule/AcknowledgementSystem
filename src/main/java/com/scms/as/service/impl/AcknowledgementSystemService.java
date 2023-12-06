package com.scms.as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scms.as.entity.Address;
import com.scms.as.entity.OrderHeader;
import com.scms.as.entity.OrderItems;
import com.scms.as.repository.AddressDao;
import com.scms.as.repository.OrderHeaderDao;
import com.scms.as.repository.OrderItemsDao;
import com.scms.as.service.AS_Service;


@Service
public class AcknowledgementSystemService implements AS_Service{
	
	@Autowired
	private OrderHeaderDao orderHeaderDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private OrderItemsDao orderItemsDao;
	

	@Override
	public List<OrderHeader> getOrder(OrderHeader orderHeader) {
		
		List<OrderHeader> findAll = orderHeaderDao.findAll();
				
		return findAll;
	}

	@Override
	public Address getAddress(Address address) {
		
		addressDao.save(address);
		return address;
	}

	@Override
	public OrderItems getOrderItems(OrderItems orderItems) {

		orderItemsDao.save(orderItems);
		
		return orderItems;
	}

	@Override
	public OrderHeader addOrder(OrderHeader orderHeader) {
		
		orderHeaderDao.save(orderHeader);
		return null;
	}

}
