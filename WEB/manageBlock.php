<?php
    
    require ("connect.php");
    
    if($_POST['spaces']){
        $spaces = $_POST['spaces'];
        $curve = $_POST['curve'];
        $id = $_POST['id'];
 
        // insert into database    
	$sql = "UPDATE polygons SET spaces='{$spaces}', curve = '{$curve}' WHERE id = '{$id}'";
       exec($sql);
        if (mysqli_query($conn, $sql)) {
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
        }
  mysqli_close($conn);
?>
