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

    var json = $.parseJSON($("#json").val());
    var columns = json.columns;
    var rows = json.rows;
    var colnames = [];

    Object.keys(columns).forEach(function(i) {
        data.addColumn(columns[i].type, columns[i].name);
                colnames.push(columns[i].name);
    });

    var rowArray = [];

    Object.keys(rows).forEach(function(i) {
        var drivername;
        var points;
        var row = [];

        if (colnames[0] == 'drivername') {
            row.push(rows[i].drivername)
        }

        if (colnames[1] == 'points') {
            row.push(rows[i].points);
        }

        rowArray.push(row);
    });

    data.addRows(rowArray);

    // Set chart options
    var options = {'title':$("#chartName").val(), 'width':600, 'height':300};

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}