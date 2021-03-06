<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
.hidden {
    display: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a title="Go back" href="javascript:history.back()"><img src="https://cdn1.iconfinder.com/data/icons/free-98-icons/32/back-24.png"></a>
    <a title="Home" href="/client/"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/home.png"></a>
<title>Choose a small Section</title>
</head>
<body>
<h3>Choose a small Section</h3>

<form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/toDocumentData" method="post">
<div>
<select class="form-group" name="smallSection" style="width: 401px; height: 32px">
<c:forEach items="${all[0]}" var="Section">
<option>${Section.name}</option>
</c:forEach>
</select>
</div>

<input class="hidden" type="text" name="bigSection" value="${all[1]}">


<p><button title="OK" class="search" type="submit"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/checkmark.png"></button></p>
</form>

<a class="search" title="add small section" href="/admin/addSmallSection"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/add_list.png"></a>

</body>
</html>