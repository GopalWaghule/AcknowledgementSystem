package com.scms.as.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
	
	private String addressName;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;

	}
