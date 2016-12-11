<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h5>小组列表</h5>
	<div class="collection">	
		<c:forEach var="team" items="${teamList}">
			 <a href="#!" class="collection-item" id="homework-item" team_id="${team.team_id}"><span class="badge">${team.course_name}</span>${team.team_id}</a>
	    </c:forEach>   
       <input id="team_id" type="text" hidden>
       
  	</div>
	 
    <h5>作业列表</h5>
    <div class="divider"></div>
    <div id="homeworkList" class="col s12">
    	<table>
	  	   <thead>
		    <tr>
		      <th>作业名称</th>
		      <th>作业id</th>
		      <th>作业状态</th>
		      <th>成绩</th>
		      <th>查看</th>
		      <th>提交</th>
		      <th>评论</th>
		    </tr>
		  </thead>
		   <tbody id="t-homeworkBody">
			<tr>
		      <th>java第一次作业</th>
		      <th>2013211001</th>
		      <th>已提交</th>
		      <th>90</th>
		      <th>
		      	<a id="showHomework" class="btn-floating red" onclick="">  <i class="material-icons" >visibility</i></a>
		      </th>
		      <th>
		        <a id="submitHomework" class="btn-floating red" onclick="">  <i class="material-icons" >present_to_all</i></a>
		      </th>
		      <th>
		        <a id="commentHomeWork" class="btn-floating red" onclick="">  <i class="material-icons" >comment</i></a>
		      </th>
		    </tr>
			</tbody>
		</table>
    </div> <!-- end of homeworkList -->
    
    <div class="modal modal-fixed-footer" id="homeworkInfo-modal">
      <div class="modal-content">
      	<div id="modal-title">
      		<h5 id="m-homework_name">java第一次作业<span class="badge" id="m-homework_id">201301</span></h5>
      		<div class="divider"></div>
      		<div class="row">
      		  <div class="col s6">
	      		<label>发布时间:</label>
	      		<a id="m-releaseTime">2013/06/19</a>
	      	  </div>
	      	 <div class="col s6">
	      		<label>提交时间:</label>
	      		<a id="m-submitTime">2013/06/19</a>
	      	  </div>
	      	  <div class="col s12">
	      	  	   <h5>作业文件：</h5>
	      	       <ul id="m-files">
	      	       	  <li><a href="#!">test.java</a></li>
	      	       	  <li><a href="#!">test.java</a></li>
	      	       	  <li><a href="#!">test.java</a></li>
   				   </ul>
	      	  </div>
	      	  <div class="divider"></div>
	      	  <div class="col s12">
	      	  	   <h5>批改说明</h5>
	      	  	   <a id="m-correctInfo"> 这次作业写得不错</a>
	      	  </div>
	      	  <div class="col s12">
	      	  	   <h5>学生意见</h5>
	      	  	   <a id="m-comment">作业太多</a>
	      	  </div>
      		</div>
      	</div>
      </div>
      <div class="modal-footer ">
	      	<a>本次成绩：</a>
	      	<a id="m-homework_grade">90</a>
	      	<a>总成绩：</a>
	      	<a id="m-homework_total">10</a>
	      	<a>提交者：</a>
	      	<a  id="m-homework_submitter">周志善</a>
      	<a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="">完成</a>
      </div>
    </div><!-- end of homeworkInfo_modal -->
    
     <div class="modal modal-fixed-footer" id="homeworkSubmit-modal">
      <div class="modal-content">
     	<div id="modal-title">
     		<h5 id="m-homework_name">java第一次作业<span class="badge" id="m-homework_id">201301</span></h5>
     	</div>
   		<div class="divider"></div>
   		<div class="row">
   		  <div class="col s6">
    		<label>发布时间:</label>
    		<a>2013/06/19</a>
     	  </div>
     	  <div class="col s6">
     		<label>提交时间:</label>
     		<a>2013/06/19</a>
     	  </div>
     	  <div class="col s12">
	     	  	<form id="homeworkFileForm" action="student/uploadHomeWorkFile">
				    <div class="file-field input-field">
				      <div class="btn">
				        <span>File</span>
				        <input type="file" multiple>
				      </div>
				      <div class="file-path-wrapper">
				        <input class="file-path validate" type="text" placeholder="Upload one or more files">
				      </div>  
	      			</div>
	      			<button type="submit" class="btn">提交</button>
	      		</form>
    		   <h5>作业文件：</h5>
    	       <ul>
    	       	  <li><a href="#!">test.java</a></li>
    	       	  <li><a href="#!">test.java</a></li>
    	       	  <li><a href="#!">test.java</a></li>
			   </ul>   
    	 </div>  <!-- end of col --> 		 
  		</div><!-- end of row -->
  	  </div><!-- end of content -->
	  <div class="modal-footer ">
	      <a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="">完成</a>
	   </div>
	 </div><!-- end of homeworkSubmit_modal -->
	 
	 <div class="modal" id="comment-modal">
	 	<div class="modal-content">
 		  <div class="row">
     		 <div class="input-field col s12">
       		 <textarea id="comment-text" class="materialize-textarea"></textarea>
       		 <label for="comment-text">请填写你的意见</label>
       	   </div>
    	  </div>	 		
	 	</div>
	 	<div class="modal-footer">
	 	   <a href="#!" class="modal-action modal-close waves-green btn-flat" onclick="">完成</a>
	   </div>
	 </div><!-- end of comment modal -->
    
</div><!-- end of container -->
<script>
 $('.modal').modal({
	      dismissible: true, // Modal can be dismissed by clicking outside of the modal
	      opacity: .5, // Opacity of modal background
	      in_duration: 300, // Transition in duration
	      out_duration: 200, // Transition out duration
	      starting_top: '4%', // Starting top style attribute
	      ending_top: '10%', // Ending top style attribute
	    }
  );
  

  $(".collection-item").click(function(){
  		var team_id=$(this).attr('team_id');
  		$("#team_id").val(team_id);
  		getHomeworks();
  });
</script>
