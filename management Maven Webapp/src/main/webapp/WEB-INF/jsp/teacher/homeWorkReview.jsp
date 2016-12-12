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
          			<input id="homework_name" type="text" class="validate">
          			<label for="hoemwork_name">作业名称</label>
        		  </div>
        		  <div class="input-field">
          			<h5 class="green-text">提交日期条件</h5>
          			<input id="search_time" type="date" class="datepicker">
        		  </div>
        		  <div class="input-field">
          			<input id="team_number" type="text" class="validate">
          			<label for="team_number">小组编号</label>
        		  </div>
        		  <a class="waves-effect waves-light btn" onclick="conditionSearch()">查询</a>
    		  </div>
    		 </div>
		  </li>
		</ul>
	  </div>
	
	  <div class="col s9">
	    <!-- Teal page content  -->
	     <div class="row">
		 	  	<h4 class="center-align" id="title" name="${data.role}"></h4>
		 	  	<div class="divider"></div>
		 	    <table class="centered ">
		 	  	  <thead>
		 	  	  	<tr>
		 	  	  	  <th data-field="name">小组编号</th>
		 	  	  	  <th data-field="pwd">作业名称</th>
		 	  	  	  <th data-filed="role">提交人姓名</th>
		 	  	  	  <th>成绩</th>
		 	  	  	  <th>批改说明</th>
		 	  	  	  <th>学生意见</th>
		 	  	  	  <th>
		 	  	  	</tr>
		 	  	  </thead>
		 	  	  <tbody id="table-body">
		 	  	  	<c:forEach var="user" items="${data.userList}">
		 	  	  	  <tr class="hoverable striped" id="${user.user_id}">
		 	  	  	  	<td>${user.user_id}</td>
		 	  	  	  	<td>${user.user_name}</td>
		 	  	  	  	<td>${user.user_pwd}</td>
		 	  	  	  	<td>${user.user_role}</td>
		 	  	  	  	<td><a class="waves-effect waves-light btn" name="modify">修改</a></td>
		 	  	  	  	<td><a class="waves-effect waves-light btn" name="delete">删除</a></td>
		 	  	  	  </tr>
		 	  	  	</c:forEach>
		 	  	  </tbody>
		 	  	</table>
		 	  </div>

		</div>
	</div>
<script type="text/javascript">
	
</script>
