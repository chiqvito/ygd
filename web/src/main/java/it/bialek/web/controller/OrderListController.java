package it.bialek.web.controller;

import it.bialek.ejb.ProductService;
import it.bialek.jpa.model.OrderEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

@Named("orders")
@SessionScoped
public class OrderListController extends CommonListController<OrderEntity> {

	private static final long serialVersionUID = -3055475775398774557L;

	@EJB
	private ProductService productService;

	@Override
	public PaginationHelper<OrderEntity> getPagination() {
		if (pagination == null) {
			pagination = new PaginationHelper<OrderEntity>(10) {
				@Override
				public int getItemsCount() {
					return productService.getOrdersSize().intValue();
				}

				@Override
				public DataModel<OrderEntity> createPageDataModel() {
					return new ListDataModel<OrderEntity>(productService.getOrders(getPageFirstItem(), getPageSize()));
				}
			};
		}
		return pagination;
	}

	@Override
	public DataModel<OrderEntity> getDataModel() {
		if (dataModel == null) {
			dataModel = getPagination().createPageDataModel();
		}
		return dataModel;
	}

}
