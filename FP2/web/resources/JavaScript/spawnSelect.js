function spawnSelect() 
{
    var root = document.getElementById('selects');
    var df = document.createDocumentFragment(); 
    for (var i=0; i<8; i++) 
    {
        var select = document.createElement('select');
        select.value = i;
        select.appendChild(document.createTextNode("select" + i));
        df.appendChild(select);
        root.appendChild(df);
    } 
}
            