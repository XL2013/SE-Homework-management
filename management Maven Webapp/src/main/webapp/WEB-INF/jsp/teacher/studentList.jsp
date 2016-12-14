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
  
 <div >
 	<div class="row">
 	  <div class="col s3">
	    <!-- Grey navigation panel -->
	    <ul class="collapsible" data-collapsible="accordion">
    	<li>	
	        <div class="collapsible-header" id="rollCallTimeIdentifier" name="${rollcall_max}">请选择点名次数</div>
	        <div class="collapsible-body">
	        	<ul class="collection">
		        <c:forEach  var="i" begin="1" end="${rollcall_max}">
		        	<a href="#!" class="collection-item waves-effect waves-teal" onclick="rollCallTab('teacher/studentRollCall',${i})">
		        	<i class="material-icons">send</i>
		        		第${i}次
		        	</a>
		        </c:forEach>
		        </ul>
	        </div>
        </li>
        <li>
        	<div class="collapsible-header" id="ResultsIdentifier" onclick="rollCallTab1('teacher/studentResult1',${rollcall_max})">查看学生成绩</div>
        </li>
        </ul>
	  </div>
	  
 	  <div class="col s9" name="rollCallTab">
	  	
 	  </div>
 	</div>
 </div>
 
 <script type="text/javascript">
	  $(document).ready(function(){
	    $('.collapsible').collapsible();
 	  });
 </script>
