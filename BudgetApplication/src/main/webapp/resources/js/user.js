$(document).ready(function()
{ 
	//load only the current user 
	loadUser();
	$("#userTable").tablesorter();
}); 

function loadUser()
{
	$.ajax({
		url: '/BudgetApplication/currentData', //see URL in UserController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.username + '</td><td>'
				+ val.name + '</td><td>' + val.role + '</td><td>' 
				+ val.enabled + '</td><td>' + val.email + '</td><td>' 
				+ val.insertedBy + '</td><td>' + val.insertedOn + '</td></tr>';
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
				html += '<tr><td>' + val.username + '</td><td>'
				+ val.name + '</td><td>' + val.role + '</td><td>' 
				+ val.enabled + '</td><td>' + val.email + '</td><td>' 
				+ val.insertedBy + '</td><td>' + val.insertedOn + '</td></tr>';
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
				html += '<tr><td>' + val.username + '</td><td>'
				+ val.name + '</td><td>' + val.role + '</td><td>' 
				+ val.enabled + '</td><td>' + val.email + '</td><td>' 
				+ val.insertedBy + '</td><td>' + val.insertedOn + '</td></tr>';
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