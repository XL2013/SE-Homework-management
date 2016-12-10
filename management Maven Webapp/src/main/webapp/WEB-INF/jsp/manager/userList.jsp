<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  
 <div class="container">
 	<div class="row">
 	  <div class="col offset-l2 l10">
 	  	<h4 class="center-align" id="title" name="${data.role}"></h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th data-field="id">用户ID</th>
 	  	  	  <th data-field="name">用户姓名</th>
 	  	  	  <th data-field="pwd">用户密码</th>
 	  	  	  <th data-filed="role">用户角色</th>
 	  	  	  <th>修改</th>
 	  	  	  <th>删除</th>
 	  	  	  <th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody id="table-body">
 	  	  	<c:forEach var="user" items="${data.userList}">
 	  	  	  <tr class="hoverable striped" id="${user.user_id}">
 	  	  	  	<td>${user.user_id}</td>
 	  	  	  	<td>${user.user_name}</td>
 	  	  	  	<td>${user.user_pwd}</td>
 	  	  	  	<td>${user.user_role}</td>
 	  	  	  	<td><a class="waves-effect waves-light btn" name="modify">修改</a></td>
 	  	  	  	<td><a class="waves-effect waves-light btn" name="delete">删除</a></td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	<a class="waves-effect waves-light btn" name="add">增加</a>
 	  </div>
 	</div>
 	
 		<div id="modal1" class="modal modal-fixed-footer">
		<div class="modal-content">
			<div class="row">
			  <div class="input-field col s6">
			    <input id="m_user_id" type="text" class="validate">
			    <label for="m_user_id" id="m_user_id_label">User ID</label>
			  </div>
			  <div class="input-field col s6">
			    <input id="m_user_name" type="text" class="validate">
			    <label for="m_user_name" id="m_user_name_label">User Name</label>
			  </div>
			</div>
			<div class="row">
			  <div class="input-field col s6">
			    <input id="m_user_pwd" type="text" class="validate">
			    <label for="m_user_pwd" id="m_user_pwd_label">User PWD</label>
			  </div>
			  <div class="input-field col s6">
			    <input id="m_user_role" type="text" class="validate">
			    <label for="m_user_role" id="m_user_role_label">User Role</label>
			  </div>
			</div>

		</div>
		<div class="modal-footer">
			<a href="#!" class="waves-green btn-flat" onclick="actionComplete()" nmae="modify" id="complete">完成</a>
		</div>
	</div>
	
 </div>
 
 
 <script type="text/javascript">
 	modifyTitle()
	$('#modal1').modal({
		dismissible: false, // Modal can be dismissed by clicking outside of the modal
		opacity: .5, // Opacity of modal background
		in_duration: 300, // Transition in duration
		out_duration: 200, // Transition out duration
		starting_top: '4%', // Starting top style attribute
		ending_top: '10%', // Ending top style attribute
	});
	
	$("[name='modify']").click(function(){
		//
		//$('#title').text("hehe");
		//$("#m_user_name").text("liliang");
		$('#m_user_id').attr("readonly","readonly")
		$('#m_user_role').attr("readonly","readonly")
		$("#complete").attr("name","modify");
		$("#m_user_id_label").text($(this).parent().parent().children("td:eq(0)").text());
		$("#m_user_id").val($(this).parent().parent().children("td:eq(0)").text());
		$("#m_user_name_label").text($(this).parent().parent().children("td:eq(1)").text());
		$("#m_user_name").val($(this).parent().parent().children("td:eq(1)").text());
		$("#m_user_pwd_label").text($(this).parent().parent().children("td:eq(2)").text());
		$("#m_user_pwd").val($(this).parent().parent().children("td:eq(2)").text());
		$("#m_user_role_label").text($(this).parent().parent().children("td:eq(3)").text());
		$("#m_user_role").val($(this).parent().parent().children("td:eq(3)").text());
		$("#modal1").modal("open");
	});
	
	$("[name='delete']").click(function(){
		//$('#title').text($(this).parent().parent().children("td:eq(0)").text());
		deleteUser($(this).parent().parent().children("td:eq(0)").text());
	})
	
	$("[name='add']").click(function(){
		$("#complete").attr("name","add");
		$('#m_user_role').attr("readonly","readonly");
		$("#m_user_id_label").text("User ID");
		$("#m_user_name_label").text("User Name");
		$("#m_user_pwd_label").text("User PWD");
		$("#m_user_role_label").text("User Role");
		$("#m_user_role").val($("#title").attr("name"));
		$("#modal1").modal("open");
	})
</script>

