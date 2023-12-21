package com.scms.as.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class AD_Document {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private Long purchaseOrderNumber;
	@CreationTimestamp
	private LocalDate documentCreatedDate;
	private String acknowledgementType;
	private Double orderValue;
	
	@OneToOne(cascade = CascadeType.ALL)
	private OrderItems orderItem;

}
