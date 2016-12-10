<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<p>已选课程小组配置</p>
	<div class="collection">	
		<c:forEach var="courseInfo" items="${data.courseSetting}">
			 <a href="#!" class="collection-item" hasTeam="${courseInfo.hasTeam}" id="${courseInfo.course.course_id}"><span class="badge">创建/设置小组</span>${ courseInfo.course.course_name}</a>
	    </c:forEach>   
      <!--  
      <a href="#!" class="collection-item"><span class="badge">创建小组</span>java</a>
      <a href="#!" class="collection-item"><span class="badge">创建小组</span>c++</a> 
      -->
  	</div>
  	
	<div class="divider"></div>	
	
	<div class="row" id="team_cards">
	  <c:forEach var="teamInfo" items="${data.teamInfos}" >
		<div class="col l6">
		  <div class="card" >
		  	<div class="card-content">
			  	<a class="card-title"><span class="badge">小组信息</span>${teamInfo.course_name}</a>
			  	<table>
			  	   <thead>
				    <tr>
				      <th>成员学号</th>
				      <th>成员姓名</th>
				      <th>成员班级</th>
				    </tr>
				  </thead>
				   <tbody>
				   <c:forEach var="student" items="${teamInfo.members}">
				    <tr>
				      	<td>${student.student_id}</td>
	 	  	  	  		<td>${student.student_name }</td>
	 	  	  	  		<td>${student.class_id }</td>
				    </tr>
				    </c:forEach>
					</tbody>
			  	</table>
		  	</div><!-- end of card-content -->
		  	<div class="card-action">
		  	  <label>小组联系方式</label>
		  	  <a href="#" id="email">${teamInfo.setting.email }</a>		  	  
		  	</div>
		  </div><!-- end of card -->
		</div><!-- end of col l6 -->
	  </c:forEach>
	  
	
		 
		
	</div>
	
	
	
	<div id="modal1" class="modal modal-fixed-footer">
		<div class="modal-content">
			<h4 id="m-course_name">JAVA</h4>
			<label id="m-course_id" hidden="hidden"></label>
			<div class="divider"></div>
			
			<div class="row">
			  <div class="col l3">
			  	<p></p>
			  	<h4 class="center-align">小组配置</h4>
			  </div>
			  <div class="col l4">
			  <p>
			  	<label>小组id:		</label>
			  	<a id="m-team_id">2013</a>
			  </p>
			   <p>
			  	<label>组长id:		</label>
			  	<a id="m-monitor_id"></a>
			  </p>
			  </div>
			  <div class="col l4">
			   <p>
			  	<label>最大人数： </label>
			  	<a id="m-max">4</a>
			  	<label>最小人数： </label>
			  	<a id="m-min">0</a>
			  </p>
			   <p>
			  	<label>联系方式:   </label>
			  	<input type="email" id="m-email">
			  </p>
			  </div>
			</div>
			
			<div class="divider"></div>
			
			<div class="container">
				<table id="member-table">
				  <thead>
				    <tr>
				      <th>成员学号</th>
				      <th>成员姓名</th>
				      <th>成员班级</th>
				    </tr>
				  </thead>
				  <tbody id="member-body">
				  </tbody>
				</table>
			</div>
			
			<div class="divider"></div>

			<div id="search>">
			  <div class="row">
			    <div class="col l10 input-field">
			 	  <input id="searchInfo" class="validate" type="text"  placeholder="输入姓名或者学号">				 	 
			  	</div>
			  	<div class="col l1">
			  		<p></p>
			  		<a class="btn-floating red" onclick="searchMember()">  <i class="material-icons" >search</i></a>
			  	</div>			  
			 </div>			  	
			</div>
			<h5 >搜索结果：</h5>
			<div class="container">
		  	  <table id="result">
		  	  	<thead>
		  	  	  <tr>
		  	  	  	<th>学号</th>
		  	  	  	<th>姓名</th>
		  	  	  	<th></th>
		  	  	  </tr>
		  	  	</thead>
		  	  	<tbody id="result-body">
		  	  
		  	  	</tbody>
		  	  </table>
		  	</div>
			
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="emailSubmit()">完成</a>
		
		</div>
	</div>
</div>
<script type="text/javascript">
	 $('#modal1').modal({
	      dismissible: false, // Modal can be dismissed by clicking outside of the modal
	      opacity: .5, // Opacity of modal background
	      in_duration: 300, // Transition in duration
	      out_duration: 200, // Transition out duration
	      starting_top: '4%', // Starting top style attribute
	      ending_top: '10%', // Ending top style attribute
	    }
 		);
	$(".collection-item").click(function(){
		var course_id=$(this).attr("id");
		initTeamInfo(course_id);
		$("#modal1").modal('open');
	});
</script>