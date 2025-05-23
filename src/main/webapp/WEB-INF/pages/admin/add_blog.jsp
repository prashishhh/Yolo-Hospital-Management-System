<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.time.LocalDate" %>
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
                
                <form action="${pageContext.request.contextPath}/add-blog" method="post">
                    <div class="form-group">
                        <label>Blog Title</label>
                        <input type="text" name="blogTitle" placeholder="Enter blog post title" 
                            value="${blogTitle != null ? blogTitle : ''}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Published Date (Current Date - Cannot be changed)</label>
                        <div class="date-display"><%= LocalDate.now() %></div>
                        <input type="hidden" name="blogPublishedDate" value="<%= LocalDate.now() %>">
                    </div>
                    
                    <div class="form-group">
                        <label>Blog Description</label>
                        <textarea name="blogDescription" placeholder="Write your blog content here." required>${blogDescription != null ? blogDescription : ''}</textarea>
                    </div>
                    
                    <div class="form-actions">
                        <button type="button" class="form-btn btn-secondary">Cancel</button>
                        <button type="submit" class="form-btn btn-primary">Publish Blog</button>
                    </div>
                    
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="error-message"><%= request.getAttribute("error") %></div>
                    <% } %>
                </form>
            </div>
        </div>
    </div>
</body>
</html>