package com.area51.model;

public class Imagen {
	
	protected int idImagen;
	protected String nombreImagen;
	protected int rutaImagen;
		
	
	
	public Imagen(int idImagen, String nombreImagen, int rutaImagen) {
		super();
		this.idImagen = idImagen;
		this.nombreImagen = nombreImagen;
		this.rutaImagen = rutaImagen;
	}
	
	public int getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	public int getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(int rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	

}
