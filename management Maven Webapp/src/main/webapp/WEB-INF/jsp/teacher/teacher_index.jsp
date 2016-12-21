<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
  <body>
  		<div class="header">  
  			
  			<div class="title ">
				  <!-- Dropdown Structure -->
				  <ul id="course-dropdown" class="dropdown-content">
				    <li id="courseDivider" class="divider"></li>
				    <li><a class="modal-trigger" href="#modal1">添加课程</a></li>
				  </ul>			 
	  			<nav class=" nav-wrapper">
	  				<a href="#" class="brand-logo">爱课堂</a>
	  				<ul id="1" class="right hide-on-med-and-down ">
	  					<li><a href="#" >个人信息</a></li>
	  					<!-- Dropdown Trigger -->
	       				<li><a class="dropdown-button" id="courseList" href="#!" data-activates="course-dropdown">课程</a></li>
	  					<li><a href="logout">退出</a></li>
	  				</ul> 		
	  			</nav>
  			</div>
	  			
	  		<div class="navPart">
		  			<nav class="row ">
		  				<ul class="center hide-on-med-and-down ">
			  					<li><a href="#" onclick="courseTab('teacher/courseInfo')">课程信息</a></li>
			  					<li><a href="#" onclick="courseTab('teacher/homeWorkReview')">作业检查</a></li>
			  					<li><a href="#" onclick="courseTab('teacher/homeWorkArrange')">布置作业</a></li>
			  					<li><a href="#" onclick="courseTab('teacher/studentList')">学生名单</a></li>
			  					<li><a href="#" onclick="courseTab('teacher/teamSetting')">设置助教</a></li>
		  				</ul>
		  			</nav>
		  			<!-- 这个是作为所选择course id的标识 -->
		  			<input class="course_id" value="" hidden >
	  		</div>
  		  			
  		</div>
  		<div class="main">
  			<div class="container center-align">
  				<h5>请选择一门课程</h5>
  			</div>
  		</div>		
		
		
		
		
		
		<div class="foot">
			<footer class="page-footer">
				<div class="container">
					<div class="row">
						<div class="col l6 s12">
							<h5 class="white-text">GetHelp</h5>
							<p>you can send a mail to us for more use info  </p>
						</div>
						<div class="col l4 offset-12 s12">
							<h5 class="white-text" >contact us</h5>
							<ul class="mailAddress">
								<li><a class="grey-text text-lighten-3" href="#!">973945379@qq.com</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="footer-copyright">
					<div class="container">
					CopyRight @ 2016 LoveCourse   loveCourse.com    All Rights deserved
					</div>
				</div>
			</footer>
		</div>
		
		
		
  		<div class="modalPart">
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
  					<div class="input-field">
  						<input id="course_rollCall" type="number" min="0">
  						<label class="active" for="course_rollCall">点到次数</label>
  					</div>
  					
  				</div>
  				<div class="modal-footer">
  					<a href="#!" class="modal-action modal-close waves-green btn-flat">取消</a>
  					<a href="#!" class="modal-action waves-green btn-flat" onclick="addCourseInfo(${teacher_id})">下一步</a>
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
  					  <input type="text" name="course_id" id="course_id" value=""  hidden >
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
  							<input id="t_min" type="number" min="1">
  						</div>
  						<div class="input-field col l6">
  							<label>max</label>
  							<input id="t_max" type="number" min="1">
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
  					<a href="#!" class="modal-action  waves-green btn-flat" onclick="addTeamConfig()">完成</a>
  				</div>
  			</div>
  		</div>
  			
  			
  			
  			
		
<script> 	

 $(".dropdown-button").dropdown({
	 	hover : true,
	 	belowOrigin : true
	 });				
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
  	 
 }); 			  	
 	//实现异步表单提交
 	var options={
 		beforeSubmit:function(){
 			if($(".file-path").val()==""){
 			  alert("请选择一个文件");
 			  return false;
 			}
 		},
 		success : function(data){
 			alert("上传成功");
 		}
 	};
 	$("#fileForm1").ajaxForm(options);
 	 
 	 //实现下拉框
 	$("#courseList").hover(function(){
 			var teacher_id=${teacher_id};
 			$.ajax({
			type : "get",
			url :　"teacher/courseList",
			data :{
				"teacher_id" : teacher_id,
			},
			dataType :"json",
			success : function(data){
				$(".courseLi").remove();
				var courses=data.courseList;
				for(var i in courses){									
					var li="<li class=\"courseLi\"> <a  href=\"#!\" id=\""+courses[i].course_id+"\"> "+courses[i].course_name+"</a></li>";
					$("#courseDivider").before(li);
				}
			}
		});	
 	});	
	$("#course-dropdown").on("click",".courseLi",function(){
			var course_id=$(this).children("a").attr("id");
			$(".course_id").val(course_id);
			courseTab('teacher/courseInfo')
			});
</script>
  			
	 
  </body>

</html>
