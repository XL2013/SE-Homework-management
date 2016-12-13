<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 	  	<div>
 	  	<h4 class="center-align" id="studentListTitle">第2次点名学生名单</h4>
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
 	  	  	<c:forEach var="student" items="${data.studentList}">
 	  	  	  <tr class="hoverable striped">
 	  	  	  	<td >${student.student_id}</td>
 	  	  	  	<td>${student.student_name }</td>
 	  	  	  	<td>${student.class_id }</td>
 	  	  	  	<td>
 	  	  	  	  <a class='dropdown-button btn-floating red' href='#' data-activates='dropdown1'><i class="material-icons">view_list</i></a> 	  
				  <!-- Dropdown Structure -->
				  <ul id='dropdown1' class='dropdown-content'>
				  	<c:forEach var="homeworkGrade" items="${data.homeworkGrades}">
				  		<li><a href="#!">${homeworkGrade.homework_name} ：
				  		${homeworkGrade.status=="1"?homeworkGrade.grade:"未批改"}</a></li>
				    	<li class="divider"></li>
				  	</c:forEach>
				  </ul>
				</td>
				<td>
				  <a class='dropdown-button btn-floating blue-gray' href='#' data-activates='dropdown2'><i class="material-icons">visibility</i></a>
				  <ul id='dropdown2' class='dropdown-content'>
				  <c:forEach var="rollCall" items="${data.rollCalls}">
				    <li><a href="#!">第${rollCall.rollcall_ID}次点名：${rollCall.rollcall_state}</a></li>
				    <li class="divider"></li>
				  </c:forEach>
				  </ul>
 	  	  	 	</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	</div>
 	  	
 	  	<div class="modal" id="resultmodal">
			<div class="modal-content" >
			  <div class="row">
		          <div class="card blue-grey darken-1">
		            <div class="card-content white-text">
		              <span class="card-title">学生成绩</span>
		              <p>I am a very simple card. I am good at containing small bits of information.
		              I am convenient because I require little markup to use effectively.</p>
		            </div>
		          </div>
		        </div>
			</div>
			<div class="modal-footer">
				<a href="#!" class="green waves-green btn-flat" onclick="$('#resultmodal').modal('close')">完成</a>
			</div>
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
</script>
        