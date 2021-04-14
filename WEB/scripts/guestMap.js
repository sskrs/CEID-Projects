var map = L.map('map').setView([40.61914337227133, 22.96947253836266], 13);

L.tileLayer(
   'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
         '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
         'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
      id: 'mapbox.streets'
   }).addTo(map);

 //synartisi gia euresh apostash an energopoihsoume thn topothesia sto browser
function onLocationFound(e) {
   var radius = e.accuracy / 2;

   L.marker(e.latlng).addTo(map)
      .bindPopup("You are within " + radius + " meters from this point").openPopup();

   L.circle(e.latlng, radius).addTo(map);
}

function onLocationError(e) {
   console.log("Location Error");
}

var newMarker;
var radiusInput = 100;

$( "#radius" )
  .keyup(function() {
    radiusInput = $( this ).val();
    
  })


map.on('click', function (e) {
   var clickedLocation = e.latlng;
   document.getElementById("clickedLocation").value = clickedLocation;
   if (typeof (newMarker) === 'undefined') {
      newMarker = new L.marker(e.latlng, {
         draggable: false,
      });
      newMarker.addTo(map);
      newRadius = new L.circle(e.latlng, {
         radius: radiusInput
      });
      newRadius.addTo(map);
   } else {
      map.removeLayer(newMarker);
      newMarker = new L.marker(e.latlng, {
         draggable: false,
         radius: radiusInput
      });
      newMarker.addTo(map);
      map.removeLayer(newRadius);
      newRadius = new L.circle(e.latlng, {
         radius: radiusInput
      });
      newRadius.addTo(map);
   }
});


var time = new Date().toLocaleTimeString(); //get current time

map.on('locationfound', onLocationFound);
map.on('locationerror', onLocationError);

map.locate({
   setView: true,
   maxZoom: 16
});


function onEachFeature(feature, layer) {
   var popupForm = feature.id + '<br>Πληθυσμός: ' + feature.properties.population + '<br>Ζήτηση: ' + feature.properties.demand + '%' +'<br>fparkingspaces: ' + feature.properties.fparkingspaces;

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
var date = new Date();
var time = date.getHours();


var geoJsonlayer = new L.GeoJSON.AJAX("simdata.js", {
   onEachFeature: onEachFeature,
   style: style
});
geoJsonlayer.addTo(map);

var parking;

function drawMap() {
   map.removeLayer(geoJsonlayer);
   geoJsonlayer = new L.GeoJSON.AJAX("simdata.js", {
      onEachFeature: onEachFeature,
      style: style
   });
   geoJsonlayer.addTo(map);
}

function timeSelect() {
   var time = $("#timepicker").val();
   var hourmin = time.split(":");
   var hour = parseInt(hourmin[0], 10);
   var min = parseInt(hourmin[1], 10);
   if (min > 30) {
      hour++;
   }

   time = hour;
   var values = "time=" + time;

   $.ajax({
      type: "POST",
      url: "runSim.php",
      data: values,
      // if sent
      success: function () {
         drawMap();
      },
      error: function () {
         alert("Κάτι πήγε στραβά!");
      }
   });
   event.preventDefault();


}
