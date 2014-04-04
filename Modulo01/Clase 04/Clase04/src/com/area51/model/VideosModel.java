package com.area51.model;

public class VideosModel {
	
	protected int idVideo;
	protected String nombreVideo;
	protected String descripcionVideo;
	protected int imagenVideo;
	
	
	
	public VideosModel(int idVideo, String nombreVideo,
			String descripcionVideo, int imagenVideo) {
		super();
		this.idVideo = idVideo;
		this.nombreVideo = nombreVideo;
		this.descripcionVideo = descripcionVideo;
		this.imagenVideo = imagenVideo;
	}
	
	public int getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}
	public String getNombreVideo() {
		return nombreVideo;
	}
	public void setNombreVideo(String nombreVideo) {
		this.nombreVideo = nombreVideo;
	}
	public String getDescripcionVideo() {
		return descripcionVideo;
	}
	public void setDescripcionVideo(String descripcionVideo) {
		this.descripcionVideo = descripcionVideo;
	}
	public int getImagenVideo() {
		return imagenVideo;
	}
	public void setImagenVideo(int imagenVideo) {
		this.imagenVideo = imagenVideo;
	}
	
	
	
	
}
