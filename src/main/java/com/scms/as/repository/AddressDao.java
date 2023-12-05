package com.scms.as.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.as.entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Serializable>{

}
