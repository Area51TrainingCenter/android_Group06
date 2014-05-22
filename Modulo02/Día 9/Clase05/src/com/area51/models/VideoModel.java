package com.area51.models;

public class VideoModel {
	protected int idVideo;
	protected String nameVideo;
	protected String descriptionVideo;
	protected String pathImageVideo;
	
	public VideoModel(int idVideo, String nameVideo, String descriptionVideo,
			String pathImageVideo) {
		super();
		this.idVideo = idVideo;
		this.nameVideo = nameVideo;
		this.descriptionVideo = descriptionVideo;
		this.pathImageVideo = pathImageVideo;
	}
	
	public int getIdVideo() {
		return idVideo;
	}
	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}
	public String getNameVideo() {
		return nameVideo;
	}
	public void setNameVideo(String nameVideo) {
		this.nameVideo = nameVideo;
	}
	public String getDescriptionVideo() {
		return descriptionVideo;
	}
	public void setDescriptionVideo(String descriptionVideo) {
		this.descriptionVideo = descriptionVideo;
	}
	public String getPathImageVideo() {
		return pathImageVideo;
	}
	public void setPathImageVideo(String pathImageVideo) {
		this.pathImageVideo = pathImageVideo;
	}

}
