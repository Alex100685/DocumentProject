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

<a href="javascript:history.back()"><img src="https://cdn1.iconfinder.com/data/icons/free-98-icons/32/back-24.png"></a>
    <a href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
<title>Login records</title>
</head>
<body>

<div>
    <form class="form-inline" action="/superadmin/showActions" method="post" style="width: 100px; ">
    <button type="submit" class="search">Show rows:</button><input type="text" class="form-control" name="pattern" pattern = "[0-9]{1,3}$" title="Must contain only numbers from 1 to 999" required = "required" placeholder="number of rows" style="width: 100px; ">
	</form>
</div>

<div class="container">
    <h3>Action record list</h3>


    

    <table class="table table-striped">

        
        	
      <thead>
        <tr>
        	<th style="width: 60px; "><b>Action id</b></th>
        	
            <th style="width: 210px; "><b>Date</b></th>
            
            <th style="width: 210px; "><b>User </b></th>
            <th style="width: 210px; "><b>User id </b></th>
            <th style="width: 210px; "><b>Document name </b></th>
            <th style="width: 210px; "><b>Action name </b></th>
            <th style="width: 210px; "><b>Old value</b></th>
            <th style="width: 210px; "><b>New value</b></th>
            
        </tr>
        </thead>
            	
            	 <c:forEach items="${actionRecords}" var="record">
            <tr>
            	<td>${record.id}</td>
            	
            	<td>${record.date}</td>
            	
            	<td>${record.userName}</td>
            	
            	<td>${record.userId}</td>
            	
            	<td>${record.documentName}</td>
            	
            	<td>${record.actionName}</td>
            	
            	<td>${record.oldValue}</td>
            	
            	<td>${record.newValue}</td>
            	
                 </tr>
        </c:forEach>
    </table>
</div>
<div>
    <a class="DeleteButton" title="delete history" href="/superadmin/deleteActionHistory">delete history</a>
	
</div>
</body>
</html>