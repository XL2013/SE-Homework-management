<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<p>未分配小组的课程</p>
	<div class="collection">
    <a href="#!" class="collection-item"><span class="badge">创建小组</span>java</a>
    <a href="#!" class="collection-item"><span class="badge">创建小组</span>c++</a>
  	</div>
	<div class="divider"></div>	
	
	<div class="row" id="team_cards">
		<div class="col l6">
		  <div class="card" id="card1">
		  	<div class="card-content">
		  	<span class="card-title">Java</span>
		  	<table>
		  	   <thead>
			    <tr>
			      <th>成员学号</th>
			      <th>成员姓名</th>
			      <th>成员班级</th>
			    </tr>
			  </thead>
			   <tbody>
			    <tr>
			      <th>2013211001</th>
			      <th>徐志雷</th>
			      <th>2013211304</th>
			    </tr>
				</tbody>
		  	</table>
		  	</div>
		  	<div class="card-action">
		  	  <a href="#" id="email">973945379@qq.com</a>
		  	  
		  	</div>
		  </div>
		</div>
	</div>
	
	
	
	<div id="modal1" class="modal modal-fixed-footer">
		<div class="modal-content">
			<h4 id="m-course_id">JAVA</h4>
			
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
			  	<a id="m-monitor_id">2013211000</a>
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
			  	<a id="m-email">973945379@qq.com</a>
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
				    <tr>
				      <th>2013211001</th>
				      <th>徐志雷</th>
				      <th>2013211304</th>
				    </tr>
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
			  		<a class="btn-floating red" onclick="">  <i class="material-icons" >search</i></a>
			  	</div>			  
			 </div>			  	
			</div>
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
		  	  	  <tr>
		  	  	  	<th>2013211001</th>
		  	  	  	<th>周中山</th>
		  	  	  	<th><a class="btn-floating red"><i class="material-icons" onclick="">add</i></a></th>
		  	  	  </tr>
		  	  	</tbody>
		  	  </table>
		  	</div>
			
		</div>
		<div class="modal-footer">
			<a href="#!" class="waves-green btn-flat" onclick="checkTeamMember()">完成</a>
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
		initTeamInfo();
		$("#modal1").modal('open');
	});
</script>