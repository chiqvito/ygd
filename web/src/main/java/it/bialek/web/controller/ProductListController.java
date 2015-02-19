package it.bialek.web.controller;

import it.bialek.ejb.ProductService;
import it.bialek.jpa.model.ProductEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

@Named("products")
@SessionScoped
public class ProductListController extends CommonListController<ProductEntity> {

	private static final long serialVersionUID = -3848087004725333589L;

	@EJB
	private ProductService productService;

	@Override
	public PaginationHelper<ProductEntity> getPagination() {
		if (pagination == null) {
			pagination = new PaginationHelper<ProductEntity>(10) {
				@Override
				public int getItemsCount() {
					return productService.getProductsSize().intValue();
				}

				@Override
				public DataModel<ProductEntity> createPageDataModel() {
					return new ListDataModel<ProductEntity>(productService.getProducts(getPageFirstItem(), getPageSize()));
				}
			};
		}
		return pagination;
	}

	@Override
	public DataModel<ProductEntity> getDataModel() {
		if (dataModel == null) {
			dataModel = getPagination().createPageDataModel();
		}
		return dataModel;
	}

}
