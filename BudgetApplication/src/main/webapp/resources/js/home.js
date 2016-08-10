$(document).ready(function()
{ 
	
	// Close the dropdown if the user clicks outside of it
	window.onclick = function(e) 
	{
		if (!e.target.matches(".dropButton")) 
		{
			var dropdowns = document.getElementsByClassName("dropdown-content");
			
			for (var d = 0; d < dropdowns.length; d++) 
			{
				var openDropdown = dropdowns[d];
				
				if (openDropdown.classList.contains('show')) 
				{
					openDropdown.classList.remove('show');
				}
			}
		}
	}
}); 

//When the user clicks on the Admin button, toggle dropdown content
function showAdminMenu() 
{
    document.getElementById("adminMenu").classList.toggle("show");
}

function getTotal()
{
	//ajax call
	$.ajax({
		url: '/BudgetApplication/entries', //see URL in BudgetController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var total = 0; //stores the string to be returned
			
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