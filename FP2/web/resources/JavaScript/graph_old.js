/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




            google.load("visualization", "1", {packages:["corechart"]});
            google.setOnLoadCallback(drawChart);
            //window.setInterval(drawChart(),100)
            
            //<![CDATA[
            
                   
            function drawChart() 
            {
//                for (var i = 0;i< translateGraphData.length;i+2)
//                    {
//                        sortedData.add(translateGraphData[i]+","+translateGraphData[i+1]);
//                    }
                var translateGraphData = JSON.parse(loadGraphData);
//                alert(translateGraphData);
                var gData = google.visualization.dataTable(
                    
            
            
            );
                
                var options = 
                {
                    title: graphTitle,
                    //hAxis: {title: 'Age', minValue: 0, maxValue: 15},
                    //vAxis: {title: 'Weight', minValue: 0, maxValue: 15},
                    legend: 'none'
                };

                var chart = new google.visualization.ScatterChart(document.getElementById('graph'));
                chart.draw(gData, options);

            }
            
         
             //]]>
