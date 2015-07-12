<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<style>
    a:hover {
      font-weight:bold;
    } 
    body{
    background-color: rjb(120, 50, 120);
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>mazurov.company</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <a href="javascript:history.back()">Go Back</a>
    <a href="/client/">To Main</a>
</head>
<body>


<div class="container">
<a href="<c:url value="/j_spring_security_logout"/>"><h4>Logout</h4></a>
    <h3>Documents List</h3>

<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
       <form class="form-inline" role="form" action="/superadmin/accessManagement" method="post">
     <input type="submit" value="Access Management"> 
    </form>
    </sec:authorize>
    
    <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
       <form class="form-inline" role="form" action="/superadmin/uploadFonts" method="post">
     <input type="submit" value="Font List"> 
    </form>
    </sec:authorize>
    

    

    <table class="table table-striped">
    
    
    <thead>
        <tr>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <td align="center" style="width: 200px; "><b></b></td>
        </sec:authorize>
        
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByInvNumb" method="post" style="width: 100px; ">
        
        <input type="submit" value="Search"><input type="text" class="form-control" name="pattern" placeholder="by numb" style="width: 100px; ">
    </form></b></td>
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByName" method="post" style="width: 200px; ">
        
        <input type="submit" value="Search"><input type="text" class="form-control" name="pattern" placeholder="by Name" style="width: 200px; ">
    </form></b></td>
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByPublisher" method="post" style="width: 200px; ">
        
        <input type="submit" value="Search"><input type="text" class="form-control" name="pattern" placeholder="by Publisher" style="width: 200px; ">
    </form></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px;"><b></b></td>
            </sec:authorize>
            <td><b></b></td>
            
        </tr>
        </thead>
        
        	
      <thead>
        <tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        	<td align="center" style="width: 200px; "><b>Select</b></td>
        	</sec:authorize>
        	
            <td align="center" style="width: 200px; "><b>Inv.number</b>

<% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByInvNum")){
out.println("<form action=\"/client/sortByInvNumRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByInvNum\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
            </td>
            
            <td align="center" style="width: 200px; "><b>Name</b>
            
            <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByName")){
out.println("<form action=\"/client/sortByNameRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByName\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
          </td>  
        
<td align="center" style="width: 200px;"><b>Publisher</b>
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByPublisher")){
out.println("<form action=\"/client/sortByPublisherRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByPublisher\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="/admin/addPublisherFromMain">add</a>
|
<a href="/admin/deletePublisherFromMain">delete</a>
</sec:authorize> 
            </td>
            
             </td>  
        
<td align="center" style="width: 200px;"><b>Publ.Date</b>
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByPublishDate")){
out.println("<form action=\"/client/sortByPublishDateRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByPublishDate\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
            </td>
            
            
            <td align="center" style="width: 200px;"><b> Receiver </b>
        <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByStatus")){
out.println("<form action=\"/client/sortByStatusRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByStatus\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="/admin/addReceiverFromMain">add</a>
|
<a href="/admin/deleteReceiverFromMain">delete</a>
</sec:authorize> 
            </td>
            
            
            
            
            
            <td align="center" style="width: 200px;"><b>Doc.type</b>
     <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByDocType")){
out.println("<form action=\"/client/sortByDocTypeRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByDocType\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
            </td>
            
            
            
            
            <td align="center" style="width: 200px;"><b>Quantity</b>
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByQuantity")){
out.println("<form action=\"/client/sortByQuantityRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByQuantity\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
            </td>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px; "><b>Edit</b></td>
            </sec:authorize>
            
            <td align="center" style="width: 200px;"><b>Note</b>
       <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/sortByNote")){
out.println("<form action=\"/client/sortByNoteRev\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByNote\" style=\"width: 53px; \">");
out.println("<button type=\"submit\">sort</button>");
out.println("</form>");
}
%>
            </td>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px; "><b>Action</b></td>
             </sec:authorize>
            
            <td align="center" style="width: 200px; "><b>View</b></td>
            
        </tr>
        </thead>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <form method="post" action="/admin/deleteDocuments">
        </sec:authorize>
            	
            	 <c:forEach items="${document}" var="document">
            <tr>
            <td align="center" style="width: 200px; "><input type="checkbox" name="id []" value="${document.inventaryNumber}"></td>
            	<td align="right" style="width: 200px; ">${document.inventaryNumber}</td>
            	<td align="center" style="width: 200px; ">${document.name}</td>
            	<td align="center" style="width: 200px; ">${document.publisher.name}</td>
            	<td align="center" style="width: 200px; ">${document.publishDate}</td>
            	<td align="center" style="width: 200px; ">${document.receiver.name}</td>
            	<td align="center" style="width: 200px; ">${document.docType}</td>
            	<td align="center" style="width: 200px; ">${document.quantity}</td>
            	
            	<sec:authorize access="hasRole('ROLE_ADMIN')">
            	<td align="center" style="width: 200px; "><a href="/admin/edit?in=${document.inventaryNumber}">Edit</a></td>
            	</sec:authorize>
            	
            	<td align="center" style="width: 200px; ">${document.note}</td>
            	<sec:authorize access="hasRole('ROLE_ADMIN')">
            	<td align="center" style="width: 200px; ">
            	<c:if test="${document.fileName == null}"><a href="/admin/uploadFile?in=${document.inventaryNumber}">Upload file</a></c:if>
            	<c:if test="${document.fileName != null}"><a href="/admin/downloadFile?in=${document.inventaryNumber}">Download file</a></c:if>
            	</td>
            	</sec:authorize> 
            	
            	<td align="center" style="width: 200px; ">
            	<c:if test="${document.fileName != null}"><a href="/client/seeFile?in=${document.inventaryNumber}">View</a></c:if>
            	</td>
            	
                 </tr>
        </c:forEach>
    </table>
    
    <sec:authorize access="hasRole('ROLE_ADMIN')">
<p><input type="submit" value="Delete"></p>
 </sec:authorize> 
  </form>
  
    <form class="form-inline" role="form" action="/admin/addDocument" method="post">
    
     <sec:authorize access="hasRole('ROLE_ADMIN')">
        <input type="submit" value="Add new">
        </sec:authorize> 
        
    </form>
</div>
</body>
</html>