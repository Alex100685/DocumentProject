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
.hidden {
    display: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Document</title>
<a href="javascript:history.back()"><img src="https://cdn1.iconfinder.com/data/icons/free-98-icons/32/back-24.png"></a>
    <a href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/createDocument" method="post">
        <div class="form-group"><h3>New Document</h3></div>
        
        <div class="form-group"><input type="text" value="${all[1].name}"  class="form-control" name="sectionName" placeholder="Section Name" style="width: 429px; " readonly></div>
        
        Publisher :
        <div> 
        <select class="form-group" name="publisher" style="width: 429px; height: 34px">
<c:forEach items="${all[0]}" var="Publisher">
<option>${Publisher.name}</option>
</c:forEach>
</select> 
		</div>
		
		<div class="form-group"> 
		<a href="/admin/createNewPublisher">Create new Publisher</a>
		</div>
		
		Publish Date:
		<div class="form-group"> 
  
  <input type="text" class="form-control" name="publishDate" style="width: 429px; height: 34px">
  <input type="text" class="hidden" name="bigSectionName" value="${all[4]}" style="width: 429px; height: 34px">
</div> 
		
		
    Number in section:    
<div class="form-group"><input type="text" class="form-control" pattern = "[0-9]{0,3}$" title="Must contain only numbers from 1 to 999" value="${all[2]}" name="numberInSection" required = "required" placeholder="Number in section" style="width: 429px; "></div>
        Document name:
        <div class="form-group"><input type="text" class="form-control" required="required" pattern=".{1,}" title="Must be not empty" name="name" placeholder="Name" style="width: 429px; "></div>
        

        Receiver :
        <div> 
        <select class="form-group" name="receiver" style="width: 429px; height: 32px">
<c:forEach items="${all[3]}" var="receiver">
<option>${receiver.name}</option>
</c:forEach>
</select> 
		</div>
		Note:
        <div class="form-group"><input type="text" class="form-control" name="note" placeholder="Note" style="width: 429px; "></div>
        Document type:
        <div class="form-group"><input type="text" class="form-control" name="doctype" placeholder="Doc. type" style="width: 429px; "></div>
        Quantity
        <div class="form-group"><input type="text" pattern = "[0-9]{1,3}$" title="Must contain only numbers from 1 to 999" required = "required" class="form-control" name="quantity" placeholder="Quantity" style="width: 429px; "></div>
        
        
        <div class="form-group">File: <input type="file" name="file"></div>

        <div><button type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/save.png"></button></div>
    </form>
</div>
</body>
</html>