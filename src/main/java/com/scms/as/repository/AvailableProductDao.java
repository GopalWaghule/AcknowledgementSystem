package com.scms.as.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scms.as.entity.AvailableProducts;

public interface AvailableProductDao extends JpaRepository<AvailableProducts, Serializable> {

	Optional<AvailableProducts> findByProductNumber(Long lon);

	void deleteByProductNumber(Long productNumber);

}
