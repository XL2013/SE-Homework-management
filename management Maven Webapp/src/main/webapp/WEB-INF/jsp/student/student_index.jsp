<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
  	<link type="text/css" rel="stylesheet" href="css/layout.css" >
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
		      <a href="#!name"><span class="white-text name">学生姓名</span></a>
		      <a href="#!email"><span class="white-text email">学生id</span></a>
		   	   </div>
		    </li>
		    <li><a href="#!">作业 </a></li>
		    <li><a href="#!">成绩</a></li>
		    <li><a href="#!">小组</a></li>
			<li class="no-padding">
	       	   <ul class="collapsible collapsible-accordion">
	             <li>
	              <a class="collapsible-header">已选课程</a>
	              <div class="collapsible-body">
	                <ul>
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
  	<script>
  	 $(".button-collapse").sideNav();
  	</script>
  </body>
  
 
</html>
