<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   	<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
  	<link type="text/css" rel="stylesheet" href="css/layout.css" >
 	 <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
    <script type="text/javascript" src="js/custom.js"></script>
    <script type="text/javascript" src="js/manager.js"></script>
</head>

  <body>
  	<div class="header">
  		<ul id="slide-out" class="side-nav fixed">
		   <li>
		  	  <div class="userView">
			     <div class="background">
			       <img src="img/bupt_1.jpg">
			     </div>
		      <a href="#!user"><img class="circle" src="img/bupt-logo.png"></a>
		      <a href="#!name"><span class="white-text name" >管理员：${user.user_name}</span></a>
		      <a href="#!"><span class="white-text " id="student_id">ID：${user.user_id}</span></a>
		   	  </div>
		    </li>
		    <li><a href="#!" onclick="managerTab('manager/userList',3)">学生用户管理</a></li>
		    <li><a href="#!" onclick="managerTab('manager/userList',2)">助教用户管理</a></li>
		    <li><a href="#!" onclick="managerTab('manager/userList',1)">老师用户管理</a></li>
  		</ul>
  		<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
  	</div>
  	<div class="main">
  	</div>
  	<script>
 	 //managerTab("manager/userList",2);
  	 $(".button-collapse").sideNav();
  	</script>
  </body>
  
</html>