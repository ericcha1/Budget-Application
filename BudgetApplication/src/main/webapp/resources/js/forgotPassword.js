function resetPass()
{
	//store the entered in email address
	var userEmail = document.getElementsByName("emailField")[0].value;
	
	$.ajax({
		url: '/BudgetApplication/sendEmail',
		type: 'GET',
		data: {email: userEmail},
		success: function (response)
		{
			console.log(response);
			
			//replace the current window to include the returned message
			var myWindow = window.open("forgotPassword?message=" + response, "_self");
		},
		error: function(data)
		{
			console.log(data);
		}
	});

}