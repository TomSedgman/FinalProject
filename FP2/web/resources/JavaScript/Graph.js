
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart());

    
function drawChart() 
{
      
    function buildData()
    {
        //var dataArray = dataIn.split("|");
        var data = new google.visualization.DataTable();
        data.addColumn('date', 'Date');
        data.addColumn('number', titles[currentVariable]);
        var num = (dataIn.length);//dataArray.length;
        data.addRows(num/2);
//        var dateFormatter = new google.visualization.DateFormat
//            ({
//                pattern: "dd/MM/yy-hh:mm:ss"
//            });
//            dateFormatter.format(dataIn,0);
        var i = 0;
        var j = 0;
        while (i<num)
        {
            
            var d = (dataIn[i]);
            if (i%2===0)
            {
                d = new Date(d);
                data.setCell(j,0,d);
                i++;
            }
            else
            {
                
                data.setCell(j,1,parseFloat(d));                
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
