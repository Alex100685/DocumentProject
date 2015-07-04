<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a href="javascript:history.back()">Go Back</a>
<a href="/client/">To Main</a>
<title>Import from Excel</title>
</head>
<body>
<h4>${note}</h4>

<div class="container">

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/superadmin/saveDocsFromExcel" method="post">
        <div class="form-group"><h3>Import from Excel</h3></div>
        <div class="form-group">File: <input type="file" name="file"></div>
        <div class="form-group"><input type="submit" value="Import"></div>
    </form>
</div>

</body>
</html>