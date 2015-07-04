<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a href="javascript:history.back()">Go Back</a>
<a href="/client/">To Main</a>
<title>Choose a big section</title>
</head>
<body>
<h3>Choose a Big Section</h3>




<form method="post" action="/admin/ChooseSmallSection">
    
    
<div>
<select class="form-group" name="SelectBigSection" style="width: 370px; height: 46px">
<c:forEach items="${bigSections}" var="bigSection">

<option>${bigSection.name}</option>

</c:forEach>
</select>
</div>
    
    
<p><input class="form-group" type="submit" value="Choose"></p>
  </form>



<a href="/admin/addBigSection">Create new Big Section</a>

</body>
</html>