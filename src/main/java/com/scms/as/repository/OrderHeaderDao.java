package com.scms.as.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.OrderHeader;

public interface OrderHeaderDao extends JpaRepository<OrderHeader, Serializable>{

}
