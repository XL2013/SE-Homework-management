<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  
 <div >
 	<div class="row">
 	  <div class="col s3">
	    <!-- Grey navigation panel -->
	      <ul class="collection with-header">
	        <li class="collection-header"><h4>请选择点名次数</h4></li>
	        <c:forEach  var="i" begin="1" end="5">
	        	<a href="#!" class="collection-item waves-effect waves-teal" onclick="modifyStudentRollCallStat()">
	        		第${i}次
	        	</a>
	        </c:forEach>
	      </ul>
	  </div>
	  
 	  <div class="col s9">
 	  	<h4 class="center-align">学生名单</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th data-field="id">学生ID</th>
 	  	  	  <th data-field="name">学生姓名</th>
 	  	  	  <th data-field="class">学生班级</th>
 	  	  	  <th>点名</th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody >
 	  	  	<c:forEach var="student" items="${studentList}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td >${student.student_id}</td>
 	  	  	  	<td>${student.student_name }</td>
 	  	  	  	<td>${student.class_id }</td>
 	  	  	  	<td>
      				<input type="checkbox" id="${student.student_id}"/>
      				<label for="${student.student_id}"></label>
 	  	  	  	</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	
 	  </div>
 	</div>
 </div>
 
 <script type="text/javascript">
	  $(document).ready(function(){
	    $('.collapsible').collapsible();
 	  });
 </script>
