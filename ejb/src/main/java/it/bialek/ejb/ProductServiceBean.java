package it.bialek.ejb;

import it.bialek.jpa.dao.OrderDao;
import it.bialek.jpa.dao.ProductDao;
import it.bialek.jpa.model.OrderEntity;
import it.bialek.jpa.model.ProductEntity;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductServiceBean implements ProductService {

	@EJB
	private ProductDao productDao;

	@EJB
	private OrderDao orderDao;

	@Override
	public Long getProductsSize() {
		return productDao.getProductsCount();
	}

	@Override
	public List<ProductEntity> getProducts(int pageFirstItem, int pageSize) {
		return productDao.getProducts(pageSize, pageFirstItem);
	}

	@Override
	public boolean order(OrderEntity order) {
		return orderDao.save(order);
	}

	@Override
	public List<OrderEntity> getOrders(int pageFirstItem, int pageSize) {
		return orderDao.getOrders(pageSize, pageFirstItem);
	}

	@Override
	public Long getOrdersSize() {
		return orderDao.getOrdersCount();
	}

}
