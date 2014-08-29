
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
            
            hAxis: {title: xTitle},
                  
             series: calculateSeries(),
             vAxes: {0: {title: y1Type },
                    1: {title: y2Type}
                    },  
            pointSize: 0.5,
            legend: {position: 'top', textStyle: {fontSize: 10}}
            };
        var chart = new google.visualization.LineChart(document.getElementById('graph'));
        chart.draw(totalData, options);
        
        function calculateSeries()
          {
              var series = [];

              for (i=0;i<axisAssignment.length;i++)
              {
                  series[i] = {targetAxisIndex: axisAssignment[i]};
              }
              return series;
          }
          
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
        var dataType = dataIn[0];
        dataIn.shift();
        var axis = dataSelect(dataType);
        axisAssignment.push(axis);
            
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
    function dataSelect(type)
    {
        var axisNumber;
        if (y1Type === null || y1Type === type)
        {
            y1Type = type;
            axisNumber = 0;
        }
        else if (y2Type === null || y2Type === type)
        {
            y2Type = type;
            axisNumber = 1;
        }
        else
        {
            alert("You already have 2 axes assigned.\n\nPlease clear the graph \nor select more objects of \ntype"+y1Type+" or \ntype "+y2Type+" to continue.");
            axisNumber = null;
        }
        return axisNumber;
    }
    
}
