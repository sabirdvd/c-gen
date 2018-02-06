package com.yunyi.order.dao;

public interface OrderMapper {

		int insertOrder(Order obj);

		List<Order> getOrder(Map<String, Object> params);

		int countOrder(Map<String, Object> params);

		List<Order> listOrder(Map<String, Object> params);

		int updateOrderByOrderId(Map<String, Object> params);

}