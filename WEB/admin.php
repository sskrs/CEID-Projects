<?php
require 'connect.php';
include('session.php');
?>


<!DOCTYPE html>
<html lang="en">

<head>
  <title>EasyParking</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="styles/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" />
  <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
  <script type="text/javascript" src="simpleblocks.js"></script>

      <link rel="icon" type="image/png" sizes="32x32" href="images/faviconmedium.png">
  <link rel="icon" type="image/png" sizes="16x16" href="images/favicontiny.png">
</head>
<style>
.navbar-default{
	border-left-style:none;
	border-top-style:none;
	border-right-style:none;
	border-bottom-style: solid; 
	border-color:white;
   background-color: #66151c;
   height: 100px;
    border-radius: 0px;
   font-family: "Comic Sans MS", cursive, sans-serif;
}


.navbar-default .navbar-nav>.active>a:hover{
	text-decoration: underline;
}
 .navbar-default .navbar-nav>li>a {
   color: white;
 }
 .navbar-default .navbar-nav>li>a:hover{
   color: white;
   text-decoration: underline;
 }
 
 .menu ul li{
position: absolute;
top: 20%;	
right:1%;
list-style: none;
font-family: cursive;

}
h4{
	color:white;
}
h5{
	color:white;
}

.btn-block{
	 color: #66151c;
	 font-weight: bold;
  font-family: "Comic Sans MS", cursive, sans-serif;
}

.container-fluid .btn-block:hover,
.container-fluid .btn-block:focus{
	background-color: #FAF0E6;
	text-decoration: none;
	color: #66151c;
}

.modal-content{
	background-color: white;
	color: #66151c;
	
  font-family: "Comic Sans MS", cursive, sans-serif;
}
button.btn-default{
	color:black;
}
input[type=submit] {
	background-color:#66151c;
	 color: white;
	 border-radius: 10px;
	  
	  border: none;
}
#sub:hover{
	background-color:#800000;
}

	
</style>
<body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand"  href="admin.php"><img style="margin-top:-10px" src="images/easypar.png" /></a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="admin.php">Διαχείριση πόλης</a></li>
        <li><a href="simulation.php">Εξομοίωση</a></li>
		<li><a href="index.php"> GOTO: mobile</a></li>

      </ul>

     

		<ul class="menu navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Διαχειριστής</a></li>
		<li><a href="login.php"><span class="glyphicon glyphicon-log-in"></span> Αποσύνδεση</a></li>
			
		</ul>
          </div>
  </nav>
  <div class="container-fluid">
    <div class="row content" style="background-color:#66151c">
      <div class="col-sm-3 ">
        
	<!--diaxwristikh-grammh-->
       	<br>
	 <h4 class="modal-title">Επιλέξτε:</h4>
        <h5 class="modal-title">1. να ανεβάσετε αρχείο .kml</h5>
        <h5 class="modal-title">2. να διαγράψετε τη βάση δεδομένων</h5>
	       	<br>
	<!--me to parakatw php deixnoume an h bash einai gemath h oxi-->        
	<button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#uploadModal">Ανέβασμα .kml</button>

        <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#deleteModal">Διαγραφή ΒΔ</button>
	<?php
        $query = "SELECT id FROM polygons";
        $result = mysqli_query($conn, $query);
        $count = mysqli_num_rows($result);
         
        if($count == 0) {
            echo '<p style="font-size:75%; text-align:left; color:white;">ΒΔ: κενή</p>';
			error_reporting(0);
			unlink('simpleblocks.js');
        } else {
            echo '<p style="font-size:75%; text-align:left; color:white;">ΒΔ: γεμάτη</p>';

        }
        ?>
        
	<!--diaxwristiko-grammh-->
        <br>
     
        <!-- Upload Modal -->
        <div class="modal fade" id="uploadModal" role="dialog">
          <div class="modal-dialog">

            <!-- Upload Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h3 class="modal-title">Διαλέξτε αρχείο: </h3>
              </div>
              <div class="modal-body">
                <form action="upload.php" id="uploadForm" method="POST" enctype="multipart/form-data" style="font-family: Comic Sans MS, cursive, sans-serif">
                  <input type="file" name="fileToUpload" id="filez"><br><br>
                  <input type="submit" id="sub">
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Κλείσιμο</button>
              </div>
            </div>

          </div>
        </div>
         <!-- Delete Modal -->
         <div class="modal fade" id="deleteModal" role="dialog">
            <div class="modal-dialog">
  
              <!-- Delete Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h3 class="modal-title">ΠΡΟΣΟΧΗ!</h3>
                </div>
                <div class="modal-body">
                  <h4>Με αυτή την ενέργεια θα διαγράψετε τη ΒΔ, συμφωνειτε;</h4>
                  <button id="deleteButton" class="btn btn-danger">Οριστική διαγραφή</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">Ακύρωση</button>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">Κλείσιμο</button>
                </div>
              </div>
  
            </div>
        
		</div>
        
		<br>
		 <img src="http://i.imgur.com/EFODl.jpg" alt="HTML5 Icon" width="100%" height="100%">
      </div>

      <div id="map-container" class="col-sm-9" style="margin: auto">
        <div id="mapid"></div>
      </div>
    
	
	</div>
 

 </div>

  <script src="scripts/scripts.js"></script> 
  <script src="scripts/manageMap.js"></script> <!--emfanizei to xarth -->
  <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

</body>

</html>