// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['corechart']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {
    // Create the data table.
    var data = new google.visualization.DataTable();
    var json = $("#json").val();
    var obj = $.parseJSON(json);
    var columns = obj.columns;
    var rows = obj.rows;

    alert("columns: " + columns);
    alert("rows: " + rows);

    for (var column in columns) {
        alert("column: " + column);
        data.addColumn(column.type, column.name);
    }

    //data.addColumn('string', 'Topping');
    //data.addColumn('number', 'Slices');
    data.addRows([
      ['Mushrooms', 3],
      ['Onions', 1],
      ['Olives', 1],
      ['Zucchini', 1],
      ['Pepperoni', 2]
    ]);

    // Set chart options
    var options = {'title':$("#chartName").val(), 'width':400, 'height':300};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}