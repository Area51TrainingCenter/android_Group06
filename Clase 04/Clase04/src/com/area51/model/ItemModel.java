package com.area51.model;

public class ItemModel {

	protected int idItem;
	protected String nameItem;
	protected int imageItem;
	
	public ItemModel(int idItem, String nameItem, int imageItem) {
		super();
		this.idItem = idItem;
		this.nameItem = nameItem;
		this.imageItem = imageItem;
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
	public int getImageItem() {
		return imageItem;
	}
	public void setImageItem(int imageItem) {
		this.imageItem = imageItem;
	}
	
	
	
}
