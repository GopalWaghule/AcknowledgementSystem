package com.scms.as.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.OrderHeader;

public interface OrderHeaderDao extends JpaRepository<OrderHeader, Serializable>{
	
	List<OrderHeader> findByUserName(String userName);
	
	Optional<OrderHeader> findByPurchaseOrderNumber(Long purchaseOrderNumber);
	

}
