package it.bialek.web.controller;

import java.io.Serializable;

import javax.faces.model.DataModel;

public abstract class CommonListController<T> implements Serializable {

	private static final long serialVersionUID = -6975236622331461281L;

	protected PaginationHelper<T> pagination;
	protected DataModel<T> dataModel = null;

	public abstract PaginationHelper<T> getPagination();

	public abstract DataModel<T> getDataModel();

	private void recreateModel() {
		dataModel = null;
	}

	public String next() {
		getPagination().nextPage();
		recreateModel();
		return "home";
	}

	public String previous() {
		getPagination().previousPage();
		recreateModel();
		return "home";
	}

}
