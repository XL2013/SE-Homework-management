<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<p>负责小组列表</p>  	
	<div class="divider"></div>	
	
	<div class="row" id="team_cards">
	  <c:forEach var="teamInfo" items="${teamInfos}" >
		<div class="col l12">
		  <div class="card" >
		  	<div class="card-content">
			  	<a class="card-title"><span class="badge">${teamInfo.team.course_id}</span>${teamInfo.course_name}</a>
			  	<table>
			  	   <thead>
				    <tr>
				      <th>成员学号</th>
				      <th>成员姓名</th>
				      <th>成员班级</th>
				      <th>设置组长<th>
				    </tr>
				  </thead>
				   <tbody>
				   <c:forEach var="student" items="${teamInfo.members}">
				    <tr>
				      	<td>${student.student_id}</td>
	 	  	  	  		<td>${student.student_name }</td>
	 	  	  	  		<td>${student.class_id }</td>
	 	  	  	  		<td>
	 	  	  	  		<input id="${student.student_id}" type="checkbox" class="leaderCheckBox" team_id="${teamInfo.team.team_id}" ${student.student_id==teamInfo.team.leader_id?'checked':''}>
	 	  	  	  		<label for="${student.student_id}"></label>
	 	  	  	  		</td>
				    </tr>
				    </c:forEach>
					</tbody>
			  	</table>
		  	</div><!-- end of card-content -->
		  	<div class="card-action">
		  	  <div class="row">
		  	    <div class="col l6">
		  	  	  <label>小组联系方式 :<a href="#!" >  ${teamInfo.team.email }</a></label>	
		  	    </div>	  	 
				 <div class="col l6">
		  	  	  <label>小组id:  <a href="#!" >   ${teamInfo.team.team_id }</a></label>	
		  	    </div>	
		  	  </div>	  	  
		  	</div>
		  </div><!-- end of card -->
		</div><!-- end of col l6 -->
	  </c:forEach>	
	 </div>	<!--  end of cardlist -->
</div><!--  end of container -->
<script>
$(".leaderCheckBox").click(function(){
		var isChecked=$(this).prop("checked");
		var team_id=$(this).attr('team_id');
		if(isChecked){

		   $("input[team_id='"+team_id+"']").prop("checked",false);
		   $(this).prop("checked",true);
		}
		var student_id=$(this).attr('id');
		changeTeamLeader(student_id, team_id);				
});
</script>