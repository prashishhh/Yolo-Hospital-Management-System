<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Blog</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/add_blog.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/header.jsp" />
	
	<div class="main-content">
    <div class="card">
      <div class="form-container">
        <div class="form-header">
          <h1 class="form-title">Add Blog Post</h1>
        </div>
        
        <form action="addBlog.jsp" method="post">
          
          <div class="form-group">
            <label>Blog Title</label>
            <input type="text" name="blogTitle" placeholder="Enter blog post title" required>
          </div>
          
          <div class="form-group">
            <label>Published Date (Current Date - Cannot be changed)</label>
            <div class="date-display">April 14, 2025</div>
            <input type="hidden" name="blogPublishedDate" value="2025-04-14">
          </div>
          
          <div class="form-group">
            <label>Blog Description</label>
            <textarea name="blogDescription" placeholder="Write your blog content here." required></textarea>
          </div>
          
          <div class="form-actions">
            <button type="button" class="form-btn btn-secondary">Cancel</button>
            <button type="submit" class="form-btn btn-primary">Publish Blog</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>