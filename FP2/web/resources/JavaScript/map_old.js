/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//<![CDATA[
//                var projects = "#{bean.loadProject("ths28")}";
//                var nodes = "#{bean.loadNodes(0)}"; 
//                var GPSLat = "#{bean.GPSLat()}";
//                var GPSLatArray = GPSLat.split(",");
//                var GPSLong = "#{bean.GPSLong()}";
//                var GPSLongArray = GPSLong.split(",");
//                var NodeName = "#{bean.NodeName()}";
//                var NodeNameArray = NodeName.split(",");
//                var centrepoint = "#{bean.CentrePoint()}";
//                var values = centrepoint.split(",");
//                var Lat = parseFloat(values[0]);
//                var Long = parseFloat(values[1]);
//                var bounds = new google.maps.LatLngBounds();
//                var infowindow = new google.maps.InfoWindow();  
//                var markerVariable = new Array();
//                var variables = "#{bean.getVariables()}";
//                var variableArray = variables.split("//")
//                //var json = "#/{bean.makeJson()}"
//                var graphTitle=""; 
//                var loadGraphData = "#{bean.graphData(6, 3)}";
              
//                var sortedData = new Array();
                
                
                
                

                function initialize() 
                {   
                var mapOptions = 
                {
                    center: new google.maps.LatLng(Lat, Long),
                    zoom: 15

                };
                var map = new google.maps.Map(document.getElementById("map"),
                mapOptions);
                
               

                    for (i = 0; i < variableArray.length; i++)
                    {  

                        marker = new google.maps.Marker(
                        {
                            position: new google.maps.LatLng(parseFloat(GPSLatArray[i]), parseFloat(GPSLongArray[i])),
                            map: map,
                            title: NodeNameArray[i],
                            id: i


                        });
                        
    
                        google.maps.event.addListener(marker, 'click', (function(marker, i) 
                        {
                            return function() 
                            {
                                var Array = variableArray[i].split(",");
                                var returnString = "<div id='marker "+i+"'>"+ NodeNameArray[i].toString()+"<br/> <br/><select name='menu'>";
                                
                                for (j=0;j<Array.length;j++)
                                {
                                    var tempString = NodeNameArray[i].toString()+j.toString();
                                    markerVariable[j] = "<option id='"+tempString+"' class='variable' style='width: 150px'>"+Array[j].toString()+"</option>";
                                    returnString = returnString.concat(markerVariable[j]);
                                    

                                }
                                
                                returnString = returnString.concat("</select></div>");//add select box to confirm
                               
                               
                              infowindow.setContent(returnString);
                              
                              infowindow.open(map, marker);
                              

                            }
                          }
                          )(marker, i));
                    }

                    google.maps.event.addListener(marker, "click", function() {
                    infowindow.open(map, marker);
                    });
                }
                
                function testAlert(name)
                {
                    alert("alert:"+name);
                }
                    
                google.maps.event.addDomListener(window, 'load', initialize);
                    
                function graphName()
                {
                    alert("alert");
                    graphTitle = "test";

                }
                
              
               
            //]]>