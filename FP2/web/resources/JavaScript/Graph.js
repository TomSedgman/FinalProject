
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart());

    
function drawChart() 
{
      
    function buildData()
    {
        var dataArray = dataIn.split("|");
        var data = new google.visualization.DataTable();
        data.addColumn('number', 'Date');
        data.addColumn('number', titles[currentVariable]);
        var num = dataArray.length;
        data.addRows(num);
        for (var i=0;i<num;i++)
        {
            var record = dataArray[i].split(",");
            data.setCell(i, 0, record[0]);
            data.setCell(i, 1, record[1]);
        }
        return data;
    }
    
        var title = "Node: "+currentNode;
        var xTitle = "Date/RecordNumber";
        var yTitle = titles[currentVariable];
        var data = buildData();
        var options = 
        {
            title: title,
            hAxis: {title: xTitle},
            vAxis: {title: yTitle},
            legend: 'none'
        };
        var chart = new google.visualization.ScatterChart(document.getElementById('graph'));
        chart.draw(data, options);
    
}
