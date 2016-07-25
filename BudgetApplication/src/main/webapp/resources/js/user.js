$(document).ready(function()
{ 
	//call function  
	fillUserTable();
	$("#userTable").tablesorter();
}); 

function fillUserTable()
{	
	//ajax call
	$.ajax({
		url: '/BudgetApplication/users', //see URL in UserController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each user in the table
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.id + '</td><td>' + val.username 
				+ '</td><td>' + val.password + '</td><td>' + val.name
				+ '</td><td>' + val.role + '</td><td>' + val.enabled + "</td></tr>";
			});
			
			//clear table body, then update
			$('#userTable tbody').empty().append(html);
			$('#userTable').trigger('update');
		},
		error: function(error){
	        console.log(error);
	 }
	});
}

