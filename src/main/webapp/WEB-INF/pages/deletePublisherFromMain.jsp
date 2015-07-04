<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<a href="javascript:history.back()">Go Back</a>
<a href="/client/">To Main</a>
<h4>${all[1]}</h4>
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <h3>Users List</h3>


    

    <table class="table table-striped">
    
    

        
        	
      <thead>
        <tr>
        	<td style="width: 60px; "><b>Id</b></td>
        	
            <td style="width: 210px; "><b>Name</b></td>
            
            <td style="width: 150px; "><b>Action</b></td>
            
        </tr>
        </thead>
            	
            	 <c:forEach items="${all[0]}" var="publisher">
            <tr>
            	<td>${publisher.id}</td>
            	
            	<td>${publisher.name}</td>
            	
            	 <td>
            	 <a href="/admin/deletePublisher?id=${publisher.id}">Delete</a>
            	 </td>
            	 
            	
                 </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>