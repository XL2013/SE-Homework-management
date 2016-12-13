<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 	  	
 	  	<h4 class="center-align" id="studentListTitle">第2次点名学生名单</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th data-field="id">学生ID</th>
 	  	  	  <th data-field="name">学生姓名</th>
 	  	  	  <th data-field="class">学生班级</th>
 	  	  	  <th><a class="waves-effect waves-light btn" onclick="modifyStudentRollCallStat()">确认点名</a></th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody >
 	  	  	<c:forEach var="student" items="${studentList}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td >${student.student_id}</td>
 	  	  	  	<td>${student.student_name }</td>
 	  	  	  	<td>${student.class_id }</td>
 	  	  	  	<td>
      				<input type="checkbox" id="${student.student_id}" name="checkbox"/>
      				<label for="${student.student_id}"></label>
 	  	  	  	</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>