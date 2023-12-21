package com.scms.as.storemanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scms.as.entity.AvailableProducts;
import com.scms.as.entity.OrderItems;
import com.scms.as.repository.AvailableProductDao;

@Service
public class StoreManagerImpl implements StoreManager {

	@Autowired
	private AvailableProductDao apDao;

	@Override
	public List<OrderItems> addToWarehouse(List<OrderItems> items) {

		List<AvailableProducts> findAll = apDao.findAll();
		List<OrderItems> added = new ArrayList<>();
		for (OrderItems orderItems : items) {
			String productName = orderItems.getProductName();
			int productQuantity = orderItems.getProductQuantity();
			Long number = orderItems.getProductNumber();
			boolean productFound = false;
			for (AvailableProducts ap : findAll) {
				if (ap.getProductName().equals(productName) && (ap.getProductNumber() == number)) {
					ap.setAvailableQuantity((productQuantity) + (ap.getAvailableQuantity()));
					added.add(orderItems);
					apDao.save(ap);
					productFound = true;
					break;
				}
			}

			if (!productFound) {
				AvailableProducts newProduct = new AvailableProducts();
				newProduct.setProductName(orderItems.getProductName());
				newProduct.setAvailableQuantity(productQuantity);
				newProduct.setPrice(orderItems.getProductPrice());
				newProduct.setProductNumber(number);
				apDao.save(newProduct);
				added.add(orderItems);
			}
		}
		return added;
	}

}
