
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart());
    
function drawChart() 
{
      
        var title = "Node: "+currentNode;
        var xTitle = "Date";
        var yTitle = titles[currentVariable];
//        var dataCount = 0;
        if (totalData !== null)
        {
            totalData = google.visualization.data.join(totalData,newData(),'full',[[0,0]],[1],[1]);
//            dataCount++;
        }
        else
        {
            totalData = newData();
//            dataCount = 1;
        }
        
        var options = 
        {
            title: title,
            hAxis: {title: xTitle},
            vAxis: {title: yTitle},
            legend: 'none',
            pointSize: 1,
        };
        var chart = new google.visualization.ScatterChart(document.getElementById('graph'));
        chart.draw(totalData, options);
   
   function newData()
    {
        var data = new google.visualization.DataTable();
        data.addColumn('date', 'Date');
        data.addColumn('number', titles[currentVariable]);
        var num = (dataIn.length);
        data.addRows(num/2);
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
}
