<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



	
<div class="container ">
	<div class="row">
	  <div class="col offset-l2 l6">
	  	<div class="card">
	  	  <div class="card-content">
	  	  	<a class="card-title">${data.course.course_name}<span class="badge">${data.course.course_id }</span></a>
	  	  	<p>${data.course.description}</p>
	  	  	<div class="divider"></div>
	  	  	<div class="row">
	  	  	  <h6 class="col l6">小组前缀: <a id="info-teamPrefix">${ data.teamConfig.prefix}</a></h6>
	  	  	  <h6 class="col l6">小组最大人数: <a id="info-teamMax">${ data.teamConfig.team_max}</a></h6>
	  	  	  <h6 class="col l6">小组最小人数: <a id="info-teamMin">${ data.teamConfig.team_min}</a></h6>
	  	  	  <h6 class="col l6">任课老师: <a id="info-teacher_name">${ data.teacher_name}</a></h6>
	  	  	  <h6 class="col l6">点名次数: <a id="info-total">${data.total}</a></h6>
	  	  	</div>
	  	  </div>
	  	</div>
	  </div>
	</div><!-- end of row -->
</div><!-- end of container -->


