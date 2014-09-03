<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="manageInventory.Item" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 	<%@ page isELIgnored="false"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
 function submit(){
		if(!itemForm.itemName.value){
			invalidQuantity.style.display="none";
			noItemName.style.display='block';
			successfulAdd.style.display="none";
            duplicateItem.style.display="none";
			return false;
		}
		if(isNaN(itemForm.itemQty.value) == true || itemForm.itemQty.value <= 0){
			noItemName.style.display='none';
			invalidQuantity.style.display='block';
			successfulAdd.style.display="none";
            duplicateItem.style.display="none";
			return false;
		}
		
	 	$.ajax({
 		type: "POST",
 		dataType: "json",
		url: "http://localhost:8080/manageInventory/viewItems",
		data: $('#itemForm').serialize(),
     	success: function(msg){
     		if(msg == true) {
     			noItemName.style.display='none';
    			invalidQuantity.style.display='none';
                successfulAdd.style.display="block";
                duplicateItem.style.display="none";
   		 	} else {
   		 	noItemName.style.display='none';
			invalidQuantity.style.display='none';
            successfulAdd.style.display="none";
   		 	duplicateItem.style.display="block";
   		 	}
		},
		error: function(){
			
			$("#resultContainer").html("<div class='alert alert-danger'>Server Error! Item not added, please check your input</div>");
			}
   		});
 } 
 function refreshPage(){
	    window.location.reload();
	}
 function myReset() {
  itemForm.reset();
noItemName.style.display='none';
invalidQuantity.style.display='none';
successfulAdd.style.display="none";
duplicateItem.style.display="none";
}
</script>

<title>Inventory Management System</title>
</head>
<body>


	<a href="#basicModal" class="btn btn-large btn-success" data-toggle="modal"
		data-target="#basicModal" data-backdrop="static">Add Item</a>

	<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div id="resultContainer">
					<div class="alert alert-danger" id="noItemName" style="display:none;">
					    <strong>Error!</strong> Item name cannot be empty
					</div>
					<div class="alert alert-danger" id="invalidQuantity" style="display:none;">
					    <strong>Error!</strong> Item Quantity should be a positive integer
					</div>
					<div id="successfulAdd" style="display:none;" class='alert alert-success'>Item added successfully</div>
					<div id="duplicateItem" style="display:none;" class='alert alert-danger'>Item already exists in inventory</div>
					</div>
					<h3 class="modal-title" id="myModalLabel">Add Item</h3>
				</div>
				<div class="modal-body">
				<form id= "itemForm" name="itemForm" method="post">
					<p>Enter Item Name:</p>
					<input type="text" name="itemName" id="itemName"  />
					<p>Enter Item Quantity:</p>
					<input type="text" name="itemQty" id="itemQty"  />
				</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary" value="Reset" onClick="myReset()" />
					<button type="button" class="btn btn-success" onclick="submit()">Add</button>
					<button type="button" class="btn btn-danger" onclick="refreshPage()" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	
<table class = "table table-hover" >
<tbody>
<tr><th>ID<th>Name</th><th>Quantity</th></tr>
<c:forEach items="${requestScope.Items}" var="item" varStatus="loopCounter">
<tr class="${item.quantity == 0 ? 'danger' : ''} "><td><c:out value="${loopCounter.index +1} "></c:out></td>
<td><c:out value="${item.name}"></c:out></td>
<td><c:out value="${item.quantity}"></c:out></td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>