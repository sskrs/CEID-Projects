<?php
   include("connect.php");
   session_start();
   
   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form 
      
      $myusername = mysqli_real_escape_string($conn,$_POST['username']);
      $mypassword = mysqli_real_escape_string($conn,$_POST['password']); 
      
      $sql = "SELECT id FROM users WHERE username = '$myusername' and password = '$mypassword'";
      $result = mysqli_query($conn,$sql);
      $row = mysqli_fetch_array($result,MYSQLI_ASSOC);
      $active = $row['active'];
      
      $count = mysqli_num_rows($result);
	  	    
      // If result matched $myusername and $mypassword, table row must be 1 row
		
      if($count == 1) {
		$_SESSION['login_user'] = $myusername;        
        header("location: admin.php");
      }else if ($count == 0) {
		  echo  '<div class="alert alert-danger">
                <a href="login.php" class="close" data-dismiss="alert">x</a>
                <p><strong>username or password wrong! Please try again!.</strong></p>
                
            </div>';
		
      }
   }
?>

<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <title>EasyParking! | Find parking spots near you | available for mobiles too.</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="icon" type="image/png" sizes="32x32" href="images/faviconmedium.png">
	<link rel="icon" type="image/png" sizes="16x16" href="images/favicontiny.png">
<body>
<style>
body {
	background-color: #66151c;
}
#banner {
	height: 150px;
	background:#66151c;
}

h3 {
	font-family: 'Roboto Slab', serif;
	color: white;
}

.container {
  position: absolute;
  align-items: center;
  margin:0;
  top:250px;
 
  left:37%;
  max-width: 350px;
  padding: absolute;
  background-color: white;
  border-style: groove;
  border-color: #66151c;
  
}

input[type=text], input[type=password] {
  width: 90%;
  padding: 10px;
  margin: 5px 2px 20px 2px;
  border-style: ridge;
  border-color: white;
  background: white;
  font-family: cursive;
}

.btn {
  background-color:#66151c;
  color: white;
  padding: 15px;
  border: none;
  cursor: pointer;
  width: 80px;
  border-style: ridge;
  border-color: #F5DEB3;
  font-family: system-ui;
}
label {
	font-family: emoji;
	font-size: 1.5em;
}
.login-box {
	color: #66151c;
}

.container{
	top: -200px;
	animation: drop 0.75s ease forwards;
}
 @keyframes drop{
	 0%{opacity: 0}
	 70%{ transform: translateY(500px)}
	 100%{ transform: translateY(450px); opasity: 1;}
 }
</style>
</br>

<section id="banner" align="center" >
	<div id="colortitle">
        <img  src="images/easypar.png" />
	</div>
	</br>
	<h3>Ένα πλήρες σύστημα διαχείρισης παρόδιας στάθμευσης</h3>
	<ul class="actions">
	</ul>
</section>

<div class="container">
 </br>
   <div class="login-box animated fadeInUp" role="dialog">
	<form action = "" method = "post">	
		<label for="username" style="color: #66151c">Username</label>
		<input type="text" placeholder="Enter Username" name="username" required>
		<label for="password" style="color: #66151c">Password</label>
		<input type="password" placeholder="Enter Password" name="password" required>
		<center>
			<button type="submit" class="btn"><b><i>Login</i></b></button>
			</br>
			</br>
			<button type="submit" class="btn" onclick="window.open('index.php');"><b><i>Guest</i></b></button>
		</center>
	</form>
	</br>
	<a href="#"><p class="small" style="color: #66151c" align="center">Ξέχασες τον κωδικό σου; Πάτησε εδώ!</p></a>
	</div>  
</div>
	
 <img src="http://pluspng.com/img-png/parking-lot-png-hd-cram-school-parking-lot-png-1920.png" alt="HTML5 Icon" width="100%" height="100%">
	
</body>


</html>