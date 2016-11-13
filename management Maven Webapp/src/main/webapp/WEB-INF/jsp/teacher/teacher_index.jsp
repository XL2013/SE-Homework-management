<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection"/>
 	 <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>

  </head>
  <body>
			  <!-- Dropdown Structure -->
			  <ul id="course-dropdown" class="dropdown-content">
			    <li><a href="#!">one</a></li>
			    <li><a href="#!">two</a></li>
			    <li class="divider"></li>
			    <li><a class="modal-trigger" href="#modal1">添加课程</a></li>
			  </ul>
  			<nav class="nav-wrapper">
  				<a href="#" class="brand-logo">爱课堂</a>
  				<ul id="1" class="right hide-on-med-and-down ">
  					<li><a href="#" >个人信息</a></li>
  					<!-- Dropdown Trigger -->
       				<li><a class="dropdown-button" href="#!" data-activates="course-dropdown">课程<i class="mdi-navigation-arrow-drop-down right"></i></a></li>
  					<li><a href="logout">退出</a></li>
  				</ul> 		
  			</nav>
  			<script>      
  			 $(".dropdown-button").dropdown({
  			 	hover : true,
  			 	belowOrigin : true
  			 });
  			</script>
  			<nav class="row">
  				<ul class="center hide-on-med-and-down ">
	  					<li><a href="#" >课程信息</a></li>
	  					<li><a href="#">活动</a></li>
	  					<li><a href="#">作业</a></li>
	  					<li><a href="#">学生名单</a></li>
	  					<li><a href="#">设置助教</a></li>
	  					<li><a href="#">课程配置</a></li>
  				</ul>
  			</nav>
  			<div class="container">
  				<p>请选择一个课程</p>
  			</div>
  			
  			
  			<!-- 模态框部分 -->
  			<div id="modal1" class="modal modal-fixed-footer">
  				<div class="modal-content">
  					<h4>第一步:设置课程信息</h4>
  					<div class="divider"></div>
  					<div class="input-field">
  						<input id="course_name" type="text" class="validate">
  						<label class="active" for="course_name">课程名称</label>
  					</div>
  					<div class="input-field">
  						<textarea id="description" class="materialize-textarea"></textarea>
  						<label class="active" for="description">课程描述</label>
  					</div>
  				</div>
  				<div class="modal-footer">
  					<a href="#!" class="modal-action modal-close waves-green btn-flat">取消</a>
  					<a href="#modal2" class="modal-action modal-close waves-green btn-flat" onclick="addCourseInfo(${teacher_id})">下一步</a>
  				</div>
  			</div>
  			<div id="modal2" class="modal modal-fixed-footer">
  				<div class="modal-content">
  					<h4>第二步:导入学生名单 </h4>
  					<div class="divider"></div>
  					<form method="post" id="fileForm1" action="teacher/uploadStudentFile" enctype="multipart/form-data">
  					 <div class="file-field input-field ">
  					   <div class="btn">
  					     <span>FILE</span>
  					     <input type="file" name="files" multiple>
  					   </div>
  					   <div class="file-path-wrapper">
  					     <input class="file-path validate" type="text" placeholder="上传一个或多个文件">
  					   </div> 					   
  					 </div>
  					  <input type="text" name="course_id" id="course_id"value=""  hidden >
  					 <button type="submit" class="btn">提交</button>
  					</form>
  				</div>
  				<div class="modal-footer">
  					<a href="#modal3" class="modal-action modal-close waves-green btn-flat">下一步</a>
  				</div>
  			</div>
  			<div id="modal3" class="modal modal-fixed-footer">
  				<div class="modal-content">
  					<h4>第三步:设置小组配置 </h4>
  					<div class="divider"></div>
  					<div class="row">
  						<h5>1.小组人员设置</h5>
  						<div class="input-field col l6">
  							<label>min</label>
  							<input id="t_min" type="text">
  						</div>
  						<div class="input-field col l6">
  							<label>max</label>
  							<input id="t_max" type="text">
  						</div>
  					</div>
  					<div class="row">
  						<h5>2.小组编号前缀设置</h5>
  						<div class="input-field col l6">
  							<label>年份：4位</label>
  							<input id="t_year" type="text">
  						</div>
  						<div class="input-field col l6">
  							<label>班级：后两位</label>
  							<input id="t_class" type="text">
  						</div>
  					</div>
  				</div>
  				<div class="modal-footer">
  					<a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="addTeamConfig()">完成</a>
  				</div>
  			</div>
  			
  			
  			
  			
			<script> 
			
			function addCourseInfo(teacher_id){
				var course_name=$("#course_name").val();
				var description=$("#description").val();
				$.ajax({
					type : "post",
					url :　"teacher/addCourse",
					data :{
						"teacher_id" : teacher_id,
						"course_name" : course_name,
						"description" : description
					},
					dataType :"json",
					success : function(data){
						$("#course_id").val(data);
					}
				});
			
			}
			function addTeamConfig(){
				alert("click")
				var course_id=$("#course_id").val();
				var team_max=$("#t_max").val();
				var team_min=$("#t_min").val();
				var year=$("#t_year").val();
				var class_id=$("#t_class").val();
				$.ajax({
					type : "post",
					url :　"teacher/addTeamConfig",
					data :{
						"course_id" : course_id,
						"team_max" : team_max,
						"team_min" : team_min,
						"year" : year,
						"class_id" : class_id
					},
					dataType :"json",
					success : function(data){
						alert(data);
					}
				});
				
			}
			$(document).ready(function(){
			    // 实现弹出模态框
			   	 $('.modal').modal({
			   	 		dismissible : false,
			   	 		opacity : .5,
			   	 		in_duration  : 400,
			   	 		out_duration : 300,
			   	 		starting_top :'4%',
			   	 		ending_top : '10%'	 		
			   	 });
			   	 //实现下拉框
			   	 $(".dropdown-button").dropdown({
  			 		hover : true,
  			 		belowOrigin : true
  				 });
			  	}); 
			  	
			  	//实现异步表单提交
			  	var options={
			  		success : function(data){
			  			alert(data);
			  		}
			  	};
			  	$("#fileForm1").ajaxForm(options);
			  				  	
		  	 </script>
  			
	 
  </body>

</html>
