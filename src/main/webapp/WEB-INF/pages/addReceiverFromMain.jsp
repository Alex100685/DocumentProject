<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <a href="javascript:history.back()">Go Back</a>
    <a href="/client/">To Main</a>
    <h4>${note}</h4>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/SaveNewReceiverFromMain" method="post">
        <div class="form-group"><h3>New Receiver</h3></div>
        

<div class="form-group"><input type="text" class="form-control" name="nameOfReceiver" required="required" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,1000}" title="Must be not empty" placeholder="Name of Receiver" style="width: 327px; "></div>
        <div class="form-group"><input type="submit" value="Add"></div>
    </form>
</div>
</body>
</html>