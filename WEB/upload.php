<?php
//uploads file to root directory and parses is it with kmltomysql.php
$target_dir = ".../fileToUpload";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$IsUploadOK = 1;
//strlower gia lowercase kml extension
$FileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
if (file_exists($target_file)) {
    $IsUploadOK = 0;
}
if($FileType != "kml") {
    echo "Μόνο kml files επιτρέπονται!";
    $IsUploadOK = 0;
}
if ($IsUploadOK == 0) {
    echo "ΣΦΑΛΜΑ!";
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], 'tmpkml.kml')) {
        echo "Το αρχείο σας ". basename( $_FILES["fileToUpload"]["name"]). " ανέβηκε επιτυχώς!.";
	//ektupwnoume se morfh array ta stoixeia tou proswrinou mas kml arxeiou.
		print_r($_FILES);
	//xrhsh require kai oxi include gia na diakopei h roh kai n mhn sunexistei mono me emfanish warning
        include 'kmltomysql.php';
        //diagrafh proswrinou arxeiou
        unlink('tmpkml.kml');
        include 'mysqltojson.php';
    } else {
        echo "ΣΦΑΛΜΑ! Το αρχείο σας δεν ανέβηκε!";
		print_r($_FILES);
    }
}
?>
