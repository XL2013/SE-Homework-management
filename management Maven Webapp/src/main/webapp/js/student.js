function studentTab(url){
	var student_id=$("#student_id").text();
	$.ajax({
		type :"get",
		url :url,
		data :{
			"student_id" : student_id
		},
		dataType :"html",
		success : function(data){
				$(".main").html(data);
		}
	});
}
function sc(url){
	var student_id=$("#student_id").text();
	var course_id=$(".course_id").val();
	$.ajax({
		type :"get",
		url :url,
		data :{
			"student_id" : student_id,
			"course_id":course_id
		},
		dataType :"html",
		success : function(data){
				$(".main").empty();
				$(".main").html(data);
		}
	});
}

function searchMember(){
	var info=$("#searchInfo").val();
	var course_id=$("#m-course_id").text();
	$.ajax({
		type :"post",
		url:"student/searchResult",
		data:{
			"info":info,
			"course_id" :course_id
		},
		dataType:"json",
		success : function(data){
			var members=data.members;
			$("#result-body").empty();
			for(var i in members){
				var tr="<tr>"+"<th>"+members[i].student_id+"</th>"+"<th>"+members[i].student_name+"</th>"
						+"<th><a class=\"btn-floating red\"><i class=\"material-icons\" onclick="
						+"\"addMember('"+members[i].student_id +"',this)\""+">add</i></a></th>";
				console.log(tr);
				$("#result-body").append(tr);
			}
		}
	});
}
function addMember(student_id,obj){
	if(!checkTeamMember())
		return;
	var team_id=$("#m-team_id").text();
	$.ajax({
		type :"post",
		url:"student/addTeamMember",
		data:{
			"student_id":student_id,
			"team_id": team_id
		},
		dataType:"json",
		success : function(data){
			$(obj).parent().parent().parent().remove(); //增加后删除
			//这里在成员栏增加相应tr
			var member=data.student;
			var tr="<tr> <th>"+member.student_id+"</th>"+"<th>"+member.student_name+"</th>"+"<th>"+member.class_id
			+"</th> </tr>";
			$("#member-body").append(tr);
		}
	});
}

function checkTeamMember(){
	//动态获取数据行数
	var count=$("#member-body tr").length;
	var min=$("#m-min").text();
	var max=$("#m-max").text();
	if(max<=count) {
		alert("成员已达上限");
		return false;
	}
	return true;

}
function initTeamInfo(course_id){
	var student_id=$("#student_id").text();
	$.ajax({
		type:"get",
		url:"student/initTeamInfo",
		data:{
			"course_id":course_id,
			"student_id":student_id
		},
		dataType:"json",
		success: function(data){
			$("#m-course_id").text(course_id);
			$("#m-course_name").text(data.course_name);
			$("#m-team_id").text(data.team.team_id);
			$("#m-monitor_id").text(data.team.leader_id);
			$("#m-max").text(data.teamConfig.team_max);
			$("#m-min").text(data.teamConfig.team_min);
			$("#m-email").val(data.team.email);
			//todo :add Members
		    var members=data.members;
		    $("#member-body").empty();
		    for(var i in members){
				var tr="<tr> <th>"+members[i].student_id+"</th>"+"<th>"+members[i].student_name+"</th>"+"<th>"+members[i].class_id
						+"</th> </tr>";
				$("#member-body").append(tr);
		    }
		}
	});
}
function  emailSubmit(){

	var team_id=$("#m-team_id").text();
	var email=$("#m-email").val();
	$.ajax({
		type:"post",
		url:"student/emailChange",
		data:{
			"team_id":team_id,
			"email":email
		},
		dataType:"json"
	});
	
	studentTab('student/team');
}
