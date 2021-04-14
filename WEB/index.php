<!DOCTYPE html>
<html lang="en">
<head>
   <title>EasyParking | Λειτουργία χρήστη </title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

   
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA=="
      crossorigin="" />
   <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js" integrity="sha512-nMMmRyTVoLYqjP9hrbed9S+FzjZHW5gY1TWCHA5ckwXZBadntCNs8kEqAWdrb9O7rxbCaA4lKTIWjDXZxflOcA=="
      crossorigin=""></script>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<link rel="stylesheet" type="text/css" href="styles/BootstrapGuest.css">

 
   <script src="scripts/leaflet.ajax.min.js"></script> 
</head>

<body>

<style>
body {
	height:100%;
	background-color: #66151c;
}



.navbar{
	height: 150px;
	background:#66151c;
}
ul
{
	position: absolute;
top: 10%;	
right:1%;
list-style-type: none;
font-family: cursive;

}

p{
	position: absolute;
	top:70%;
	right: 39.5%;
}

.btn-primary
 {
	color: #66151c;
  background-color: white;
  font-weight: bold;
  font-family: "Comic Sans MS", cursive, sans-serif;
 
 }

 
 .btn1{
	color: #66151c;
  background-color: white;
  font-weight: bold;
  font-family: "Comic Sans MS", cursive, sans-serif;
  position: absolute;
  left: -1275px;
  top:140px;
 }
.btn2{
	color: #66151c;
  background-color: white;
  font-weight: bold;
  font-family: "Comic Sans MS", cursive, sans-serif;
  position: relative;
  top:-10px;
  border-radius: 4px;
}
	
div > label{
	color: white;
	position: relative;
	top: 90px;
}

div > input{
	position: relative;
	top: 100px;
}


</style>
 <nav class="navbar ">
 <div class="image" align="left">
		</br>
	  <img  src="images/easypar.png" />
</div>

     <div class="container-fluid">
	 
      <div class="navbar-header">
	  <br>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Εμφάνιση πόλης:</button>
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">Εύρεση στάθμευσης:</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
       <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Δώσε χρόνο εξομοίωσης:</h4>
        </div>
        <div class="modal-body">
			<form role="form" action="runSim.php" method="post" id="calcDemand">
							<input id="timepicker" name="time" class="form-control timepicker" autocomplete="off">
            </form>

        </div>
        <div class="modal-footer">
		<button class="btn2" type="button" onclick="timeSelect()" id="calcDemand" >Εκτέλεση</button>

        </div>
      </div>
      
    </div>
  </div>
  
  
  <!----=========================================================-->
  <!-- Modal2 -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Δώσε χρόνο εξομοίωσης:</h4>
        </div>
        <div class="modal-body">
                <form action="upload.php" id="uploadForm" method="POST" enctype="multipart/form-data">
                        
                           <label for="clickedLocation" >Βάλτε συντεταγμένες</label>
                           <input type="text" class="form-control" name="clickedLocation" id="clickedLocation" aria-describedby="locationHelp" autocomplete="off">
                           <label for="time">Χρόνος εξομοίωσης</label>
                           <input type="text" name="time" class="form-control timepicker" id="time" autocomplete="off">
                        
                           <label for="radius">Επιθυμητή ακτίνα</label>
                           <input type="text" class="form-control" name="radius" id="radius" value="100" pattern="([1-3][0-9]{2}|400)" title="(100m-400m)" aria-describedby="radiusHelp" autocomplete="off">
      </form>

        </div>
        <div class="modal-footer">
		 <button type="submit" id="form-submit" class="btn2" color="black">Eκτέλεση</button>
        </div>
      </div>
      
    </div>
  </div>
  
	     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         </button>
		 
	  </div>
	  <div class="navbaright">
		<ul>
        <li><a href="#" style="color: white"><span class="glyphicon glyphicon-user"></span>Χρήστης</a></li>
		<li><a href="login.php" style="color: white"><span class="glyphicon glyphicon-log-in" ></span>Login</a></li>
		<ul>
	  </div>

		
  
</div>
 
	  
   </nav>
   <div id='map'><div id="loading" class="loading">
    <img src="images/loading.gif" /> 
  </div></div>


	<script src="scripts/indexLoad.js"></script>
   <script src="scripts/guestMap.js"></script>
   <script src="scripts/guestScripts.js"></script>
   <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
</body>

</html>