
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================//customer_id`,`first_name`,`last_name`,`email`,`username`, `password`,`telephone_n
$(document).on("click", ".btnUpdate", function(event) 
{ 
	console.log("Update",$("#hidCustomerIDSave").val());
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
	$("#hidCustomerIDSave").val($(this).data("customerid"));
 $("#CustomerName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#Address").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#MobileNo").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#Email").val($(this).closest("tr").find('td:eq(3)').text());
 $("#AccNumber").val($(this).closest("tr").find('td:eq(4)').text());
 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : "DELETE", 
 data : "CustomerId=" + $(this).data("customerid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateCustomerForm() 
{ 

// CustomerName Name
if ($("#CustomerName").val().trim() == "") 
 { 
 return "Insert CustomerName."; 
 } 
// Address-------------------------------
if ($("#Address").val().trim() == "") 
 { 
 return "Insert Address."; 
 } 
 // Email
 if ($("#Email").val().trim() == "") 
 { 
 return "Insert Email."; 
 } 
 //AccNumber
 if ($("#AccNumber").val().trim() == "") 
 { 
 return "Insert AccNumber."; 
 }
// is numerical value
var telephone = $("#MobileNo").val().trim(); 
if (!$.isNumeric(telephone)) 
 { 
 return "Insert a numerical value for telephone number."; 
 }  
return true; 
}

function onCustomerSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidCustomerIDSave").val(""); 
 $("#formCustomer")[0].reset(); 
}


function onCustomerDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCustomerGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




