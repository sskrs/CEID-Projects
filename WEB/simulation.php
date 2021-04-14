<?php
require ("connect.php");
include("session.php");
header("Refresh:300");

?>

<!DOCTYPE html>
<html lang="en">

<head>
  <title>EasyParking | Εξομοίωση </title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" sizes="32x32" href="images/faviconmedium.png">
  <link rel="icon" type="image/png" sizes="16x16" href="images/favicontiny.png">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" />
  <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
  <script src="scripts/leaflet.ajax.min.js"></script>
  
  <script type="text/javascript" src="simpleblocks.js"></script>

</head>
<style>
.navbar-default{
	border-left-style:none;
	border-top-style:none;
	border-right-style:none;
	border-bottom-style: none; 
	
}
.navbar-default .container-fluid{
	border-left-style:none;
	border-top-style:none;
	border-right-style:none;
	border-bottom-style: solid; 
	border-width: thin;
	border-color:white;
   background-color: #66151c;
   height: 100px;
   
    border-radius: 0px;
   font-family: "Comic Sans MS", cursive, sans-serif;
}
 .navbar-default .container-fluid .navbar-nav > li >a {
   color: white;
 }
 
  .menu ul li{
position: absolute;
top: 40%;	
right:1%;
list-style: none;
font-family: cursive;

}
 .navbar-default .container-fluid .navbar-nav>li>a:hover{
   color: white;
   text-decoration: underline;
 }
 
 
 .container-fluid .row{
	background-color:  #66151c;
	
	color:white;
	
}
 #calcDemand1{
	 position:relative;
	 top: 10px;
	
	 color:black;
	 padding: 5px;
	 font-family: cursive;
	 border-radius: 10px;
 }
 #calcDemand1:hover{
	background-color:	#FFE4C4;
}
#timepicker2{
	position:relative;
	color:#808080;
	border-radius: 5px;
	 
}
#addMin{
	color: black;
}
#removeMin{
	color:black;
}

</style>

	
<body>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="admin.php"><img style="margin-top:-10px" src="images/easypar.png" /></a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="admin.php">Διαχείριση πόλης</a></li>
        <li class="active"><a href="simulation.php">Εξομοίωση</a></li>
		<li><a href="index.php"> GOTO: mobile</a></li>
      </ul>
	    <link rel="stylesheet" type="text/css" href="styles/style.css">
		<ul class="menu navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Διαχειριστής</a></li>
			<li><a href="login.php"><span class="glyphicon glyphicon-log-in"></span> Αποσύνδεση</a></li>			
		</ul>
     </div>
  </nav>

  <div class="container-fluid">
	<!--allagh xrwmatos background-color --->
    <div class="row content" >
      <div class="col-sm-3 ">
        <br>
        <div>
          <label for="time" >Ορισμός επιθυμητής ώρας:</label>
        <div class="input-group">
          <form role="form" action="runSim.php" method="post" id="calcDemand2">
           <input size="21" autocomplete="off" id="timepicker2" name="time" class="timepicker">
            <button type="submit" id="calcDemand2" class="btn btn-primary btn-block">Εκτέλεση</button>
          </form>
        </div>
        <br>
        <label for="time">Τροποποίηση χρονικού διαστήματος:</label>
        <div class="input-group">
		 <input style="width:13em" id="defMins" name="minutes" class="form-control" value="15" pattern="[1-5]?[0-9]" autocomplete="off">
         <button  type="button" id="removeMin" class="btn btn-primary">-</button>
         <button type="button" id="addMin" class="btn btn-primary">+</button>
        </div>
       
		<br>

        </div>
		
      </div>

      <div id="map-container" class="col-sm-9" style="margin-left: 0px">
        <div id="mapid"></div>
      </div>
    </div>
  </div>

  <div id="loading" class="loading">
    <img src="images/loading.gif" /> 
  </div>

	<script src="scripts/scripts.js"></script>
		<script src="scripts/simLoad.js"></script>
	<script src="scripts/simulateMap.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
</body>

</html>