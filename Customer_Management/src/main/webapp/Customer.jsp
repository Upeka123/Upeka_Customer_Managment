<%@page import="model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Customer.js"></script>
</head>
<body>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Customer Management V10.1</h1>
<form id="formCustomer" name="formCustomer" method="post" action="Customer.jsp">

 <br> Customer_Name: 
 <input id="CustomerName" name="CustomerName" type="text" 
 class="form-control form-control-sm">
 <br> Address: 
 <input id="Address" name="Address" type="text" 
 class="form-control form-control-sm">
 <br> Mobile_No: 
 <input id="MobileNo" name="MobileNo" type="text" 
 class="form-control form-control-sm">
 <br>Email: 
 <input id="Email" name="Email" type="text" 
 class="form-control form-control-sm">
 <br>Acc_Number: 
 <input id="AccNumber" name="AccNumber" type="text" 
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidCustomerIDSave" 
 name="hidCustomerIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divCustomerGrid">
 <%
 	Customer customerObj = new Customer(); 
 	out.print(customerObj.readCustomer()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>