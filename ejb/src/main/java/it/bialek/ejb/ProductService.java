package it.bialek.ejb;

import it.bialek.jpa.model.OrderEntity;
import it.bialek.jpa.model.ProductEntity;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProductService {

	Long getProductsSize();

	List<ProductEntity> getProducts(int pageFirstItem, int pageSize);

	Long getOrdersSize();

	List<OrderEntity> getOrders(int pageFirstItem, int pageSize);

	boolean order(OrderEntity order);

}
