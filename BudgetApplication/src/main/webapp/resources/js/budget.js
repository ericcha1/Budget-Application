var currentId = 0;	

$(document).ready(function()
{
	//setting default availability of buttons
	var addButton = document.getElementById("add");
	var delButton = document.getElementById("delete");
	var modButton = document.getElementById("edit");
	addButton.disabled = false;
	delButton.disabled = true;
	modButton.disabled = true;
	
	//clicking a row will fill the fields above the table with the row's information
    $("#budgetTable").delegate("tr.rows", "click", function()
    {
    	//disable add and enable delete/modify
    	addButton.disabled = true;
    	delButton.disabled = false;
    	modButton.disabled = false;
    	
    	//store the row's information
    	var bUsername = $("td.budgetUsername", this).html();
    	var bCategory = $("td.budgetCategory", this).html();
    	var bAmount = $("td.budgetAmount", this).html().replace("$", "");
    	currentId = $("td.budgetId", this).html();
    	
    	//set the fields to the row's data
    	document.getElementsByName("usernameField")[0].value = bUsername;
    	document.getElementById("categories").value = bCategory;
    	document.getElementsByName("amountField")[0].value = bAmount;
    	
    	//scroll to top
    	window.scrollTo(0,0);
    });
    
    $("#clear").click(function()
    {
    	clearForm();
    	
    	//reset defaults
    	addButton.disabled = false;
    	delButton.disabled = true;
    	modButton.disabled = true;
    	currentId = 0;
    });

	//table and input setup
    addCategories();
	fillBudgetTable();
	$("#budgetTable").tablesorter();
	
	$("#buttons").submit(function(e) 
	{
		//need to check the string input because empty string is valid
		//if(document.getElementsByName("categoryField")[0].value == "")
			
		e.preventDefault(); 
		  
		  //e.stopPropagation(); 
		  // only necessary if something above is listening to the (default-)event too
	});
	
	document.getElementsByName("amountField")[0].onblur = function ()
	{    
		//when focus is lost from the amount field input, switch it to have two decimal places
	    this.value = parseFloat(this.value).toFixed(2);
	}
}); 

function fillBudgetTable()
{	
	$.ajax({
		url: '/BudgetApplication/entries', //see URL in BudgetController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr class="rows"><td class="budgetId">' + val.id + 
				'</td><td class="budgetUsername">' + val.username + 
				'</td><td class="budgetCategory">' + val.category + 
				'</td><td class="budgetAmount">$' + val.amount.toFixed(2) + 
				'</td><td>' + val.insertedBy + '</td><td>' + val.insertedOn + "</td></tr>";
			});
			
			//clear table body, then update
			$('#budgetTable tbody').empty().append(html);
			$('#budgetTable').trigger('update');

		},
		error: function(error){
	        console.log(error);
		}
	});
}

function addCategories()
{
	$.ajax({
		url: '/BudgetApplication/categories',
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//add options for each category
			$.each(response, function (key, val)
			{
				html += '<option value="' + val.category + '">' 
				+ val.category + '</option>';
			});
			
			//clear category options, then update
			$('#categories').empty().append(html);
			$('#categories').trigger('update');
		},
		error: function(error){
	        console.log(error);
		}
	});
}

function addEntry()
{
	//store the elements that were filled in
	var username = document.getElementsByName("usernameField")[0].value;
	var category = document.getElementById("categories").value;
	var amount = document.getElementsByName("amountField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/addEntry",
        type:"POST",
        data: {username: username, category: category, amount: amount},
        success:function(data)
        {
        	//refill table
        	fillBudgetTable();
        	clearForm();
        	showAlert("Entry successfully added.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be added.", "#f44336");
		}
    });
}

function deleteEntry()
{
    $.ajax({
    	url: "/BudgetApplication/deleteEntry",
        type: "POST",
        //currentId is the id of the row that was last clicked
        data: {id: currentId},
        success:function(data)
        {
        	//refill table
        	fillBudgetTable();
        	clearForm();
        	showAlert("Entry successfully deleted.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be deleted.", "#f44336");
		}
    });
}

function editEntry()
{
	//store the elements that were filled in
	var username = document.getElementsByName("usernameField")[0].value;
	var category = document.getElementById("categories").value;
	var amount = document.getElementsByName("amountField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/editEntry",
        type: "GET",
        //currentId is the id of the row that was last clicked
        data: {id: currentId, username: username, category: category, amount: amount},
        success:function(data)
        {	
        	fillBudgetTable();
        	clearForm();
        	showAlert("Entry successfully edited.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be edited.", "#f44336");
		}
    });
//    showAlert();
}

function showAlert(msg, color)
{
	//set color and message for alert
	var customAlert = document.getElementById("alert");
	customAlert.style.backgroundColor = color;
	$('#message').empty().append(msg);
	$('#message').trigger('update');
	
	//make alert visible
	customAlert.style.display = "block";
}

function clearForm()
{
	//reset id
	currentId = 0;
	
	//empty all fields
	document.getElementsByName("usernameField")[0].value = "";
	document.getElementById("categories").value = "";
	document.getElementsByName("amountField")[0].value = "";
}