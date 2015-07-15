<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<a href="javascript:history.back()"><img src="https://cdn1.iconfinder.com/data/icons/free-98-icons/32/back-24.png"></a>
    <a href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/saveSmallSection" method="post">
        <div class="form-group"><h3>Small section with such number or name already exists!!! Please insert data properly</h3></div>
        
<div>
<select class="form-group" name="UpperSection" style="width: 345px; height: 37px">
<c:forEach items="${BigSection}" var="BigSection">
<option>${BigSection.name}</option>
</c:forEach>
</select>
</div>

<div class="form-group"><input type="text" class="form-control" name="number" placeholder="Number of section" style="width: 343px; "></div>

        <div class="form-group"><input type="text" class="form-control" required="required" pattern=".{1,}" title="Must be not empty" name="name" placeholder="Name of section" style="width: 342px; "></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
</body>
</html>