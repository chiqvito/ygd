package it.bialek.jpa.dao;

import it.bialek.jpa.model.OrderEntity;

import java.util.List;

import javax.ejb.Local;

@Local
public interface OrderDao {

	boolean save(OrderEntity order);

	Long getOrdersCount();

	List<OrderEntity> getOrders(int maxResults, int firstResult);

}
