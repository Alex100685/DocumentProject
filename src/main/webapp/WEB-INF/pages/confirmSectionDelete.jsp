<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
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
   
        
        <button type="submit">Delete Anyway</button>
        </form>
        
        
        <form class="form-group" action="/client/" style="width: 140px; ">
        <button type="submit">Cancel</button>
        </form>
        
         
         

   
    
    
   
    
    
</div>
</body>
</html>