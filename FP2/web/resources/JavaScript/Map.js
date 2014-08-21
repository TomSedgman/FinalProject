function initialize() 
{
    
    var mapOptions = 
    {
        center: new google.maps.LatLng(Lat, Long),
        zoom: 15
    };
    var root = document.getElementById('selects');
    var df = document.createDocumentFragment(); 
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    for (var i=0; i<variableArray.length; i++) 
    {
        var id = ("select"+i);
        var select = document.createElement('select');
        select.value = i;
        select.id = id;
        select.appendChild(document.createTextNode(id));
        df.appendChild(select);
        root.appendChild(df);
        var mymarker = new google.maps.Marker(
        {
            map: map,
            title: NodeNameArray[i],
            id: NodeNameArray[i],
            position: new google.maps.LatLng(parseFloat(GPSLatArray[i]), parseFloat(GPSLongArray[i]))
        });
        var Array = variableArray[i].split(",");

        var myinfowindow = new google.maps.InfoWindow();
        setDropDownList(mymarker, myinfowindow, Array, i,id);
        
    } 
}

function setDropDownList(mapMarker, mapInfoWindow, names, node,id) 
{
    // event listener for dropdown list in the map markers' infowindow
    
    var articles = document.getElementById(id);
    articles.onchange = function() 
    {
        titles = names;
        currentNode = node;
        currentVariable = [this.options.selectedIndex];
        ajaxCall();
    };
    var nextArticle, nextOption;
    for (var i = 0; i < names.length; i++) 
    {
        nextArticle = names[i];
        nextOption = new Option(nextArticle);

/* add the new option to the option list
             ("null" for IE5, "-1" for all other browsers) */
        try 
        {
            articles.add(nextOption, -1);
        }
        catch (e) 
        {
            articles.add(nextOption, null);
        }
        mapInfoWindow.setContent(document.getElementById(id));

        google.maps.event.addListener(mapMarker, 'click', function() 
        {
            mapInfoWindow.open(map, this);
        });
    }
} 