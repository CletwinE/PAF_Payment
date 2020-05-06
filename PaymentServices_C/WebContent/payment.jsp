<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Beans.PaymentBean"%>
<%@page import="DB_Config.DBConnection"%>
<%@page import="Model.Payment"%>
<%@page import="Service.PaymentService"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>
	<div>
		<div class="container">
			<div class="row">
				<div class="col-6">
					<h1>Payment Details</h1>
					<p>Please fill in this form to payment details.</p>
					<form id="formPayment" name="formPayment" style="font-weight: bold">
						<br> Pay CardNo: <input id="payCardNo" name="payCardNo" 
						type="text" placeholder="Enter the card number" 
						class="form-control form-control-sm"> 
						<br> Pay UserName: <input id="payName" name="payName" 
						type="text" placeholder="Enter the user name" 
						class="form-control form-control-sm"> 
						<br> Pay Date: <input id="payDate" name="payDate" 
						type="text" placeholder="Enter the date" 
						class="form-control form-control-sm">
						<br> Pay Amount: <input id="payAmount" name="payAmount" 
						type="text" placeholder="Enter the amount"
						 class="form-control form-control-sm"> 
						<br> 
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
						<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
					</form>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divPaymentGrid">
						<%
							Payment payObj = new Payment();
							out.print(payObj.readPayment());
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
