package it.bialek.jpa.dao;

import it.bialek.jpa.model.ProductEntity;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProductDao {

	Long getProductsCount();

	List<ProductEntity> getProducts(int maxResults, int firstResult);

}
