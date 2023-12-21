package com.scms.as.storemanager;

import java.util.List;


import com.scms.as.entity.OrderItems;


public interface StoreManager {
	
	List<OrderItems> addToWarehouse(List<OrderItems> items);

}
