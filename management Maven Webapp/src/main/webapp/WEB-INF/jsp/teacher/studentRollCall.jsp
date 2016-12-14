<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 	  	<h4 class="center-align" id="studentListTitle">第${data.roll_order}次点名学生名单</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th data-field="id">学生ID</th>
 	  	  	  <th data-field="name">学生姓名</th>
 	  	  	  <th data-field="class">学生班级</th>
 	  	  	  <th><a class="waves-effect waves-light btn" onclick="modifyStudentRollCallStat(${data.roll_order})">确认点名</a></th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody>
 	  	  	<c:forEach var="item" items="${data.data}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td >${item.student.student_id}</td>
 	  	  	  	<td>${item.student.student_name }</td>
 	  	  	  	<td>${item.student.class_id }</td>
 	  	  	  	<td>
 	  	  	  	<c:choose>
 	  	  	  		<c:when test="${item.rollCall==1}">
 	  	  	  			<input type="checkbox" id="${item.student.student_id}" name="checkbox" checked="checked"/>
      					<label for="${item.student.student_id}"></label>
 	  	  	  		</c:when>
 	  	  	  		<c:otherwise>
 	  	  	  		 	<input type="checkbox" id="${item.student.student_id}" name="checkbox"/>
      					<label for="${item.student.student_id}"></label>
 	  	  	  		</c:otherwise>
 	  	  	  	</c:choose>

 	  	  	  	</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>

