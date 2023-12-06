package com.scms.as.service;


import java.util.List;

import com.scms.as.entity.Address;
import com.scms.as.entity.OrderHeader;
import com.scms.as.entity.OrderItems;


public interface AS_Service {
	
	public List<OrderHeader> getOrder(OrderHeader orderHeader);
	
	public Address getAddress(Address address);
	
	public OrderItems getOrderItems(OrderItems orderItems);
	
	public OrderHeader addOrder(OrderHeader orderHeader);
	
	

}
