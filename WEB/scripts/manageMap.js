var mymap = L.map('mapid').setView([40.61914337227133, 22.96947253836266], 13);
L.tileLayer(
   'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
         '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
         'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox.streets'
   }).addTo(mymap);
   

let marker = L.marker ([40.632727,  22.941234]);
marker.addTo(mymap);
marker.bindPopup("<b>Κέντρο!</b>").openPopup();
var popup = L.popup();

//pop up window kai emfanisei stoixeiwn poy mporei na eisagei o xristis
function onEachFeature(feature, layer) {
   var popupForm = feature.id + '<br>πληθυσμός: ' + feature.properties.population + '<form id="manageBlock" action="manageBlock.php" method="post">Θέσεις_parking: <input type="text" name="spaces" size="1" pattern="([1-9][0-9]{0,2}|1000)" title="Number 1-1000"/><br> Curve:\t  <select size="1" name = "curve"><option value="1">Kέντρο</option><option value="2">Κατοικία</option><option value="3">Σταθερή</option></select><input type="hidden" name="id" value="' + feature.id + '"><br><input type="submit" value="        Υποβολή          "/></form><div id="blockAdded"></div>';

   layer.bindPopup(popupForm);
}

var emptyStyle = {
   "color": "#808080",
   "weight": 1,
   "fillOpacity": 0.55
};

var graymap = L.geoJSON(simpleblocks, {onEachFeature: onEachFeature, style: emptyStyle }).addTo(mymap);