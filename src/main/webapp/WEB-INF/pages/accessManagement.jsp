<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
 a:hover {
      font-weight:bold;
    }
    
 .search {
  display: inline-block;
  font-family: arial,sans-serif;
  font-size: 12px;
  font-weight: bold;
  color: rgb(68,68,68);
  text-decoration: none;
  user-select: none;
  padding: .2em 1.2em;
  outline: none;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 2px;
  background: rgb(245,245,245) linear-gradient(#f4f4f4, #f1f1f1);
  transition: all .218s ease 0s;
  cursor: pointer;
}
.search:hover {
  color: rgb(24,24,24);
  border: 1px solid rgb(198,198,198);
  background: #f7f7f7 linear-gradient(#f7f7f7, #f1f1f1);
  box-shadow: 0 1px 2px rgba(0,0,0,.1);
}
.search:active {
  color: rgb(51,51,51);
  border: 1px solid rgb(204,204,204);
  background: rgb(238,238,238) linear-gradient(rgb(238,238,238), rgb(224,224,224));
  box-shadow: 0 1px 2px rgba(0,0,0,.1) inset;
}
.DeleteButton {
  display: inline-block;
  font-family: arial,sans-serif;
  font-size: 12px;
  font-weight: bold;
  color: rgb(68,68,68);
  text-decoration: none;
  user-select: none;
  padding: .2em 1.2em;
  outline: none;
  border: 1px solid rgba(0,0,0,.1);
  border-radius: 2px;
  background: #f7f7f7 linear-gradient(#faa3a3, #ff7070);
  transition: all .218s ease 0s;
}

.DeleteButton:hover {
  color: rgb(24,24,24);
  border: 1px solid rgb(198,198,198);
  background: #f7f7f7 linear-gradient(#faa3a3, #ff7070);
  box-shadow: 0 1px 2px rgba(0,0,0,.1);
}

    .flat-table {
  display: block;
  font-family: arial;
  -webkit-font-smoothing: antialiased;
  font-size: 115%;
  overflow: auto;
  width: auto;
  }
  th {
    background-color: rgb(112,196,105);
    color: white;
    font-weight: normal;
    padding: 5px 20px;
    text-align: center;
  }
  td {
    background-color: rgb(238, 238, 238);
    color: rgb(111, 111, 111);
    padding: 5px 20px;
  }
  .options{
  display: block;
  }
div#formline *{
display:inline
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <a title="Home" href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
<title>Access Management</title>
</head>
<body>
<div class="container">
    <h3>Users List</h3>


    <form class="form-inline" role="form" action="/superadmin/saveAccessLevels" method="post">

    <table class="table table-striped">
    
    

        <% int i = 1; %>
        	
      <thead>
        <tr>
        	<th style="width: 60px; "><b>Number</b></th>
        	
        	
            <th style="width: 210px; "><b>Login</b></th>
            
            <th style="width: 210px; "><b>Registration Date</b></th>
            
            <th style="width: 210px; "><b>Access Level</b></th>
            
            <th style="width: 150px; "><b>Authorized</b></th>
            
            <th style="width: 150px; "><b>Action</b></th>
            
        </tr>
        </thead>
            	
            	 <c:forEach items="${Users}" var="user">
            <tr>
            <%out.println("<td>"+i+"</td>");%>
            	<td>${user.username}</td>
            	
            	<td>${user.registrationDate}</td>
            	
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
            	 
            <a class="DeleteButton" title="delete user" href="/superadmin/deleteUser?id=${user.id}"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a>
            	
            	 
            	 </td>
            	
            	
            	 <% i++; %>
            	
                 </tr>
        </c:forEach>
    </table>
    
  
    
        <button class="search" title="save" type="submit"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button>  
    </form>
</div>
</body>
</html>