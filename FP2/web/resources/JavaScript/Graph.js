
google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart());
    
function drawChart() 
{
      
        var title = "Node: "+currentNode;
        var xTitle = "Date";
        var yTitle = titles[currentVariable];
        if (totalData !== null)
        {
            var tempData = newData();
            var tempData2 = totalData;
            dataArray[dataCount] = tempData;
            var joinMark = countArray(dataCount);
            totalData = google.visualization.data.join(tempData2,tempData,'full',[[0,0]],joinMark,[1]);
            dataCount = dataCount+1;
        }
        else
        {
            totalData = newData();
            dataArray[dataCount] =  totalData; 
            dataCount = 1;
        }
        
        var options = 
        {
//            title: title,
            hAxis: {title: xTitle},
            vAxis: {title: yTitle},
            pointSize: 1,
            legend: {position: 'top', textStyle: {fontSize: 14}}
            };
        var chart = new google.visualization.ScatterChart(document.getElementById('graph'));
        chart.draw(totalData, options);
        
        function countArray(count)
        {
            var arrayCount= new Array();
            if (count===1)
            {
                arrayCount[0] = count;
            }
            else
            {
                for (var i=0;i<count;i++)
                    {
                        var temp = i+1;
                        arrayCount[i] = temp;
                    }
            }
            return arrayCount;
        }
   function newData()
    {
        var data = new google.visualization.DataTable();
        data.addColumn('date', 'Date');
        data.addColumn('number', "Node: "+currentNode+": "+titles[currentVariable]);
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
