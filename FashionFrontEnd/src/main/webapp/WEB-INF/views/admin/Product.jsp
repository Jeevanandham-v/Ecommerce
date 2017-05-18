<html>
<head>
<title>Product Page</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<h1>Add a Product</h1>

	<c:url var="addAction" value="/manage_product_add?${_csrf.parameterName}=${_csrf.token}"></c:url>

	<form:form action="${addAction}" commandName="product"
		enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td><form:label path="id">
						<spring:message text="ID" />
					</form:label></td>
				<c:choose>
					<c:when test="${!empty product.id}">
						<td><form:input path="id" value="" disabled="true" readonly="true" />
						</td>
					</c:when>

					<c:otherwise>
						<td><form:input path="id" pattern=".{5,20}" required="true"
								title="id should contains 5 to 20 characters" /></td>
					</c:otherwise>
				</c:choose>
			<tr>
				<form:input path="id" hidden="true" />
				
				
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" required="true" /></td>
			</tr>


			<tr>
				<td> Price </td>
				<td><form:input path="price" required="true" /></td>
			</tr>

			<tr>
				<td>Description></td>
				<td><form:input path="description" required="true" /></td>
			</tr>

			<tr>
				<td>Supplier</td>
				<td><form:select path="supplier.name" items="${supplierList}"
						itemValue="name" itemLabel="name" /></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><form:select path="category.name" items="${categoryList}"
						itemValue="name" itemLabel="name" /></td>
			</tr>
			<tr>
				<td align="left"><form:label path="image">
						<spring:message text=" Image" />
					</form:label></td>
				<td align="left"><form:input type="file" name="image" path="image" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty product.name}">
						<input type="submit" value="<spring:message text="Edit Product"/>" />
					</c:if> <c:if test="${empty product.name}">
						<input type="submit" value="<spring:message text="Add Product"/>" />
					</c:if></td>
			</tr>
		</table>
		
		<input type="hidden" 
             name="${_csrf.parameterName}" 
             value="${_csrf.token}" />
	</form:form>
	<br>



	<h3>Product List</h3>
	<c:if test="${!empty productList}">
		<table class="tg">
			<tr>
				<th width="80">Product ID</th>
				<th width="120">Product Name</th>
				<th width="200">Product Description</th>
				<th width="80">Price</th>
				<th width="80">Product Category</th>
				<th width="80">Product Supplier</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${productList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td>${product.category.name}</td>
					<td>${product.supplier.name}</td>
					<%-- <td><a href="<c:url value='manage_product_edit/${product.id}' />">Edit</a></td>
					<td><a href="<c:url value='manage_product_delete/${product.id}' />">Delete</a></td> --%>
					<td><a href="manage_product_delete/${product.id}">Delete</a></td>
					<td><a href="manage_product_edit/${product.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>