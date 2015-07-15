<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
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
.container {
    margin:0px auto;
    max-width: 1400px;
    padding: 20px 12px 10px 20px;
    font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}
.container input[type=text], 
select{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border:1px solid #BEBEBE;
    padding: 7px;
    margin:0px;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;  
}
.container input[type=text]:focus, 
.container input[type=date]:focus,
.container input[type=datetime]:focus,
.container input[type=number]:focus,
.container input[type=search]:focus,
.container input[type=time]:focus,
.container input[type=url]:focus,
.container input[type=email]:focus,
.container textarea:focus, 
.container select:focus{
    -moz-box-shadow: 0 0 8px #88D5E9;
    -webkit-box-shadow: 0 0 8px #88D5E9;
    box-shadow: 0 0 8px #88D5E9;
    border: 1px solid #88D5E9;
}
.container .required{
    color:red;
}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> -->
<a href="javascript:history.back()"><img src="https://cdn1.iconfinder.com/data/icons/free-98-icons/32/back-24.png"></a>
    <a href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
</head>
<body>
<div class="container">
<c:if test="${all[1].fileName != null}">
    
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/saveEditedDoc" method="post">
    
    
        <div class="form-group"><h3>Edit Document</h3></div>
        
        Publisher :
        <div> 
        <select class="form-group" name="publisher" style="width: 429px; height: 34px">
<c:forEach items="${all[0]}" var="Publisher">
<option>${Publisher.name}</option>
<c:if test="${Publisher.name == all[1].publisher.name}">
<option selected="selected" value="${Publisher.name}">${Publisher.name}</option>
</c:if>
</c:forEach>
</select> 
		
	</div>
	<div> 
	<a href="/admin/createNewPublisher">Create new Publisher</a>
	
		Publish Date:
		<div class="form-group"> 
  
  <input type="text" class="form-control" name="publishDate" value="${all[1].publishDate}" style="width: 429px; height: 34px">
</div> 
		
		Inventary number:
		<div class="form-group"><input type="text" class="form-control" value="${all[1].inventaryNumber}" name="inventaryNumber" class="form-control" style="width: 429px; " readonly></div>
		Document name:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].name}" name="name" pattern=".{1,}" placeholder="Name" style="width: 429px; "></div>
        
        Receiver :
        <div> 
        <select class="form-group" name="receiver" style="width: 429px; height: 32px">
<c:forEach items="${all[2]}" var="receiver">
<option>${receiver.name}</option>
<c:if test="${receiver.name == all[1].receiver.name}">
<option selected="selected" value="${all[1].receiver.name}">${receiver.name}</option>
</c:if>
</c:forEach>
</select> 

</div>
		Note:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].note}" name="note" placeholder="Note" style="width: 429px; "></div>
        Document type:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].docType}" name="doctype" placeholder="Doc. type" style="width: 429px; "></div>
        Quantity:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].quantity}" name="quantity" placeholder="Quantity" style="width: 429px; "></div>
            	<a href="/admin/deleteFile?in=${all[1].inventaryNumber}">Delete file</a>   	
        
         <br />
        <div><button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></div>
        
        
        
    </form>
    </c:if>
    
    
    <c:if test="${all[1].fileName == null}">
    
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/saveEditedDoc2" method="post">
    
    
        <div class="form-group"><h3>Edit Document</h3></div>
        
        Publisher :
        <div> 
        <select class="form-group" name="publisher" style="width: 429px; height: 34px">
<c:forEach items="${all[0]}" var="Publisher">
<option>${Publisher.name}</option>
<c:if test="${Publisher.name == all[1].publisher.name}">
<option selected="selected" value="${Publisher.name}">${Publisher.name}</option>
</c:if>

</c:forEach>
</select> 
		</div>
		
		Publish Date:
		<div class="form-group"> 
  
  <input type="text" class="form-control" name="publishDate" value="${all[1].publishDate}" style="width: 429px; height: 34px">
</div> 
		
		<div><a href="/admin/createNewPublisher">Create new Publisher</a></div>
		Inventary number:
		<div class="form-group"><input type="text" class="form-control" value="${all[1].inventaryNumber}" name="inventaryNumber" placeholder="Number" style="width: 429px; " readonly></div>
        Document name:
        <div class="form-group"><input type="text" class="form-control" pattern=".{1,}" title="Must be not empty" value="${all[1].name}" name="name" placeholder="Name" style="width: 429px; "></div>
        
        Receiver :
        <div> 
        <select class="form-group" name="receiver" style="width: 429px; height: 32px">
<c:forEach items="${all[2]}" var="receiver">
<option>${receiver.name}</option>
<c:if test="${receiver.name == all[1].receiver.name}">
<option selected="selected" value="${all[1].receiver.name}">${receiver.name}</option>
</c:if>
</c:forEach>
</select> 

</div>
		Note:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].note}" name="note" placeholder="Note" style="width: 429px; "></div>
        Document type:
        <div class="form-group"><input type="text" class="form-control" value="${all[1].docType}" name="doctype" placeholder="Doc. type" style="width: 429px; "></div>
        Quantity:
        <div class="form-group"><input type="text" class="form-control" pattern = "[0-9]{1,3}$" title="Must contain only numbers from 1 to 999" value="${all[1].quantity}" name="quantity" placeholder="Quantity" style="width: 429px; "></div>
        
            	<label class="file_upload">
        <input class="search" type="file" name="file">
    			</label>
        <br />
        <div><button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></div>
        
        
        
    </form>
    </c:if>
    
    
</div>
</body>
</html>