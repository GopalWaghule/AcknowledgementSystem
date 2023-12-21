package com.scms.as.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scms.as.entity.AD_Document;
import com.scms.as.entity.OrderHeader;
import com.scms.as.entity.OrderItems;
import com.scms.as.service.AS_Service;
import com.scms.as.storemanager.StoreManager;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class AcknowledgementSystemController {
	@Autowired
	private AS_Service asService;
	@Autowired
	private StoreManager manager;
	// get all placed orders
	@GetMapping("/AllOrder")
	public List<OrderHeader> getAllOrders() {
		List<OrderHeader> order = asService.getAllOrders();
		return order;
	}
	// fetch orders placed by one customer
	@GetMapping("/OrdersByCustemer/{name}")
	public List<OrderHeader> getAllOrdersByCustemer(@PathVariable String name) {
		List<OrderHeader> ordersByCustomerName = asService.getOrdersByCustomerName(name);
		return ordersByCustomerName;
	}

	// save order placed via postman
	@PostMapping("/saveOrder")
	public ResponseEntity<AD_Document> saveOrder(@RequestBody OrderHeader header) {

		AD_Document oh = asService.placeOrde(header);
		return new ResponseEntity<>(oh, HttpStatus.ACCEPTED);

	}

	@PostMapping("/AddToWarehouse")
	public ResponseEntity<List<OrderItems>> addToWarehouse(@RequestBody List<OrderItems> items) {

		List<OrderItems> list = manager.addToWarehouse(items);
		return new ResponseEntity<>(list, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteAvailableProduct/{productNumber}")
	public ResponseEntity<String> deleteAvailableProduct(@PathVariable Long productNumber) {
		try {
			asService.deleteProduct(productNumber);
			return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.GONE);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<String>("Product Not Found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
	}

	//  Till Not Working..
	@PutMapping("/changeOrder/{orderNumber}")
	public ResponseEntity<AD_Document> changeMyOrder(
			@PathVariable Long orderNumber, @RequestBody OrderHeader orderHeader) {

		try {
			AD_Document changeOrder = asService
					.changeOrder(orderNumber, orderHeader);
			return new ResponseEntity<AD_Document>(changeOrder, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<AD_Document>(HttpStatus.NOT_FOUND);
		}
	}
}
