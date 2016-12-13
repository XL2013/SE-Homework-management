<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
		<p>已选课程</p>
	<div class="collection">	
		<c:forEach var="course" items="${data.courses}">
			 <a href="#!" class="collection-item"  id="${course.course_id}"><span class="badge">查看成绩</span>${course.course_name}</a>
	    </c:forEach>     
      <a href="#!" class="collection-item"><span class="badge">查看成绩</span>java</a>
      <a href="#!" class="collection-item"><span class="badge">查看成绩</span>c++</a> 
  	</div><!-- end of collection -->
  	<div class="grade-body">
	  	<h4>成绩列表</h4>
	  	<div class="divider"></div>
	  	<table>
	  	  <thead>
	  	    <tr>
	  	      <th>作业序号</th>
	  	      <th>作业名字</th>
	  	      <th>成绩</th>
	  	    </tr>
	  	  </thead>
		 <tbody id="t-homework_grade_body">
		 	<tr>
		 	  <td>20130101</td>
		 	  <td>第一次java作业</td>
		 	  <td>90</td>
		 	</tr>
		 </tbody>
		</table>
		<div class="divider"></div>
		<div class="row">
		 <div class="col s4">
		   <h6>总点到次数： <a id="rollCallTimes">4</a></h6>		  
		 </div>
		 <div class="col s4">
		   <h6>缺勤次数： <a id="absenceTimes">3</a></h6>		  
		 </div>
		 <div class="col s4">
		   <h6>课程总成绩：<a id="totalGrade">90</a></h6>		   
		 </div>
		</div><!-- end of row -->
	</div><!-- end of grade body -->
  	
  	
</div><!-- end of container -->
<script>
  $(".collection-item").click(function(){
  		var course_id=$(this).attr("id");
  		var student_id=$("#student_id").text();
  		showHomeworkGrade(course_id, student_id);
  });
</script>
