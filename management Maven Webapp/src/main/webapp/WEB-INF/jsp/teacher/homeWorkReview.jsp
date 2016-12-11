<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  
 <div class="container">
 	<div class="row">
 	  <div class="col offset-l2 l10">
 	  	<h4 class="center-align">学生名单</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th data-field="id">学生ID</th>
 	  	  	  <th data-field="name">学生姓名</th>
 	  	  	  <th data-field="class">学生班级</th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody >
 	  	  	<c:forEach var="student" items="${studentList}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td>${student.student_id}</td>
 	  	  	  	<td>${student.student_name }</td>
 	  	  	  	<td>${student.class_id }</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	
 	  </div>
 	</div>
 </div>

