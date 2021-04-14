var center = [40.61914337227133, 22.96947253836266];
var mymap = L.map('mapid').setView(center, 13);
L.tileLayer(
   'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
         '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
         'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox.streets'
   }).addTo(mymap);

var popup = L.popup();

function onEachFeature(feature, layer) {
   var popupForm = feature.id + '<br>Πληθυσμός: ' + feature.properties.population + '<br>Ζήτηση: ' + feature.properties.demand + '%';

   layer.bindPopup(popupForm);
}


function getColor(d) {
   return d > 85 ? '#d7191c' :
          d > 60 ? '#FFD700' :         
          d > 0  ? '#1a9850' :
                   '#808080';
}


function style(feature) {
   return {
       fillColor: getColor(feature.properties.demand),
       fillOpacity: 0.7,
       weight: 0
   };
}


var emptyStyle = {
   "color": "#808080",
   "weight": 1,
   "fillOpacity": 0.55
};

var geoJSONlayer = L.geoJSON(simpleblocks, {onEachFeature: onEachFeature, style: style}).addTo(mymap);

var parking;
if(mymap.hasLayer(parking)){
   mymap.removeLayer(parking);
}
parking = new L.GeoJSON.AJAX("simdata.js",{onEachFeature: onEachFeature, style: style});    
parking.addTo(mymap);


function draw(){
   if(mymap.hasLayer(parking)){
      mymap.removeLayer(parking);
   }
   parking = new L.GeoJSON.AJAX("simdata.js",{onEachFeature: onEachFeature, style: style});    
   parking.addTo(mymap);
}



