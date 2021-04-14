<?php
require 'connect.php';
$sql = "TRUNCATE TABLE polygons;";
if (mysqli_query($conn, $sql)) {
//echo "Record deleted successfully";
} else {
echo "Error deleting record: " . mysqli_error($conn);
}
$fileofman = "simpleblocks.js";
$fileofsim ="simdata.js";
unlink($fileofsim);
unlink($fileofman);
mysqli_close($conn);
header("Location: admin.php");
die();
?>
