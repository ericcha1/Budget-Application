var currentUser = "";

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
    $("#userTable").delegate("tr.rows", "click", function()
    {
    	//disable add and enable delete/modify
    	addButton.disabled = true;
    	delButton.disabled = false;
    	modButton.disabled = false;
    	
    	//store the row's information
    	var fillUser = $("td.username", this).html();
    	var fillPass = $("td.password", this).html();
    	var fillName = $("td.name", this).html();
    	var fillRole = $("td.role", this).html();
    	var fillEnabled = $("td.enabled", this).html();
    	currentUser = $("td.username", this).html();
    	
    	//set the fields to the row's data
    	document.getElementsByName("usernameField")[0].value = fillUser;
    	document.getElementsByName("passwordField")[0].value = fillPass;
    	document.getElementsByName("nameField")[0].value = fillName;
    	document.getElementsByName("roleField")[0].value = fillRole;
    	document.getElementsByName("enabledField")[0].value = fillEnabled;
    	
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
    	currentUser = "";
    });
	
	//call function  
	fillUserTable();
	$("#userTable").tablesorter();
});

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
				html += '<tr class="rows"><td class="username">' + val.username 
				+ '</td><td class="password">' + val.password 
				+ '</td><td class="name">' + val.name + '</td><td class="role">'
				+ val.role + '</td><td class="enabled">' + val.enabled + '</td></tr>';
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

function addUser()
{
	//store the elements that were filled in
	var username = document.getElementsByName("usernameField")[0].value;
	var password = document.getElementsByName("passwordField")[0].value;
	var name = document.getElementsByName("nameField")[0].value;
	var role = document.getElementsByName("roleField")[0].value;
	var enabled = document.getElementsByName("enabledField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/addUser",
        type:"POST",
        data: {username: username, password: password, name:name, role:role, enabled:enabled},
        success:function(data)
        {
        	//refill the table
        	fillUserTable();
		},
		error: function(error)
		{
	        console.log(error);
		}
    });
}

function deleteUser()
{
    $.ajax({
    	url: "/BudgetApplication/deleteUser",
        type: "POST",
        //currentUser is the username of the row that was last clicked
        data: {username: currentUser},
        success:function(data)
        {
        	//refill table
        	fillUserTable();
		},
		error: function(error)
		{
	        console.log(error);
		}
    });
}

function editUser()
{
	//store the elements that were filled in
	var username = document.getElementsByName("usernameField")[0].value;
	var password = document.getElementsByName("passwordField")[0].value;
	var name = document.getElementsByName("nameField")[0].value;
	var role = document.getElementsByName("roleField")[0].value;
	var enabled = document.getElementsByName("enabledField")[0].value;
	
    $.ajax({
    	url: "/BudgetApplication/editUser",
        type: "GET",
        //currentId is the id of the row that was last clicked
        data: {username: currentUser, password: password, name:name, role:role, enabled:enabled},
       	success:function(data)
        {
        	//refill table
        	fillUserTable();
		},
		error: function(error)
		{
	        console.log(error);
		}
    });
}

function clearForm()
{
	//reset current user
	currentUser = "";
	
	//empty all fields
	document.getElementsByName("usernameField")[0].value = "";
	document.getElementsByName("passwordField")[0].value = "";
	document.getElementsByName("nameField")[0].value = "";
	document.getElementsByName("roleField")[0].value = "";
	document.getElementsByName("enabledField")[0].value = "";
}