<?php
  require 'connect.php';
error_reporting(E_ALL & ~E_NOTICE);

  //to tmp arxeio pou mesa tou exei ta stoixeia pou fortwsame kata to upload
  $kml = simplexml_load_file("tmpkml.kml");

  $query = "SELECT id FROM polygons";
  $result = mysqli_query($conn, $query);
  $count = mysqli_num_rows($result);
  $placemarks = $kml->Document->Folder->Placemark;
    
  if($count != 0) {
    echo 'Η ΒΔ έχει ήδη γεμίσει!';
  }
  else { 
	//gia kathe placemark tou kml kanoume explode ta stoixeia pou xreiazomaste	
     for ($i = 0; $i < sizeof($placemarks); $i++) {
	  $description = $placemarks[$i]->description;
      $id = $placemarks[$i]->name;
      // afairoume tis allages grammhs apo to tag tou description
      $expElem = explode("\n", $description);
	  //edw /[^0-9.]+/ me th xrhsh tou regular exp afairoume otidhpote einai den einai arithmos
      $population = preg_replace('/[^0-9.]+/', '', $expElem[6]);
      $coordinates = $placemarks[$i]->MultiGeometry->Polygon->outerBoundaryIs->LinearRing->coordinates;
      if($population == '') {
        $population = 0;
      }
	  //antikathistoume , me keno gia na dhmiourghsoume to format tou polygon pou einai: POLYGON((x1 y1,x2 y2...))
	  //antikatastash , me to _ mesa sto tag coordinates
      $coordinates = str_replace(',', '_', $coordinates);
	  //antikatastash kenou me to , mesa sto tag coordinates
      $coordinates = str_replace(' ', ',', $coordinates);
	  //antikatastash _ me to keno mesa sto tag coordinates
      $coordinates = str_replace('_', ' ', $coordinates);

	  //format tou polygon pou einai: POLYGON((x1 y1,x2 y2...))
      $coordinates = "POLYGON((" . $coordinates;
      $coordinates = $coordinates . "))";
	  
	  //tuxaia arxikopoihsh thesewn kai kampules 1-->kentrikh, 2-->katoikia, 3-->statherh
      $spaces = rand(1,100);
      $curve = rand(1,3);
      $gg = 0;
      
	  //The POLYGONFROMTEXT() function generates a GEOGRAPHY value from a string containing a well known text (WKT) representation of a geographic polygon. 
      $sql = "INSERT INTO polygons (id ,population, coordinates, spaces, curve)	VALUES ( '{$id}', '{$population}', PolygonFromText('{$coordinates}'), '{$spaces}', '{$curve}')";
      if (mysqli_query($conn, $sql)) {
        $gg = 1;
      }
    }
	}
  
 mysqli_close($conn); 
?>