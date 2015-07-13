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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <a href="javascript:history.back()">Go Back</a>
    <a href="/client/">To Main</a>
</head>
<body>
<div class="container">
<h3>${s.name} contains one or more documents inside which will be deleted!!!</h3>

<form class="form-group" action="/admin/deleteSAnyway" style="width: 140px; ">

<div class="form-group"><input type="text" class="form-control" value="${s.id}" name="sId" style="width: 429px; " readonly></div>
<div class="form-group"><input type="text" class="form-control" value="${s.name}" name="sName" style="width: 429px; " readonly></div>
   
        
        <button class="DeleteButton" type="submit">Delete Anyway</button>
        </form>
        
        
        <form class="form-group" action="/client/" style="width: 140px; ">
        <button class="search" type="submit">Cancel</button>
        </form>
        
         
         

   
    
    
   
    
    
</div>
</body>
</html>