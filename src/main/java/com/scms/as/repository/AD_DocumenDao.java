package com.scms.as.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.AD_Document;

public interface AD_DocumenDao extends JpaRepository<AD_Document, Serializable>{
	
	AD_Document findByPurchaseOrderNumber(Long purchaseOrderNumber);

}
