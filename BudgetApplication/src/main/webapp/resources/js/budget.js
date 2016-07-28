var currentId = 0;	

$(document).ready(function()
{ 
	//clicking a row will fill the fields above the table with the row's information
    $("#budgetTable").delegate("tr.rows", "click", function()
    {
    	//store the row's information
    	var bUsername = $("td.budgetUsername", this).html();
    	var bCategory = $("td.budgetCategory", this).html();
    	var bAmount = $("td.budgetAmount", this).html();
    	
    	//set the fields to the row's data
    	document.getElementsByName("usernameField")[0].value = bUsername;
    	document.getElementsByName("categoryField")[0].value = bCategory;
    	document.getElementsByName("amountField")[0].value = bAmount;
    	currentId = $("td.budgetId", this).html();
    	
    	//scroll to top
    	window.scrollTo(0,0);
    });

	//fill table and apply tablesorter
	fillBudgetTable();
	$("#budgetTable").tablesorter();

}); 

function fillBudgetTable()
{	
	//ajax call
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
				'</td><td class="budgetAmount">' + val.amount + "</td></tr>";
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

function addEntry()
{
	//store the elements that were filled in
	var bUsername = document.getElementsByName("usernameField")[0].value;
	var bCategory = document.getElementsByName("categoryField")[0].value;
	var bAmount = document.getElementsByName("amountField")[0].value;

    $.ajax({
    	url: "/BudgetApplication/saveEntry",
        type:"POST",
        data: {username: bUsername, category: bCategory, amount: bAmount},
        success:function(data)
        {
        	//refill the table
        	fillBudgetTable();
		},
		error: function(error)
		{
	        console.log(error);
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
		},
		error: function(error)
		{
	        console.log(error);
		}
    });
}

function editEntry()
{
	//store the elements that were filled in
	var bUsername = document.getElementsByName("usernameField")[0].value;
	var bCategory = document.getElementsByName("categoryField")[0].value;
	var bAmount = document.getElementsByName("amountField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/editEntry",
        type: "GET",
        //currentId is the id of the row that was last clicked
        data: {id: currentId, username: bUsername, category: bCategory, amount: bAmount},
        success:function(data)
        {
        	//refill table
        	fillBudgetTable();
		},
		error: function(error)
		{
	        console.log(error);
		}
    });
}