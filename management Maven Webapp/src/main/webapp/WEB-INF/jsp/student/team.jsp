<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<p>未分配小组的课程</p>
	<div class="collection">
    <a href="#!" class="collection-item"><span class="badge">创建小组</span>java</a>
    <a href="#!" class="collection-item"><span class="badge">创建小组</span>c++</a>
  	</div>
	<div class="divider"></div>	
	
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
			  	<a id="m-min">4</a>
			  </p>
			   <p>
			  	<label>联系方式:   </label>
			  	<a id="m-email">973945379@qq.com</a>
			  </p>
			  </div>
			</div>
			<div class="divider"></div>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-green btn-flat">取消</a>
			<a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="addCourseInfo(${teacher_id})">下一步</a>
		</div>
	</div>
	<script type="text/javascript">
		 $('#modal1').modal({
		      dismissible: true, // Modal can be dismissed by clicking outside of the modal
		      opacity: .5, // Opacity of modal background
		      in_duration: 300, // Transition in duration
		      out_duration: 200, // Transition out duration
		      starting_top: '4%', // Starting top style attribute
		      ending_top: '10%', // Ending top style attribute
		      ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
		        alert("Ready");
		      },
		      complete: function() { alert('Closed'); } // Callback for Modal close
		    }
  		);
  		$(".collection-item").click(function(){
  			$("#modal1").modal('open');
  		});
	</script>
</div>