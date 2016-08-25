$(document).ready(function()
{
	addDates();
}); 

function fillDurationTable()
{	
	var id = document.getElementById("dates").value;
	
	$.ajax({
		url: '/BudgetApplication/dateEntries',
		type: 'GET',
		dataType : 'json',
		data: {id: id},
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr class="rows"><td class="budgetId">' + val.id +
				'</td><td class="budgetCategory">' + val.category + 
				'</td><td class="budgetAmount">$' + val.amount.toFixed(2) + 
				'</td><td>' + val.insertedBy + '</td><td>' + val.insertedOn + '</td></tr>';
			});
			
			//clear table body, then update
			$('#durationTable tbody').empty().append(html);
			$('#durationTable').trigger('update');

		},
		error: function(error){
	        console.log(error);
		}
	});
}

function addDates()
{
	$.ajax({
		url: '/BudgetApplication/dates',
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ''; //stores the string to be returned
			
			//add options for each category
			$.each(response, function (key, val)
			{
				html += '<option value="' + val.id + '">' 
				+ val.startDate + ' to ' + val.endDate + '</option>';
			});
			
			//clear category options, then update
			$('#dates').empty().append(html);
			$('#dates').trigger('update');
		},
		error: function(error){
	        console.log(error);
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