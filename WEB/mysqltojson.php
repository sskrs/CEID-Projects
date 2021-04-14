<?php
require("connect.php");
//ST_AsGeoJSON — Return the geometry as a GeoJSON element.
$sql = "SELECT id, population, ST_AsGeoJSON(coordinates) FROM polygons";
$result = mysqli_query($conn, $sql);


//Dhmiourgoume/arxikopoioume ena JavaScript string(simpleblocks) pou periexei JSON syntax:
$init='var simpleblocks = {"type": "FeatureCollection","features": [';
$jsontype =  $init;
$jsontype= $jsontype;
//epistrefei tis tis grammes tou $result
if (mysqli_num_rows($result) > 0) {
	//dhmiourgia tou arxeiou (json ousiastika) pou tha omadopoihsoume ta dedomena pou pairnoume apo th bash kai tha ta baloume sto simpleblocks.js wste meta na ta probaloume sto xarth!
    while($row = mysqli_fetch_assoc($result)) {
      $jsontype = $jsontype. '{"type": "Feature", "id": "' . $row["id"] . '", "properties": {"population": ' . $row["population"] . '}, "geometry": ';
      $jsontype = $jsontype . $row["ST_AsGeoJSON(coordinates)"] . '},';
      
    }
} else {
   // echo "0 results";
}
//Strip whitespace (or other characters) from the end of a string
$jsontype = rtrim($jsontype,',');
$jsontype = $jsontype . "]}";
$myfile = fopen("simpleblocks.js", "w") or die("Unable to open file!");
fwrite($myfile,$jsontype);
fclose($myfile);
mysqli_close($conn);
?>