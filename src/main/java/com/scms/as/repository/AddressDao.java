package com.scms.as.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

}
