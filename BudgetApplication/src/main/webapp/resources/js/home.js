$(document).ready(function()
{ 
	
	//retrieve the categories
	$.ajax({
		url: '/BudgetApplication/categories',
		type: 'GET',
		dataType : 'json',
		success: function (categories)
		{
			console.log(categories);
			var labels = new Map(); //map a category to an index, to reduce searching
			
			//store total spending in each category
			//use the number received from searching the map to index into this array
			//initialize to all 0
			var totals = new Array(categories.length).fill(0);
			
			var numCat = categories.length; //amount of categories
			
			//insert into map
			$.each(categories, function (key, val)
			{
				labels.set(val.category, key);
			});
			//console.log(labels);
			
			//retrieve the spending data
			$.ajax({
				url: '/BudgetApplication/budgetEntries',
				type: 'GET',
				dataType : 'json',
				success: function (data)
				{
					console.log(data);
					
					var dataLen = data.length; //amount of different budget entries
					
					//calculate each category's total
					$.each(data, function (key, val)
					{
						totals[labels.get(val.category)] += val.amount;
					});
					//console.log(totals);
					
					//set values for canvas, scale, axis
					var width = 1000;
					var height = 1500;
					
					var widthScale = d3.scaleLinear()
									.domain([0, 200])
									.range([0, width - 300]);
					
					var heightScale = d3.scaleLinear()
									.domain([0, numCat])
									.range([0, 840]);
					
					var color = d3.scaleLinear()
								.domain([0,60])
								.range(["red", "blue"]);
					
					var xAxis = d3.axisBottom()
								.scale(widthScale);
					
					xAxis.tickSize(.75);
					
					var yAxis = d3.axisLeft()
				    			.scale(heightScale);
					
					yAxis.tickSize(.75)
						.tickFormat(function(d,i){ return categories[i].category; })
						.tickValues(d3.range(21));
					
					var canvas = d3.select("body")
								.append("svg")
								.attr("width", width)
								.attr("height", height)
								.append("g")
								.attr("transform", "translate(200, 50)");
					
					//bars for each category
					var bars = canvas.selectAll("rect")
								.data(totals)
								.enter()
									.append("rect")
									.attr("width", function(d) { return widthScale(d); })
									.attr("height", 25)
									.attr("fill", function(d) { return color(d); })
									.attr("y", function(d, i) { return i * 40; }); //space between bars
					
					//place x axis
					var yTranslate = 40 * numCat;
					console.log(yTranslate);
					canvas.append("g")
						.attr("stroke", "white")
						//.attr("fill", "white")
						.attr("transform", "translate(0, " + yTranslate + ")")
						.call(xAxis);
					
					//place y axis
					canvas.append("g")
						.attr("stroke", "white")
						.attr("transform", "translate(-25, 10)")
						.call(yAxis);
					
					d3.selectAll("g")
						.style("fill", "white");
					
				},
				error: function(error){
			        console.log(error);
				}
			});
			
		}
	});
	
	getRecent();
}); 

function getRecent()
{
	$.ajax({
		url: '/BudgetApplication/recent', //see HomeController.java
		type: 'GET',
		dataType : 'json',
		success: function (response)
		{
			console.log(response);
			var html = ""; //stores the string to be returned
			
			//append fields for each entry in the table
			$.each(response, function (key, val)
			{
				html += '<tr><td>' + val.category + '</td><td>$' + val.amount.toFixed(2)
				+ '</td><td>' + val.insertedOn + "</td></tr>";
			});
			
			if (html != "")
			{
				//clear table body, then update
				$('#recentTable tbody').empty().append(html);
				$('#recentTable').trigger('update');
			}
			else
			{
				$('#recentTable thead').empty();
				$('#message').empty().append("No entries added yet!");
				document.getElementById("recentTable").style.display = "none";
			}
		},
		error: function(error)
		{
	        console.log(error);
		}
	});
}