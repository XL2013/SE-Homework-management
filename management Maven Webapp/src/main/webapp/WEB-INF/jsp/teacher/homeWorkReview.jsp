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
		<ul class="collapsible" data-collapsible="accordion">
		  <li>
		    <div class="collapsible-header" onclick="allSearch()">全部查询<i class="material-icons">send</i></div>
		  </li>
		  <li>
		    <div class="collapsible-header">条件查询<i class="material-icons">send</i></div>
		    <div class="collapsible-body">
		      <div class="row">
    			  <div class="input-field">
          			<input id="homework_name1" type="text" class="validate">
          			<label for="homework_name1">作业名称</label>
        		  </div>
        		  <div class="input-field">
          			<h5 class="green-text">提交日期条件</h5>
          			<input id="search_time1" type="date" class="datepicker">
        		  </div>
        		  <div class="input-field">
          			<input id="team_number1" type="text" class="validate">
          			<label for="team_number1">小组编号</label>
        		  </div>
        		  <a class="waves-effect waves-light btn" onclick="conditionSearch(this)">查询</a>
    		  </div>
    		 </div>
		  </li>
		  <li>
		    <div class="collapsible-header" onclick="allSearch()">计算个人成绩<i class="material-icons">send</i></div>
		  </li>
		  
		</ul>
	  </div>
	
	  <div class="col s9">
	    <!-- Teal page content  -->
	     <div class="row">
		 	  	<h4 class="center-align" id="title"></h4>
		 	  	<div class="divider"></div>
		 	    <table class="centered ">
		 	  	  <thead id="table-head">
		 	  	  	<tr>
		 	  	  	  <th>提交时间</th>
		 	  	  	  <th data-field="name">小组编号</th>
		 	  	  	  <th data-field="pwd">作业名称</th>
		 	  	  	  <th data-filed="role">提交人姓名</th>
		 	  	  	  <th>成绩</th>
		 	  	  	  <th>批改说明</th>
		 	  	  	  <th>学生意见</th>
		 	  	  	  <th>期末占分</th>
		 	  	  	</tr>
		 	  	  </thead>
		 	  	  <tbody id="table-body">
		 	  	  </tbody>
		 	  	</table>
		 	  </div>

		</div>
	</div>
	
	<div id="homework_modal" class="modal modal-fixed-footer">
		<div class="modal-content">
			<h5>小组作业文件列表</h5>
			<div class="divider"></div>
			<ul id="#m-files"></ul>
		</div>
		<div class="modal-footer">
  		  <a href="#!" class="modal-action  waves-green btn-flat" onclick="addTeamConfig()">完成</a>
  		</div>
	</div>
	
<script type="text/javascript">
	$(document).ready(function(){
   // 实现弹出模态框
  	 $('#homework_modal').modal({
  	 		dismissible : true,
  	 		opacity : .5,
  	 		in_duration  : 400,
  	 		out_duration : 300,
  	 		starting_top :'4%',
  	 		ending_top : '10%'	 		
  	 });
  	 
 }); 
</script>
