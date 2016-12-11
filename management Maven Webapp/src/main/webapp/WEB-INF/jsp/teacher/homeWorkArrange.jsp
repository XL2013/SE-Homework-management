<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="css/layout.css" >
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!--Import jQuery before materialize.js-->
  <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="js/jquery.form.js"></script>
  <script type="text/javascript" src="js/materialize.min.js"></script>
  <script type="text/javascript" src="js/custom.js"></script>

</head>
	<div class="row">
	
	  <div class="col s3">
	    <!-- Grey navigation panel -->
	    <ul class="collapsible" data-collapsible="accordion" id="home_work_show">
	      <li>
	         <h4 class="header center hide-on-med-and-down" >已布置的作业</h2>
	      </li>
		</ul>
	  </div>
	
	  <div class="col s9">
	    <!-- Teal page content  -->
	     <div class="row">
		    <div class="card blue-grey darken-1">
		      <div class="card-content white-text">
		        <span class="card-title" id="title">布置作业</span>
		        <p>请教师填写与作业相关的信息并点击提交完成作业布置</p>
		      </div>
		      <div class="card-action">
		        <a class="btn-floating btn-large waves-effect waves-light blue" onclick="homeWorksUpdateTab()"><i class="material-icons">done</i></a>
		        <a class="btn-floating btn-large waves-effect waves-light blue" onclick="homeWorkArrangeTab()"><i class="material-icons">add</i></a>
		      </div>
		    </div>
		    <div id="tab_contents">
		    	
		    </div>
		  <!-- 这里添加课程信息 -->
		</div>
		      
	  </div>
	
	</div>
<<script type="text/javascript">
	getHomeworksInfoByCourseID()
</script>
