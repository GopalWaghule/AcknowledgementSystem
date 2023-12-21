package com.scms.as.service;


import java.util.List;

import com.scms.as.entity.AD_Document;
import com.scms.as.entity.Address;
import com.scms.as.entity.OrderHeader;
import com.scms.as.entity.OrderItems;


public interface AS_Service {
	
	public List<OrderHeader> getAllOrders();
	
	public Address saveAddress(Address address);
	
	public OrderItems saveOrderItems(OrderItems orderItems);
		
	public List<OrderHeader> getOrdersByCustomerName(String customerName);
	
	public AD_Document placeOrde(OrderHeader orderHeader);
	
	public void deleteProduct(Long productNumber);
	
	public AD_Document changeOrder(Long orderNumber,OrderHeader newOrder);
	

	
	
	
	

}
