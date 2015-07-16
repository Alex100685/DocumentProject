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
    .image {
    ​width: 70%;
    height: 70%;
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
  cursor: pointer;
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
  cursor: pointer;
}

.DeleteButton:hover {
  color: rgb(24,24,24);
  border: 1px solid rgb(198,198,198);
  background: #f7f7f7 linear-gradient(#faa3a3, #ff7070);
  box-shadow: 0 1px 2px rgba(0,0,0,.1);
}

.ViewButton {
   
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
  cursor: pointer;
}

.ViewButton:hover {
  color: rgb(24,24,24);
  border: 1px solid rgb(198,198,198);
  background: #f7f7f7 linear-gradient(#f7f7f7, #f1f1f1);
  box-shadow: 0 1px 2px rgba(0,0,0,.1);
  
}

    .table table-striped {
  display: block;
  font-family: arial;
  -webkit-font-smoothing: antialiased;
  font-size: 115%;
  overflow: auto;
  width: auto;
  }
  th {
    background-color: rgb(112,196,105);
    color: white;
    font-weight: normal;
    padding: 5px 20px;
    text-align: center;
  }
  td {
    background-color: rgb(238, 238, 238);
    color: rgb(111, 111, 111);
    padding: 5px 20px;
  }
  .options{
  display: block;
  }
  ​
div#formline*{
display: inline-block;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>mazurov.company</title>
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> -->
</head>


<body>

<div class="container">
<a class="logout" title="Exit" href="<c:url value="/j_spring_security_logout"/>"><h4><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/logout.png"></h4></a>


    <h3>Documents List</h3>
    
   <div id="formline">
   
     <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
       <form action="/superadmin/accessManagement" method="post">
     <button title="users access management" type="submit" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/conference.png"></button>
    </form>
       <form action="/superadmin/uploadFonts" method="post">
     <button title="font management" type="submit" class="search" value="Font List"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/services.png"> </button>
    </form>
    </sec:authorize>
  
</div>
    

    <table class="table table-striped">
    
    
    <thead>
        <tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <td align="center" style="width: 200px; "><b></b></td>
        </sec:authorize>
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByInvNumb" method="post" style="width: 100px; ">
        
        <button type="submit" class="search"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/search.png"> </button><input type="text" class="form-control" name="pattern" placeholder="by Number" style="width: 100px; ">
    </form></b></td>
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByName" method="post" style="width: 200px; ">
        
        <button type="submit" class="search"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/search.png"> </button><input type="text" class="form-control" name="pattern" placeholder="by Name" style="width: 200px; ">
    </form></b></td>
            <td align="center"><b><form class="form-inline" role="form" action="/client/searchByPublisher" method="post" style="width: 200px; ">
        
        <button type="submit" class="search"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/search.png"> </button><input type="text" class="form-control" name="pattern" placeholder="by Publisher" style="width: 200px; ">
    </form></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            
            
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px;"><b></b></td>
            <td align="center" style="width: 200px;"><b></b></td>
            </sec:authorize>
            
        </tr>
        </thead>
        
        	
      <thead>
        <tr>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        	<th align="center" style="width: 200px; "><b>Select</b></th>
        	</sec:authorize>
        	
            <th align="center" style="width: 200px; "><b>Inv.number</b></th>
            
            <th align="center" style="width: 200px; "><b>Name</b></th>
            <th align="center" style="width: 200px; "><b>Publisher</b></th>
            <th align="center" style="width: 200px; "><b>Publ.date</b></th>
            <th align="center" style="width: 200px; "><b> Receiver </b></th>
            <th align="center" style="width: 200px; "><b>Doc.type</b></th>
            <th align="center" style="width: 200px; "><b>Quantity</b></th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th align="center" style="width: 200px; "><b>Edit</b></th>
            </sec:authorize>
            <th align="center" style="width: 200px; "><b>Note</b></th>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th align="center" style="width: 200px; "><b>Action</b></th>
            </sec:authorize> 
            <th align="center" style="width: 200px; "><b>View</b></th>
            </tr>
       </thead>
        
        
        <thead>
        <tr>    
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px; "></td>
            </sec:authorize>
			<td align="center" style="width: 200px; ">
<% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByInvNum")){
out.println("<form action=\"/client/sortByInvNumRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByInvNum\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            <td align="center" style="width: 200px; ">
            
            <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByName")){
out.println("<form action=\"/client/sortByNameRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByName\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
          </td>  
        
<td align="center" style="width: 200px; ">
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByPublisher")){
out.println("<form action=\"/client/sortByPublisherRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByPublisher\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>  
            </td>
            
         
        
<td align="center" style="width: 200px; ">
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByPublishDate")){
out.println("<form action=\"/client/sortByPublishDateRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByPublishDate\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            
            <td align="center" style="width: 200px; ">
        <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByStatus")){
out.println("<form action=\"/client/sortByStatusRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByStatus\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            
            
            
            
            <td align="center" style="width: 200px; ">
     <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByDocType")){
out.println("<form action=\"/client/sortByDocTypeRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByDocType\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            
            
            
            <td align="center" style="width: 200px; ">
      <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByQuantity")){
out.println("<form action=\"/client/sortByQuantityRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByQuantity\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            <td align="center" style="width: 200px; "></td>
            
            
            
            <td align="center" style="width: 200px; ">
       <% 
if(request.getAttribute("javax.servlet.forward.servlet_path").toString().endsWith("/client/sortByNote")){
out.println("<form action=\"/client/sortByNoteRev\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-up-16.png\"></button>");
out.println("</form>");
}
else{
out.println("<form action=\"/client/sortByNote\" style=\"width: 53px; \">");
out.println("<button class=\"search\" type=\"submit\"><img class=\"image\" src=\"https://cdn0.iconfinder.com/data/icons/glyphpack/26/double-arrow-down-16.png\"></button>");
out.println("</form>");
}
%>
            </td>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            
            <td align="center" style="width: 200px; "></td>
            <td align="center" style="width: 200px; "></td>
            
            </sec:authorize> 
            
            
            
        </tr>
       <h1> </thead>
       
       <thead>
        <tr>    
            
            
            <td align="center" style="width: 200px; "></td>
            
			<td align="center" style="width: 200px; "></td>
            
            <td align="center" style="width: 200px; ">
          </td>  
        
<td align="center" style="width: 200px; ">
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a title="add publishers" href="/admin/addPublisherFromMain">add</a>
|
<a title="delete publishers" href="/admin/deletePublisherFromMain">delete</a>
</sec:authorize>  
            </td>
            
         
        
<td align="center" style="width: 200px; ">
            </td>
            
            
            <td align="center" style="width: 200px; ">
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a title="add receivers" href="/admin/addReceiverFromMain">add</a>
|
<a title="delete receivers" href="/admin/deleteReceiverFromMain">delete</a>
</sec:authorize> 
            </td>
            
            
            
            
            
            <td align="center" style="width: 200px; "></td>
            
            
            
            
            <td align="center" style="width: 200px; ">
            </td>
            
            <td align="center" style="width: 200px; "></td>
            
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px; ">
            </td>
            
            <td align="center" style="width: 200px; "></td>
            <td align="center" style="width: 200px; "></td>
            </sec:authorize>
            
            
        </tr>
       <h1> </thead>
        
         <sec:authorize access="hasRole('ROLE_ADMIN')">
        <form method="post" action="/admin/deleteDocuments">
            </sec:authorize> 
            	
            <c:forEach items="${bs}" var="bigSection">
            <thead>
            <tr>	
            <sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <td align="center" style="width: 200px; "><b></b>
          				</td>  
          				</sec:authorize> 
			<td align="left" style="width: 200px; color:black "><b><h3>${bigSection.id}</h3></b>
            				</td>
			<td align="center" style="width: 200px; color:black "><b><h3>${bigSection.name}</h3></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b>
            					</td>
            <td align="center" style="width: 200px; "><b></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <td align="center" style="width: 200px; "><b></b></td>
            <td align="center" style="width: 200px; "><b></b></td>
            		
            		 
            <td align="center" style="width: 200px; color:red "><a class="DeleteButton" title="delete big section" href="/admin/deleteBigSection?id=${bigSection.id}"><img  class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a></td> 
            </sec:authorize> 
            <td align="center" style="width: 200px; "><b></b></td>
        			</tr>
        </thead>
      
            	 <c:forEach items="${bigSection.sections}" var="sect">
            	 <thead>
            	 <tr>
            	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
           	<td align="center" style="width: 200px; "><b></b>
          				</td> 
 				</sec:authorize>
			<td align="center" style="width: 200px; color:black"><b><h4>${sect.id}</h4></b>
            				</td>
			<td align="center" style="width: 200px; color:black"><b><h4>${sect.name}</h4></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b>
            					</td>
            <td align="center" style="width: 200px; "><b></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b>
            				</td>
            <td align="center" style="width: 200px; "><b></b></td>
             <td align="center" style="width: 200px; "><b></b></td>
             <sec:authorize access="hasRole('ROLE_ADMIN')">
              <td align="center" style="width: 200px; "><b></b></td>
               <td align="center" style="width: 200px; "><b></b></td>
                
            <td align="center" style="width: 200px; "><a class="DeleteButton" title="delete section" href="/admin/deleteSection?id=${sect.id}"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></a>
            		</td>
            		</sec:authorize> 
            	 <td align="center" style="width: 200px; "><b></b></td>
            	 </tr>
            	 </thead>
            	 
            	  <c:forEach items="${sect.documents}" var="document">
            	 
            	 
            	 <tr>
            	 <sec:authorize access="hasRole('ROLE_ADMIN')">
            	 <td align="center" style="width: 200px; "><input type="checkbox" name="id []" value="${document.inventaryNumber}"></td>
            	</sec:authorize> 
            	<td align="right" style="width: 200px; ">${document.inventaryNumber}</td>
            	<td align="center" style="width: 200px; ">${document.name}</td>
            	<td align="center" style="width: 200px; ">${document.publisher.name}</td>
            	<td align="center" style="width: 200px; ">${document.publishDate}</td>
            	<td align="center" style="width: 200px; ">${document.receiver.name}</td>
            	<td align="center" style="width: 200px; ">${document.docType}</td>
            	<td align="center" style="width: 200px; ">${document.quantity}</td>
            	
            	<sec:authorize access="hasRole('ROLE_ADMIN')">
            	<td align="center" style="width: 200px; "><a class="search" title="edit document" href="/admin/edit?in=${document.inventaryNumber}"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/edit_property.png"></a></td>
            	</sec:authorize> 
            	
            	<td align="center" style="width: 200px; ">${document.note}</td>
            	
            	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
            	<td align="center" style="width: 200px; ">
            	
            	<c:if test="${document.fileName == null}"><a class="search" title="upload file" href="/admin/uploadFile?in=${document.inventaryNumber}"> <img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/upload.png"></a></c:if>
            	<c:if test="${document.fileName != null}"><a class="search" title="download file" href="/admin/downloadFile?in=${document.inventaryNumber}"><img class="image" src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/download.png"></a></c:if>
            	
            	</td>
            	</sec:authorize> 
            	
            	<td align="center" style="width: 200px; ">
            	<c:if test="${document.fileName != null}"><a class="ViewButton" title="view document as PDF" href="/client/seeFile?in=${document.inventaryNumber}"><img class="image" src="https://cdn2.iconfinder.com/data/icons/flat-ui-icons-24-px/24/eye-24-24.png"></a></c:if>
            	</td>
            	
            	 
            	 </tr>
            	
            	 </c:forEach>
            	 </c:forEach>
            	 
            
       </c:forEach> 
    </table>
    
   <sec:authorize access="hasRole('ROLE_ADMIN')"> 
<p><button type="submit" title="delete selected" class="DeleteButton"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/trash.png"></button></p>
</sec:authorize> 

  </form>
  
    <form role="form" action="/admin/addDocument" method="post">
    
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <button type="submit" title="add new document" class="search"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/add_list.png"></button>
     </sec:authorize>  
     
     <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
     <a class="search" title="import documents from excel" href="/superadmin/importFromExcel"><img src="https://cdn2.iconfinder.com/data/icons/windows-8-metro-style/26/excel.png"></a>
     </sec:authorize> 
</div>
</body>
</html>