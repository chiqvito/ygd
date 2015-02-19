package it.bialek.web.controller;

import it.bialek.ejb.ProductService;
import it.bialek.jpa.model.OrderEntity;
import it.bialek.jpa.model.ProductEntity;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

@Named("order")
@SessionScoped
public class OrderController implements Serializable {

	private static final long serialVersionUID = -2400663561078235653L;

	@Inject
	private Logger log;

	@Inject
	private FacesContext facesContext;

	@EJB
	private ProductService productService;

	private ProductEntity product;
	private OrderEntity newOrder;

	public String order(ProductEntity product) {
		this.product = product;
		return "order";
	}

	public ProductEntity getProduct() {
		return product;
	}

	public String register() {
		try {
			newOrder.setProduct(getProduct());
			productService.order(newOrder);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ordered!", "Order successful"));
			initNewOrder();
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			String errorMessage = e.getMessage();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful"));
		}
		return "fail";
	}

	public OrderEntity getNewOrder() {
		if (newOrder == null) {
			initNewOrder();
		}
		return newOrder;
	}

	public void initNewOrder() {
		newOrder = new OrderEntity();
		newOrder.setQuantity(0L);
	}

}
