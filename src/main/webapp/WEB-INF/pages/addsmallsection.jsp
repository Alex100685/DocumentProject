<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <a href="javascript:history.back()">Go Back</a>
    <a href="/client/">To Main</a>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/saveSmallSection" method="post">
        <div class="form-group"><h3>New Small Section</h3></div>
        
<div>
<select class="form-group" name="UpperSection" style="width: 304px; height: 35px">
<c:forEach items="${BigSection}" var="BigSection">
<option>${BigSection.name}</option>
</c:forEach>
</select>
</div>

<div class="form-group"><input type="text" class="form-control" name="number" pattern = "[0-9]{0,3}$" title="Must contain only numbers from 1 to 999" placeholder="Number of section" style="width: 304px; "></div>

        <div class="form-group"><input type="text" class="form-control" required="required" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,1000}" title="Must be not empty" name="name" placeholder="Name of section" style="width: 304px; "></div>
        <div class="form-group"><input type="submit"  value="Add"></div>
    </form>
</div>
</body>
</html>