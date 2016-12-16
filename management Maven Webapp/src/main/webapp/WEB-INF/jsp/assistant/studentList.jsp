<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
  <p>协助课程</p>
	<div class="collection">	
		<c:forEach var="course" items="${courses}">
			 <a href="#!" class="collection-item" id="${course.course.course_id}" onclick="rollCallTab1('teacher/studentResult1',${course.rollcall_max})"><span class="badge">查看学生名单</span>${course.course.course_name}</a>
	    </c:forEach>   
  	</div>

</div>



<script>
</script>