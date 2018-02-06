package com.test.order.dao;

public interface OrderDeliverMapper {

	int insertOrderDeliver(OrderDeliver obj);

	List<OrderDeliver> getOrderDeliver(Map<String, Object> params);

	int countOrderDeliver(Map<String, Object> params);

	List<OrderDeliver> listOrderDeliver(Map<String, Object> params);

	int updateOrderDeliverByOrderId(Map<String, Object> params);

}