<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
		<p>已选课程</p>
	<div class="collection">	
		<c:forEach var="course" items="${courses}">
			 <a href="#!" class="collection-item"  id="${course.course_id}"><span class="badge">查看成绩</span>${course.course_name}</a>
	    </c:forEach>     
  
  	</div><!-- end of collection -->
  	<div id="grade-body" >
  	
	</div><!-- end of grade body -->
  	
  	
</div><!-- end of container -->
<script>
  $(".collection-item").click(function(){
  		var course_id=$(this).attr("id");
  		var student_id=$("#student_id").text();
  		showHomeworkGrade(course_id, student_id);
  });
</script>
