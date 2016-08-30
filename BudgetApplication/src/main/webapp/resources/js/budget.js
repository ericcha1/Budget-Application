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
    $("#limitTable").delegate("tr.rows", "click", function()
    {
    	//disable add and enable delete/modify
    	addButton.disabled = true;
    	delButton.disabled = false;
    	modButton.disabled = false;
    	
    	//store the row's information
    	var bCategory = $("td.limitCategory", this).html();
    	var bAmount = $("td.limitAmount", this).html().replace("$", "");
    	currentId = $("td.limitId", this).html();
    	
    	//set the fields to the row's data
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
	
	$("#buttons").submit(function(e) 
	{
		clearForm();
    	
    	//reset defaults
    	addButton.disabled = false;
    	delButton.disabled = true;
    	modButton.disabled = true;
    	currentId = 0;
		
		//prevent normal submission and display success/fail alerts
		e.preventDefault(); 
	});
	
	$("#dateForm").submit(function(e) 
	{		
		var startDate = document.getElementsByName("startDate")[0].value;
		var endDate = document.getElementsByName("endDate")[0].value;
		
		//check start comes before end
		if (!compareDates(startDate, endDate))
		{
			showAlert("ERROR: Make sure the start date comes before the end date.", "#f44336");
		}
		else
		{
			//create new budget
			startBudget();
		}
		
		//prevent normal submission and display success/fail alerts
		e.preventDefault(); 
	});
	
	$("#delete").click(function(e) 
	{
		clearForm();
    	
    	//reset defaults
    	addButton.disabled = false;
    	delButton.disabled = true;
    	modButton.disabled = true;
    	currentId = 0;
	});
	
	//when focus is lost from the amount field, set to have two decimal places
	document.getElementsByName("amountField")[0].onblur = function ()
	{    
	    this.value = parseFloat(this.value).toFixed(2);
	}
	
	//table and input setup
    fillCategories();
	fillLimitTable();
	$("#limitTable").tablesorter();
	
	//disable start button if budget is active
	checkActive();
}); 

function fillLimitTable()
{	
	$.ajax({
		url: '/BudgetApplication/limits', //see URLs in LimitController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr class="rows"><td class="limitId">' + val.id +
				'</td><td class="limitCategory">' + val.category + 
				'</td><td class="limitAmount">$' + val.amount.toFixed(2) + 
				'</td><td>' + val.insertedBy + '</td><td>' + val.insertedOn + '</td></tr>';
			});
			
			//clear table body, then update
			$('#limitTable tbody').empty().append(html);
			$('#limitTable').trigger('update');

		},
		error: function(error)
		{
	        console.log(error);
		}
	});
}

function fillCategories()
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
		error: function(error)
		{
	        console.log(error);
		}
	});
}

function addLimit()
{
	//store the elements that were filled in
	var category = document.getElementById("categories").value;
	var amount = document.getElementsByName("amountField")[0].value;
	
	//check for empty strings
	if(category == "")
	{
		showAlert("ERROR: Limit could not be added. Please fill in every value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/addLimit",
        type:"POST",
        data: {category: category, amount: amount},
        success:function(data)
        {
        	//refill table
        	fillLimitTable();
        	showAlert("Limit successfully added.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Limit could not be added.", "#f44336");
		}
    });
}

function deleteLimit()
{
    $.ajax({
    	url: "/BudgetApplication/deleteLimit",
        type: "POST",
        //currentId is the id of the row that was last clicked
        data: {id: currentId},
        success:function(data)
        {
        	//refill table
        	fillLimitTable();
        	showAlert("Limit successfully deleted.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Limit could not be deleted.", "#f44336");
		}
    });
}

function editLimit()
{
	//store the elements that were filled in
	var category = document.getElementById("categories").value;
	var amount = document.getElementsByName("amountField")[0].value;
	
	//check for empty string
	if(category == "")
	{
		showAlert("ERROR: Category could not be added. Please fill in an appropriate value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/editLimit",
        type: "GET",
        //currentId is the id of the row that was last clicked
        data: {id: currentId, category: category, amount: amount},
        success:function(data)
        {	
        	fillLimitTable();
        	showAlert("Limit successfully edited.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Limit could not be edited.", "#f44336");
		}
    });
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
	document.getElementById("categories").value = "";
	document.getElementsByName("amountField")[0].value = "";
}

function startBudget()
{	
	var start = document.getElementsByName("startDate")[0].value;
	var end = document.getElementsByName("endDate")[0].value;

    $.ajax({
    	url: "/BudgetApplication/startBudget",
        type:"POST",
        data: {startDate: start, endDate: end},
        success:function(data)
        {
        	document.getElementsByName("startDate")[0].disabled = true;
        	document.getElementsByName("endDate")[0].disabled = true;
        	document.getElementById("start").disabled = true;
        	showAlert("Budget successfully started.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Budget could not be started.", "#f44336");
		}
    });
	
}

function compareDates(start, end)
{
    //parse the dates
    var startParts = start.split("-");
    var startDay = parseInt(startParts[2]);
    var startMonth = parseInt(startParts[1]);
    var startYear = parseInt(startParts[0]);
    
    var endParts = end.split("-");
    var endDay = parseInt(endParts[2]);
    var endMonth = parseInt(endParts[1]);
    var endYear = parseInt(endParts[0]);
    
    //check start comes before end
    if (startYear == endYear)
    {
    	if (startMonth == endMonth)
    		{ return startDay < endDay; }
    	else
    		{ return startMonth < endMonth; }
    }
    
    return startYear < endYear;
};

function checkActive()
{
	$.ajax({
    	url: "/BudgetApplication/activeBudget",
        type:"GET",
        success:function(active)
        {
        	alert(active);
        	if (active)
        	{
        		//disable button and input when budget is still active
            	document.getElementsByName("startDate")[0].disabled = true;
            	document.getElementsByName("endDate")[0].disabled = true;
            	document.getElementById("start").disabled = true;
        	}
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Failed to retrieve status of budget.", "#f44336");
		}
    });
}