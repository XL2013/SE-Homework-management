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
    <script type="text/javascript" src="js/assistant.js"></script>
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
		      <a href="#!name"><span class="white-text name">助教:${user.user_name}</span></a>
		      <a href="#!email"><span class="white-text " id="assistant_id">${user.user_id}</span></a>
		   	   </div>
		    </li>
		    <li><a href="#!" onclick="assistantTab('assistant/correctHomework')">批改作业 <span class="new badge red" data-badge-caption="未批改">4</span></a></li>
		    <li><a href="#!" onclick="assistantTab('assistant/team')">负责小组</a></li>
		    <li><a href="#!" onclick="assistantTab('assistant/studentList')">学生名单</a></li>
  		</ul>
  		<a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
  	</div>
  	<div class="main">
  	</div>
  	<script>
  	 $(".button-collapse").sideNav();
  	 assistantTab("assistant/team");
  	</script>
  </body>
</html>
