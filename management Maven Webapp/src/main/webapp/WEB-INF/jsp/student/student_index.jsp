<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
  	<link type="text/css" rel="stylesheet" href="css/student.css" >
 	 <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
    <script type="text/javascript" src="js/custom.js"></script>
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
		      <a href="#!name"><span class="white-text name" >${user.user_name}</span></a>
		      <a href="#!"><span class="white-text " id="student_id">${user.user_id}</span></a>
		   	   </div>
		    </li>
		    <li><a href="#!" onclick="studentTab('student/homework')">作业 </a></li>
		    <li><a href="#!" onclick="studentTab('student/grade')">成绩</a></li>
		    <li><a href="#!" onclick="studentTab('student/team')">小组</a></li>
			<li class="no-padding">
	       	   <ul class="collapsible collapsible-accordion">
	             <li>
	              <a class="collapsible-header">已选课程</a>
	              <div class="collapsible-body">
	                <ul id="courseList">
	                  <li><a href="#!">First</a></li>
	                  <li><a href="#!">Second</a></li>
	                  <li><a href="#!">Third</a></li>
	                  <li><a href="#!">Fourth</a></li>
	                </ul>
                  </div>
                 </li>
       		   </ul>
     	    </li>
  		</ul>
  		<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
  	</div>
  	<div class="main">
  	</div>
  	<script>
  	 $(".button-collapse").sideNav();
  	 studentTab("student/team");
  	</script>
  </body>
  
 
</html>
