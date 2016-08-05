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
