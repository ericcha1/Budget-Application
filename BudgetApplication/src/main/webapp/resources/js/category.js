var currentCategory = "";
var addButton;
var saveButton;

$(document).ready(function()
{
	//setting default availability of buttons
	addButton = document.getElementById("add");
	saveButton = document.getElementById("save");
	
	addButton.disabled = false;
	saveButton.disabled = true;
	
    $("#clear").click(function()
    {
    	clearForm();
    	
    	//reset defaults
    	addButton.disabled = false;
    	saveButton.disabled = true;
    	currentCategory = "";
    });
	
	//submit allows required tag to work in html
	$("#buttons").submit(function(e) 
	{
		//prevent normal submission and display success/fail alerts
		e.preventDefault();
	});
	
	//table setup
	fillCategoryTable();
	$("#categoryTable").tablesorter();
});

function fillCategoryTable()
{	
	$.ajax({
		url: '/BudgetApplication/categories',
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr class="rows"><td>' + val.category + '</td><td>' 
				+ val.insertedBy + '</td><td>' +val.insertedOn 
				+ '</td><td><button type="button" onclick="editCategory(\'' 
				+ val.category + '\')">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;' 
				+ '<button type="button" onclick="deleteCategory(\'' 
				+ val.category + '\')">Delete</button></td></tr>';
			});
			
			//clear table body, then update
			$('#categoryTable tbody').empty().append(html);
			$('#categoryTable').trigger('update');

		},
		error: function(error){
	        console.log(error);
		}
	});
}

function addCategory()
{
	//store the text that was filled in
	var category = document.getElementsByName("categoryField")[0].value;
	
	//check for empty string
	if(category == "")
	{
		showAlert("ERROR: Category could not be added. Please fill in an appropriate value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/addCategory",
        type:"POST",
        data: {category: category},
        success:function(data)
        {
        	//refill table
        	fillCategoryTable();
        	clearForm();
        	showAlert("Category successfully added.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Category could not be added.", "#f44336");
		}
    });
}

function deleteCategory(delCat)
{
	currentCategory = delCat;
	
    $.ajax({
    	url: "/BudgetApplication/deleteCategory",
        type: "POST",
        data: {category: currentCategory},
        success:function(data)
        {
        	//refill table
        	fillCategoryTable();
        	clearForm();
        	showAlert("Category successfully deleted.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Category could not be deleted.", "#f44336");
		}
    });
}

function editCategory(editCat)
{
	//disable add and enable save
	addButton.disabled = true;
	saveButton.disabled = false;
	
	//store the row's information
	currentCategory = editCat;
	
	//set the fields to the row's data
	document.getElementsByName("categoryField")[0].value = editCat;
	
	//scroll to top
	window.scrollTo(0,0);
}

function saveCategory()
{
	//store the text that was filled in
	var category = document.getElementsByName("categoryField")[0].value;
	
	//check for empty string
	if(category == "")
	{
		showAlert("ERROR: Category could not be added. Please fill in an appropriate value.", "#f44336");
		return;
	}
	
    $.ajax({
    	url: "/BudgetApplication/editCategory",
        type: "GET",
        //currentCategory is the one being modified to category
        data: {initialCategory: currentCategory, category: category},
        success:function(data)
        {	
        	fillCategoryTable();
        	clearForm();
        	showAlert("Category successfully edited.", "#4CAF50");
		},
		error: function(error)
		{
	        console.log(error);
	        showAlert("ERROR: Category could not be edited.", "#f44336");
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
	//reset buttons and category
	addButton.disabled = false;
	saveButton.disabled = true;
	currentCategory = "";
	
	//empty input field
	document.getElementsByName("categoryField")[0].value = "";
}
