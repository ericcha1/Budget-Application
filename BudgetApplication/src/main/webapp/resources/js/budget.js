$(document).ready(function()
{ 
	//call function  
	fillBudgetTable();
	$("#budgetTable").tablesorter();
}); 

function fillBudgetTable()
{	
	//ajax call
	$.ajax({
		url: '/BudgetApplication/entries', //see URL in UserController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.id + '</td><td>' + val.username 
				+ '</td><td>' + val.category + '</td><td>' + val.amount + "</td></tr>";
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

