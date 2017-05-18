<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
<h1>Manage supplier</h1>
<div id="CreateSupplier">
   <form action="manage_supplier_add" method="get">
   <input type="text" name="id">
   <input type="text" name="name">
   <input type="text" name="address">
   <input type="submit" value="Create Supplier">
   
   </form>
</div>
<div id="Updatesupplier">
<form action="manage_supplier_update" method="post">
<input type="text" name="id" value="${selectedSupplier.id}">
<input type="text" name="name" value="${selectedSupplier.name}">
<input type="text" name="address" value="${selectedSupplier.address}">
<input type="submit" value="Update Supplier">
</form>

</div>

  <h2>Delete/Update supplier</h2>
  
  <div id="ShowSupplier">
  <table border="2">
  <thead>
  <tr>
  <td>Supplier ID</td>
  <td>Supplier Name</td>
  <td>Supplier address</td>
  <td>Action</td>
  </tr>
  </thead>
  
  <c:forEach var="supplier" items="${supplierList}">
  <tr>
  <td>${supplier.id}</td>
  <td>${supplier.name}</td>
  <td>${supplier.address}</td>
  <td>
  <a href="manage_supplier_delete/${supplier.id}">Delete</a>
  <a href="manage_supplier_edit/${supplier.id}">|Edit</a>
  <%-- <td><a href="<c:url value='/manage_supplier_edit/${supplier.id}'/>">Edit</a></td>
  
  <td><a href="<c:url value='/manage_supplier_delete/${supplier.id}'/>">Delete</a></td> --%>
  </td>
  </tr>
  </c:forEach>
  
  
  
  </table>
  
  
  </div>



</body>
</html>