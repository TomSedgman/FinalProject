
google.load("visualization", "1", {packages:["corechart"]});
google.load('visualization', '1.0', {'packages':['controls']});

google.setOnLoadCallback(drawChart());
    
    function newChart() // clears graph data and resets to begining
            {
                totalData = null;
                dataCount = 0;
                dataArray = null;
                y1Type = null;
                y2Type = null;
                axisAssignment = new Array();
                location.reload(true); // worked prevously as chart.clear, but addition of dashboard broke this. now reloads entire page
              
            }
    
function drawChart() 
{
        
        var title = "Node: "+currentNode;
        var xTitle = "Date";
        var yTitle = titles[currentVariable];
        if (totalData !== null) // has the chart been drawn before?
       
        {
            var tempData = newData();
            if (tempData === null) // for handling 3rd axis error
            {

            }
            else // add new data to exisiting data
            {
                var tempData2 = totalData;
                dataArray[dataCount] = tempData;
                var joinMark = countArray(dataCount);
                totalData = google.visualization.data.join(tempData2,tempData,'full',[[0,0]],joinMark,[1]);
                dataCount = dataCount+1;
            }
            
        }
        else // if yes create a new table
        {
            totalData = newData();
            dataArray[dataCount] =  totalData; 
            dataCount = 1;
        }
        
        
        var options =  
        {
            
            hAxis: {title: xTitle, },
                  
             series: calculateSeries(),
             vAxes: {0: {title: y1Type },
                    1: {title: y2Type}
                    },  
                            
            pointSize: 0.5, // 
            lineWidth: 0, // both of these replicate the apearance of scatter
            legend: {position: 'top', textStyle: {fontSize: 10}}
            };
         var dashboard = new google.visualization.Dashboard(document.getElementById('dashboard'));
         var dateRangeSlider = new google.visualization.ControlWrapper(
        {
            'controlType': 'ChartRangeFilter', 
            'containerId': 'dxControls',
            'options': 
            {
                'filterColumnLabel': 'Date',
                'ui':
                {
                    chartType: 'LineChart',
                    chartOptions: 
                                {
                                    series: calculateSeries(),
                                    pointSize: 0.5,
                                    lineWidth: 0
                                }
                }
            }
        });
//        var sliderArray = new Array(); // previous attempt to get vertical sliders working
//        sliderArray[0]=dateRangeSlider;
//        var dataCol = totalData.getNumberofColumns();
//        for (var i = 0;i<dataCol;i++)
//        {
//            var yaxisSlider = new google.visualization.ControlWrapper(
//            {
//                'controlType': 'NumberRangeFilter',
//                'containerId': 'dyControls',
//                'options': 
//                {
//                    'filterColumnIndex': i+1,
//                    'ui':
//                    {
//                        label: i+1,
//                        orientation:'vertical',
//                        //chartOptions: options
//
//                    }
//                }
//            });
//            sliderArray.push(yaxisSlider);
//        }
//        var y2axisSlider = new google.visualization.ControlWrapper(
//           {
//            'controlType': 'NumberRangeFilter',
//            'containerId': 'dyControls',
//            'options': 
//            {
//                'filterColumnLabel': y2Type,
//                'ui':
//                {
//                    label: y2Type,
//                    orientation:'vertical'
//                    
//                }
//            }
//        });

        //var chart = new google.visualization.LineChart(document.getElementById('graph'));
        var chart = new google.visualization.ChartWrapper({
            'chartType': 'LineChart',
          'containerId': 'graph',
          'options': options
        });

        dashboard.bind(dateRangeSlider, chart);
        dashboard.draw(totalData);
        
        //chart.draw(totalData, options);
        
        function calculateSeries() // return array indicating which y axis data to be plotted against
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
       
    
    
        
   function newData() // get data from database
    {
        var dataType = dataIn[0];
        dataIn.shift(); // remove data type for axisAssignment
        var axis = dataSelect(dataType);
        
        var data = new google.visualization.DataTable();

        if(dataIn.length !== 0)
        {
            axisAssignment.push(axis); 
            data.addColumn('datetime', 'Date');
            data.addColumn('number', "Node: "+NodeNameArray[currentNode]+": "+titles[currentVariable]);
            var num = (dataIn.length);
            data.addRows(num/2);
            var i = 0;
            var j = 0;
            while (i<num)
            {
                var d = (dataIn[i]);
                if (i%2===0) // convert 1d array into 2d array for Datatable
                {
                    
                    var date = new Date(d);
                    //date = d;
                    data.setCell(j,0,date);
                    i++;
                }
                else
                {
                    data.setCell(j,1,parseFloat(d));                
                    i++;
                    j++;
                }
            }
        }
        else 
        {
            data = null;
        }
        return data;
    }
    
    function dataSelect(type) // calculate if an axis is occupied, and if so, is the incoming dataType matching one of the selected variable, if both axes are full, and the incoming does not match, throw the error.
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
            alert("You already have 2 axes assigned.\n\nPlease clear the graph or select more objects of type"+y1Type+" or type "+y2Type+" to continue.");
            dataIn.length = 0;
        }
        return axisNumber;
    }
    
}
