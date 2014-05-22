package com.area51.model;

public class Item {

	protected int item;
	protected String nombreItem;
	protected String pathImageItem;

	public Item(int item, String nombreItem, String pathImageItem) {
		super();
		this.item = item;
		this.nombreItem = nombreItem;
		this.pathImageItem = pathImageItem;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}

	public String getPathImageItem() {
		return pathImageItem;
	}

	public void setPathImageItem(String pathImageItem) {
		this.pathImageItem = pathImageItem;
	}

}
