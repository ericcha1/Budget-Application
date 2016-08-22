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
    	var fillEmail = $("td.email", this).html();
    	currentUser = $("td.username", this).html();
    	
    	//set the fields to the row's data
    	document.getElementsByName("usernameField")[0].value = fillUser;
    	document.getElementsByName("passwordField")[0].value = fillPass;
    	document.getElementsByName("nameField")[0].value = fillName;
    	document.getElementsByName("roleField")[0].value = fillRole;
    	document.getElementsByName("enabledField")[0].value = fillEnabled;
    	document.getElementsByName("emailField")[0].value = fillEmail;
    	
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
	
    $("#buttons").submit(function(e) 
	{
    	//reset page
		clearForm();
    	addButton.disabled = false;
    	delButton.disabled = true;
    	modButton.disabled = true;
    	currentUser = "";
    	
    	//prevent normal submission and display success/fail alerts
		e.preventDefault();
	});
    
    $("#delete").click(function(e) 
	{
    	//reset page
		clearForm();
    	addButton.disabled = false;
    	delButton.disabled = true;
    	modButton.disabled = true;
    	currentUser = "";
	});
    
	//table setup 
	fillUserTable();
	$("#userTable").tablesorter();
});

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
				html += '<tr class="rows"><td class="username">' + val.username 
				+ '</td><td class="password">' + val.password 
				+ '</td><td class="name">' + val.name + '</td><td class="role">'
				+ val.role + '</td><td class="enabled">' + val.enabled 
				+ '</td><td class="email">' + val.email + '</td><td>' 
				+ val.insertedBy + '</td><td>' + val.insertedOn + "</td></tr>";
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
	var email = document.getElementsByName("emailField")[0].value;
	
	//check for empty strings
	if(username == "" || password == "" || name == "" || role == "" || enabled == "" || email == "")
	{
		showAlert("ERROR: Category could not be added. Please fill in every value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/addUser",
        type:"POST",
        data: {username: username, password: password, name: name, 
        	role: role, enabled: enabled, email: email},
        success:function(data)
        {
        	//refill the table
        	fillUserTable();
        	showAlert("Entry successfully added.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be added.", "#f44336");
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
        	showAlert("Entry successfully deleted.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be deleted.", "#f44336");
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
	var email = document.getElementsByName("emailField")[0].value;
	
	//check for empty string
	if(username == "" || password == "" || name == "" || role == "" || enabled == "" || email == "")
	{
		showAlert("ERROR: Category could not be added. Please fill in every value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/editUser",
        type: "GET",
        //currentId is the id of the row that was last clicked
        data: {username: currentUser, password: password, name: name, 
        	role: role, enabled: enabled, email: email},
       	success:function(data)
        {
        	//refill table
        	fillUserTable();
        	showAlert("Entry successfully edited.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Entry could not be edited.", "#f44336");
		}
    });
}

function showAlert(msg, color)
{
	//set color and message for alert
	var customAlert = document.getElementById("alert");
	customAlert.style.backgroundColor = color;
	$('#message').empty().append(msg);
	$('#message').trigger('update');
	
	//make alert visible
	customAlert.style.display = "block";
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
	document.getElementsByName("emailField")[0].value = "";
}