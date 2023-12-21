package com.scms.as.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scms.as.entity.AD_Document;
import com.scms.as.entity.Address;
import com.scms.as.entity.AvailableProducts;
import com.scms.as.entity.OrderHeader;
import com.scms.as.entity.OrderItems;
import com.scms.as.repository.AD_DocumenDao;
import com.scms.as.repository.AddressDao;
import com.scms.as.repository.AvailableProductDao;
import com.scms.as.repository.OrderHeaderDao;
import com.scms.as.repository.OrderItemsDao;
import com.scms.as.service.AS_Service;

@Service
public class AcknowledgementSystemService implements AS_Service {

	@Autowired
	private OrderHeaderDao orderHeaderDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private OrderItemsDao orderItemsDao;
	@Autowired
	private AvailableProductDao apDao;
	@Autowired
	private AD_DocumenDao documenDao;

	@Override
	public List<OrderHeader> getAllOrders() {
		List<OrderHeader> findAll = orderHeaderDao.findAll();
		return findAll;
	}

	@Override
	public Address saveAddress(Address address) {

		addressDao.save(address);
		return address;
	}

	@Override
	public OrderItems saveOrderItems(OrderItems orderItems) {

		orderItemsDao.save(orderItems);

		return orderItems;
	}

	@Override
	public List<OrderHeader> getOrdersByCustomerName(String name) {
		List<OrderHeader> findByUserName = orderHeaderDao.findByUserName(name);
		return findByUserName;
	}

	// this method is working till ad anc rd document
	@Override
	public AD_Document placeOrde(OrderHeader orderHeader) {
		Optional<AvailableProducts> productNumber = apDao
				.findByProductNumber(orderHeader.getOrderItems().getProductNumber());
		AD_Document result = new AD_Document();
		result.setUserName(orderHeader.getUserName());
		result.setPurchaseOrderNumber(orderHeader.getPurchaseOrderNumber());
		result.setOrderItem(orderHeader.getOrderItems());

		if (productNumber.isPresent()) {
			AvailableProducts availableProducts = productNumber.get();
			System.out.println("product is available as -> " + availableProducts);
			if ((availableProducts.getAvailableQuantity()) >= (orderHeader.getOrderItems().getProductQuantity())) {

				availableProducts.setAvailableQuantity(
						availableProducts.getAvailableQuantity() - orderHeader.getOrderItems().getProductQuantity());

				orderHeaderDao.save(orderHeader);
				System.out.println("Product available to deliver");

				double price = availableProducts.getPrice();
				int quantity = orderHeader.getOrderItems().getProductQuantity();

				apDao.save(availableProducts);
				result.setAcknowledgementType("AD");
				result.setOrderValue(price * quantity);

				documenDao.save(result);
				System.out.println("AD document is created ,and shipment information is getting figured out....");

			}

		} else {
			result.setAcknowledgementType("RD");
			documenDao.save(result);
			System.out.println(
					"RD document is created ,we will contact soon with proper information about and support....");

		}

		return result;
	}

	// to check whether the product is available or not if yes then it will give
	// product

	@Override
	public void deleteProduct(Long productNumber) {
		apDao.deleteByProductNumber(productNumber);
	}

	@Override
	public AD_Document changeOrder(Long orderNumber, OrderHeader newOrder) {

		OrderHeader order = orderHeaderDao.findByPurchaseOrderNumber(orderNumber).get();

		// old product info getting first because it will be modifies in certain
		// scenarios
		Long oldProductNumber = order.getOrderItems().getProductNumber();
		int oldOrderQuantity = order.getOrderItems().getProductQuantity();
		AvailableProducts oldProduct = apDao.findByProductNumber(oldProductNumber).get();
		int availableQuantity = oldProduct.getAvailableQuantity();

		// Getting new product information for further operations
		Long newProductNumber = newOrder.getOrderItems().getProductNumber();
		int newOrderQuantity = newOrder.getOrderItems().getProductQuantity();
		AvailableProducts newProduct = apDao.findByProductNumber(newProductNumber).get();
		double newProductPrice = newProduct.getPrice();
		int availableQuantity2 = newProduct.getAvailableQuantity();

		if (newProduct != null && availableQuantity2 >= newOrderQuantity) {

			// setting old order quantity
			oldProduct.setAvailableQuantity(availableQuantity + oldOrderQuantity);

			// setting new order quantity
			newProduct.setAvailableQuantity(availableQuantity2 - newOrderQuantity);

			apDao.save(oldProduct);
			apDao.save(newProduct);
			

			OrderHeader orderHeader = orderHeaderDao.findByPurchaseOrderNumber(orderNumber).get();

			orderHeader.setAddress(newOrder.getAddress());
			orderHeader.setOrderItems(newOrder.getOrderItems());
			orderHeader.setPurchaseOrderNumber(newOrder.getPurchaseOrderNumber());
			orderHeader.setUserEmail(newOrder.getUserEmail());
			orderHeader.setUserMob(newOrder.getUserMob());
			orderHeader.setUserName(newOrder.getUserName());

			orderHeaderDao.save(orderHeader);

			AD_Document document = documenDao.findByPurchaseOrderNumber(orderNumber);

			document.setAcknowledgementType("CD");

			document.setOrderItem(newOrder.getOrderItems());
			document.setOrderValue(newProductPrice * newOrderQuantity);
			document.setPurchaseOrderNumber(newOrder.getPurchaseOrderNumber());
			document.setUserName(newOrder.getUserName());

			documenDao.save(document);

			return document;

		} else {
			return null;
		}
	}

}
