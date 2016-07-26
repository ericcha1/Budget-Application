$(document).ready(function()
{ 
    $("#budgetTable").delegate("tr.rows", "click", function()
    {
    	var bUsername = $("td.budgetUsername",this).html();
    	var bCategory = $("td.budgetCategory",this).html();
    	var bAmount = $("td.budgetAmount",this).html();
    	
    	document.getElementsByName("UsernameField")[0].value = bUsername;
    	document.getElementsByName("CategoryField")[0].value = bCategory;
    	document.getElementsByName("AmountField")[0].value = bAmount;
    	
    	window.scrollTo(0,0);
    });
	//call function  
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

//function autoFill()
//{
//	var bUsername = $("td.budgetUsername",this).html();
//	var bCategory = $("td.budgetCategory",this).html();
//	var bAmount = $("td.budgetAmount",this).html();
//	
//	document.getElementsByName("UsernameField")[0].value = bUsername;
//	document.getElementsByName("CategoryField")[0].value = bCategory;
//	document.getElementsByName("AmountField")[0].value = bAmount;
//}