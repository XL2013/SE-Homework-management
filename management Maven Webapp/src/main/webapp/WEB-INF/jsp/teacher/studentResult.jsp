<%@page import="com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 	  	<div>
 	  	<h4 class="center-align" id="studentListTitle1">学生成绩表</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th>学生ID</th>
 	  	  	  <th>学生姓名</th>
 	  	  	  <th>学生班级</th>
 	  	  	  <th>作业成绩</th>
 	  	  	  <th>点名成绩</th>
 	  	  	  <th>个人成绩</th>
 	  	  	  <th>期末成绩</th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody >
 	  	  	<c:forEach var="item" items="${data}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td >${item.student.student_id}</td>
 	  	  	  	<td>${item.student.student_name }</td>
 	  	  	  	<td>${item.student.class_id }</td>
 	  	  	  	<td>
 	  	  	  	  <a class='dropdown-button btn-floating red' href='#' data-activates="${item.student.student_id}1"><i class="material-icons">view_list</i></a> 	  
				  <!-- Dropdown Structure -->
				  <ul id="${item.student.student_id}1" class='dropdown-content'>
				  	<li><a href="#!">测试而已</a></li>
					<li class="divider"></li>
				  </ul>
				</td>
				<td>
				  <a class='dropdown-button btn-floating blue-gray' href='#' data-activates='${item.student.student_id}2'><i class="material-icons">visibility</i></a>
				  <ul id='${item.student.student_id}2' class='dropdown-content'>
				  </ul>
 	  	  	 	</td>
 	  	  	 	<td>${item.person_grade}</td>
 	  	  	 	<td>${item.total_grade }</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	</div>
 	  	
<script type="text/javascript">
  $('.dropdown-button').dropdown({
      inDuration: 300,
      outDuration: 225,
      constrain_width: false, // Does not change width of dropdown to that of the activator
      hover: false, // Activate on hover
      gutter: 0, // Spacing from edge
      belowOrigin: false, // Displays dropdown below the button
      alignment: 'left' // Displays dropdown with edge aligned to the left of button
    }
  );
  $(document).ready(function(){
		var course_id=$(".course_id").val();
		if(course_id==""){
			alert("请选择一门课程");
			return;
		}
		$.ajax({
			type :"get",
			url :'teacher/studentResult',
			data :{
				"course_id" : course_id
			},
			dataType :"json",
			success : function(data){
				showStudentResult(data.data);
			}			
		});
  })
  
</script>
        