$(document).ready(function()
{ 
	//call function  
	loadUser();
	$("#userTable").tablesorter();
}); 

function loadUser()
{
	//ajax call
	$.ajax({
		url: '/BudgetApplication/currentData', //see URL in BudgetController.java
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

function fillUserTable()
{	
	//ajax call
	$.ajax({
		url: '/BudgetApplication/userData', //see URL in UserController.java
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

function searchUser()
{
	//store the element that was filled in
	var search = document.getElementsByName("searchField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/userSearch",
        type: "GET",
        data: {search: search},
        success:function(response)
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
		error: function(error)
		{
	        console.log(error);
		}
    });
}

function empty()
{
	$('#userTable tbody').empty();
	$('#userTable').trigger('update');
}