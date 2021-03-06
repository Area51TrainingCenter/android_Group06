package com.area51.models;

public class ItemGridViewModel {
	protected int idItem;
	protected String nameItem;
	protected String pathImageItem;
	
	public ItemGridViewModel(int idItem, String nameItem, String pathImageItem) {
		super();
		this.idItem = idItem;
		this.nameItem = nameItem;
		this.pathImageItem = pathImageItem;
	}
	
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getNameItem() {
		return nameItem;
	}
	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}
	public String getPathImageItem() {
		return pathImageItem;
	}
	public void setPathImageItem(String pathImageItem) {
		this.pathImageItem = pathImageItem;
	}
}
