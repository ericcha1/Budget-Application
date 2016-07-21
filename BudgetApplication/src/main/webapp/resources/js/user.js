$(document).ready(function()
{ 
	//Apply tablesorter here  
	fillUserTable();
	$("#userTable").tablesorter();
}); 

function fillUserTable()
{	
	//ajax call
	$.ajax({
		url: '/BudgetApplication/users',
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = '';
			
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.id + '</td><td>' + val.username 
				+ '</td><td>' + val.password + '</td><td>' + val.name
				+ '</td><td>' + val.role + '</td><td>' + val.enabled + "</td></tr>";
			});
			
			$('#userTable tbody').empty().append(html);
			$('#userTable').trigger('update');
		},
		error: function(error){
	        console.log(error);
	 }
	});
}

