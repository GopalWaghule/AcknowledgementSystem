package com.scms.as.entity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvailableProducts {
	
	private Long id;
	private String producName;
	private String productNumber;
	private int availableQuantity;
	private double price;
}
