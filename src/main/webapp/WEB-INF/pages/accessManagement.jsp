<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<a href="javascript:history.back()">Go Back</a>
<a href="/client/">To Main</a>
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <h3>Users List</h3>


    <form class="form-inline" role="form" action="/superadmin/saveAccessLevels" method="post">

    <table class="table table-striped">
    
    

        <% int i = 1; %>
        	
      <thead>
        <tr>
        	<td style="width: 60px; "><b>Number</b></td>
        	
        	
            <td style="width: 210px; "><b>Login</b></td>
            
            <td style="width: 210px; "><b>Access Level</b></td>
            
            <td style="width: 150px; "><b>Authorized</b></td>
            
            <td style="width: 150px; "><b>Action</b></td>
            
        </tr>
        </thead>
            	
            	 <c:forEach items="${Users}" var="user">
            <tr>
            <%out.println("<td>"+i+"</td>");%>
            	<td>${user.username}</td>
            	<td>
            	<c:if test="${user.group.name == 'Admins'}">
            	<select name="access" id="access">
  <option selected="selected" value="Admin">Admin</option>
  <option value="Superadmin">Superadmin</option>
  <option value="Client">Client</option>
				</select></c:if>
				
				<c:if test="${user.group.name == 'Superadmins'}">
            	<select name="access" id="access">
  <option selected="selected" value="Superadmin">Superadmin</option>
  <option value="Admin">Admin</option>
  <option value="Client">Client</option>
				</select></c:if>
				
				<c:if test="${user.group.name == null}">
            	<select name="access" id="access">
  <option selected="selected" value="Client">Client</option>
  <option value="Admin">Admin</option>
  <option value="Superadmin">Superadmin</option>
  
  
				</select></c:if>
            	
            	</td>
            	
            	 <td>
            	 
            	 <c:if test="${user.authorized == false}">
            	<select name="authorized" id="authorized">
  <option selected="selected" value="No">No</option>
  <option value="Yes">Yes</option>
				</select></c:if>
				
				<c:if test="${user.authorized == true}">
            	<select name="authorized" id="authorized">
  <option value="No">No</option>
  <option selected="selected" value="Yes">Yes</option>
				</select></c:if>
            	 
            	 </td>
            	 
            	  <td>
            	 
            <a href="/superadmin/deleteUser?id=${user.id}">Delete</a>
            	
            	 
            	 </td>
            	
            	
            	 <% i++; %>
            	
                 </tr>
        </c:forEach>
    </table>
    
  
    
        <input type="submit" value="Save">  
    </form>
</div>
</body>
</html>