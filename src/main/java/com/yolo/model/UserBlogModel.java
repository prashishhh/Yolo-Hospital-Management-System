package com.yolo.model;

public class UserBlogModel {
	
	private UserModel user;
	private BlogModel blog;
	
	public UserBlogModel() {
		super();
	}

	public UserBlogModel(UserModel user, BlogModel blog) {
		super();
		this.user = user;
		this.blog = blog;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public BlogModel getBlog() {
		return blog;
	}

	public void setBlog(BlogModel blog) {
		this.blog = blog;
	}
	
	
}
