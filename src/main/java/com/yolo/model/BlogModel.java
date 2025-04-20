package com.yolo.model;

import java.time.LocalDate;

public class BlogModel {
	private int blogID;
	private String blogTitle;
	private LocalDate blogPublishedDate;
	private String blogDescription;
	
	public BlogModel() {
		
	}

	public BlogModel(int blogID, String blogTitle, LocalDate blogPublishedDate, String blogDescription) {
		super();
		this.blogID = blogID;
		this.blogTitle = blogTitle;
		this.blogPublishedDate = blogPublishedDate;
		this.blogDescription = blogDescription;
	}

	public BlogModel(String blogTitle, LocalDate blogPublishedDate, String blogDescription) {
		super();
		this.blogTitle = blogTitle;
		this.blogPublishedDate = blogPublishedDate;
		this.blogDescription = blogDescription;
	}

	public int getBlogID() {
		return blogID;
	}

	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public LocalDate getBlogPublishedDate() {
		return blogPublishedDate;
	}

	public void setBlogPublishedDate(LocalDate blogPublishedDate) {
		this.blogPublishedDate = blogPublishedDate;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	
	
}
