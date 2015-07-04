<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a href="javascript:history.back()">Go Back</a>
<a href="/client/">To Main</a>
<title>Choose a small Section</title>
</head>
<body>
<h3>Choose a small Section</h3>

<form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/toDocumentData" method="post">
<div>
<select class="form-group" name="smallSection" style="width: 401px; height: 40px">
<c:forEach items="${Sections}" var="Section">
<option>${Section.name}</option>
</c:forEach>
</select>
</div>


<p><input class="form-group" type="submit" value="Choose"></p>
</form>

<a href="/admin/addSmallSection">Create a new small section</a>

</body>
</html>