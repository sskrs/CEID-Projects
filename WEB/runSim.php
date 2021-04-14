
<?php
require ("connect.php");
error_reporting(E_ALL & ~E_NOTICE);

// Demand Table
//array(time, center, home, constant)
$demandtable = array
(
  array(0, 0.75, 0.69, 0.18),
  array(1, 0.55, 0.71, 0.17),
  array(2, 0.46, 0.73, 0.21),
  array(3, 0.19, 0.68, 0.25),
  array(4, 0.2, 0.69, 0.22),
  array(5, 0.2, 0.7, 0.17),
  array(6, 0.39, 0.67, 0.16),
  array(7, 0.55, 0.55, 0.39),
  array(8, 0.67, 0.49, 0.54),
  array(9, 0.8, 0.43, 0.77),
  array(10, 0.95, 0.34, 0.78),
  array(11, 0.9, 0.45, 0.83),
  array(12, 0.95, 0.48, 0.78),
  array(13, 0.9, 0.53, 0.78),
  array(14, 0.88, 0.5, 0.8),
  array(15, 0.83, 0.56, 0.76),
  array(16, 0.7, 0.73, 0.78),
  array(17, 0.62, 0.41, 0.79),
  array(18, 0.74, 0.42, 0.84),
  array(19, 0.8, 0.48, 0.57),
  array(20, 0.8, 0.54, 0.38),
  array(21, 0.95, 0.6, 0.24),
  array(22, 0.92, 0.72, 0.19),
  array(23, 0.76, 0.66, 0.23)
);

unlink('simdata.js');

$time = $_POST['time'];
$timeExploded = explode(":",$time);
$hour = $timeExploded[0];
$min = $timeExploded[1];

if ($min>30){
    $hour++;
}

$time = intval($hour);
if (empty($time)){
    $time = intval(date("h")); //Set time as current time
}

$sql = "SELECT id, population, curve, spaces FROM polygons";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        $id = $row["id"];
        $population =  $row["population"];
        $curve =  $row["curve"];
        $spaces =  $row["spaces"];
        //ypologismos zitisis kai eleutherwn thesewn opws leei h ekfwnhsh ths askisis
		$demand = $population * 0.2 + $spaces * $demandtable[$time][$curve];
        $fparkingspaces = $spaces - ($demand * $spaces / 100);

        $sql2 = "UPDATE polygons SET fparkingspaces='{$fparkingspaces}', demand = '{$demand}' WHERE id = '{$id}'";
        if (mysqli_query($conn, $sql2)) {
            //echo "ok\n";
        } else {
            echo "Error: " . $sql2 . "<br>" . mysqli_error($conn);
        }
    
    }
    mysqltojson($time, $conn);
} else {
   // echo "0 results";
}

function mysqltojson($time, $conn){
    $sql = "SELECT id, population, demand, ST_AsGeoJSON(coordinates),fparkingspaces FROM polygons";
    $result = mysqli_query($conn, $sql);

    $jsontype = '{"type": "FeatureCollection","features": [' . $jsontype;

    if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
        $jsontype = $jsontype. '{"type": "Feature", "id": "' . $row["id"] . '", "properties": {"demand": ' . $row["demand"] . ', "fparkingspaces": ' . $row["fparkingspaces"] . ', "population": ' . $row["population"] . '}, "geometry": ';
        $jsontype = $jsontype . $row["ST_AsGeoJSON(coordinates)"] . '},';
        
        }
    } else {
       // echo "0 results";
    }

    $jsontype = rtrim($jsontype,',');
    $jsontype = $jsontype . "]}" . PHP_EOL;
    $myfile = fopen("simdata.js", "a") or die("Unable to open file!");
    fwrite($myfile, $jsontype);
    fclose($myfile);
}
mysqli_close($conn);
?>