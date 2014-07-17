/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


           var bounds = new google.maps.LatLngBounds();
            var infowindow = new google.maps.InfoWindow();    

            for (i = 0; i < locations.length; i++) {  
              var marker = new google.maps.Marker({
                position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                map: map
              });

              //extend the bounds to include each marker's position
              bounds.extend(marker.position);

              google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                  infowindow.setContent(locations[i][0]);
                  infowindow.open(map, marker);
                }
              })(marker, i));
            }

            //now fit the map to the newly inclusive bounds
            map.fitBounds(bounds);

            //(optional) restore the zoom level after the map is done scaling
            var listener = google.maps.event.addListener(map, "idle", function () {
                map.setZoom(3);
                google.maps.event.removeListener(listener);
            });

      function initialize() 
                    {
                        var mapOptions = 
                        {
                            center: new google.maps.LatLng(-34.397, 150.644),
                            zoom: 8
                        };
                        var map = new google.maps.Map(document.getElementById("map"),
                        mapOptions);
                    }
                    google.maps.event.addDomListener(window, 'load', initialize);
                    



