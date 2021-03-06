$(document).ready(function () {
	$("#alertSuccess").hide();

	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();


	//Form validation-------------------
	var status = validatePaymentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}


	// If valid-------------------------

	//$("#formPayment").submit();

	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "PaymentService",
			type: type,
			data: $("#formPayment").serialize(),
			dataType: "text",
			complete: function (response, status) {
				onPaymentSaveComplete(response.responseText, status);
			}
		});


});




function onPaymentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();

		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("UnKnown error while saving..");
		$("#alertError").show();
	}

	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();

}

//UPDATE==========================================
$(document).on(
	"click", 
	".btnUpdate", 
	function (event) {
	$("#hidPaymentIDSave").val(
		$(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#payCardNo").val($(this).closest("tr").find('td:eq(0)').text());
	$("#payName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#payDate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#payAmount").val($(this).closest("tr").find('td:eq(3)').text());
});

// REMOVE=========================================================
$(document).on("click", ".btnRemove", function (event) {
	$.ajax(
		{
			url: "PaymentService",
			type: "DELETE",
			data: "PayID=" + $(this).data("payid"),
			dataType: "text",
			complete: function (response, status) {
				onPaymentDeleteComplete(response.responseText, status);
			}
		});
});

function onPaymentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Delete.");
			$("#alertSuccess").show();

			$("#divPaymentGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();

		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("UnKnown error while deleting..");
		$("#alertError").show();
	}

	$("#hidPaymentIDSave").val("");
	$("#formPayment")[0].reset();

}

//CLIENT-MODEL================================================================
function validatePaymentForm() {

// card no
	if ($("#payCardNo").val().trim() == "") {
		return "Insert card no.";
	}
	// is numerical value
	var tempCardNo = $("#payCardNo").val().trim();
	if (!$.isNumeric(tempCardNo)) {
		return "Insert a numerical value for Card.";
	}

	// name
	if ($("#payName").val().trim() == "") {
		return "Insert name.";
	}

	// date
	if ($("#payDate").val().trim() == "") {
		return "Insert date.";
	}

	// amount
	if ($("#payAmount").val().trim() == "") {
		return "Insert amount.";
	}

	// is numerical value
	var tmpPrice = $("#payAmount").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for amount.";
	}

	// convert to decimal amount
	$("#payAmount").val(parseFloat(tmpPrice).toFixed(2));

	

	return true;
}



