package com.scms.as.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.OrderItems;

public interface OrderItemsDao extends JpaRepository<OrderItems, Serializable>{
	
	

}
