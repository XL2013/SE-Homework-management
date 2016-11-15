<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
	<head>
			<link type="text/css" rel="stylesheet" href="css/login.css" >		
			<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
		 	 <!--Import jQuery before materialize.js-->
		    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
		    <script type="text/javascript" src="js/materialize.js"></script>
		    <script type="text/javascript" src="js/custom.js"></script>
	</head>
  <body>
  		<div class="container white" id="formBox">
	  		<div class="row">
			  <form action="login" class="col s12">
			  	<div class="row">
			  	  <div class="input-field col s12">
			  	  	<input type="text"  id="user_id" name="user_id"  class="validate" placeholder="user_id">
				  	<label for="user_id"> user_id</label>
				   </div>
			    </div>
			    <div class="row">
			      <div class="col s12">
			      	<input type="password" id="password" name="password" class="validate" placeholder="password">
				    <label for="password">password</label>
				   </div>
			    </div>
			    <button type="submit">submit</button>
			  </form>	
			 </div>	    
		</div>
  </body>
  
</html>
