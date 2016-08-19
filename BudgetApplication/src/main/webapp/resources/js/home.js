$(document).ready(function()
{ 
	getRecent();
}); 

function getRecent()
{
	$.ajax({
		url: '/BudgetApplication/recent', //see HomeController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ""; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.category + '</td><td>$' + val.amount.toFixed(2)
				+ '</td><td>' + val.insertedOn + "</td></tr>";
			});
			
			if (html != "")
			{
				//clear table body, then update
				$('#recentTable tbody').empty().append(html);
				$('#recentTable').trigger('update');
			}
			else
			{
				$('#recentTable thead').empty();
				$('#message').empty().append("No entries added yet!");
				document.getElementById("recentTable").style.display = "none";
			}
		},
		error: function(error)
		{
	        console.log(error);
		}
	});
}