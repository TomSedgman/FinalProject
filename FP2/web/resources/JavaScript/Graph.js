
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart());

    
function drawChart() 
{
      
    function buildData()
    {
        //var dataArray = dataIn.split("|");
        var data = new google.visualization.DataTable();
        data.addColumn('number', 'Date');
        data.addColumn('number', titles[currentVariable]);
        var num = (dataIn.length);//dataArray.length;
        data.addRows(num/2);
        var i = 0;
        var j = 0;
        while (i<num)
        {
            if (i%2===0)
            {
                var p0 = j;
                data.setCell(j,0,p0);
                i++;
            }
            else
            {
                var p1 = parseFloat(dataIn[i]);
                data.setCell(j,1,p1);                
                i++;
                j++;
            }
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
