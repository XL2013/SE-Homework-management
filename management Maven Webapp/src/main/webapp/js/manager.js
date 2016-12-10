function managerTab(url,user_role){
	$.ajax({
		type :"get",
		url :url,
		data :{
			"user_role":user_role,
		},
		dataType :"html",
		success : function(data){
			$(".main").html(data);
		}
	});
}
function modifyTitle(){
	var user_role=$("#title").attr("name");
	if(user_role==1){
		$('#title').text("老师名单");
	}
	else if(user_role==2){
		$('#title').text("助教名单");
	}
	else{
		$('#title').text("学生名单");
	}
	
}

function deleteUser(user_id) {
	$.ajax({
		type:"post",
		url:"manager/deleteUser",
		data:{
			"user_id":user_id,
		},
		dataType:"json",
		success: function(data){
			$("#"+user_id).remove();
		}
	})
}
function actionComplete(){
	if($("#complete").attr("name")=="modify")
		$.ajax({
			type:"post",
			url:"manager/modifyUser",
			data:{
				"user_id":$("#m_user_id").val(),
				"user_name":$("#m_user_name").val(),
				"user_pwd":$("#m_user_pwd").val(),
				"user_role":$("#m_user_role").val()
			},
			dataType:"json",
			success: function(data){
				$("#"+$("#m_user_id").val()).children("td:eq(1)").text($("#m_user_name").val())
				$("#"+$("#m_user_id").val()).children("td:eq(2)").text($("#m_user_pwd").val())
				$("#"+$("#m_user_id").val()).children("td:eq(3)").text($("#m_user_role").val())
				$("#modal1").modal('close');
				$('#m_user_id').removeAttr("readonly")
				$('#m_user_role').removeAttr("readonly")
			}
		})
	else if($("#complete").attr("name")=="add"){
		$.ajax({
			type:"post",
			url:"manager/addUser",
			data:{
				"user_id":$("#m_user_id").val(),
				"user_name":$("#m_user_name").val(),
				"user_pwd":$("#m_user_pwd").val(),
				"user_role":$("#m_user_role").val()
			},
			dataType:"json",
			success: function(data){
				var tr="<tr>"+"<td>"+$("#m_user_id").val()+"</td>"+"<td>"+$("#m_user_name").val()+"</td>"+"<td>"+$("#m_user_pwd").val()+"</td>"+"<td>"+$("#m_user_role").val()+"</td>"+"<td><a class='waves-effect waves-light btn' name='modify'>修改</a></td><td><a class='waves-effect waves-light btn' name='delete'>删除</a></td>"+"</tr>"
				
				$("#table-body").append(tr);
				
				$('#m_user_role').removeAttr("readonly");
				$("#modal1").modal('close');
			}
		})
	}
	
}
function showUserList(user_role){
	$.ajax({
		type:"get",
		url:"manager/showUserList",
		data:{
			"user_role":user_role
		},
		dataType:"json",
		success: function(data){
		    var members=data.memebers;
		    $("#table-body").empty();
		    for(var member in members){
				var tr="<tr>"+"<td>"+member.user_id+"</td>"+"<td>"+member.user_name+"</td>"+"<td>"+member.user_pwd+"</td>"+"<td>"+member.user_role+"</td>"+"</tr>"
				$("#table-body").append(tr);
		    }
		}
	});
}