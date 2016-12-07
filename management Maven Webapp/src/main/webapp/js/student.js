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
				$(".main").html(data);
		}
	});
}

function searchMember(){
	var info=$("#searchInfo").val();
	var course_id=$(".course_id").val();
	$.ajax({
		type :"get",
		url:"student/searchResult",
		data:{
			"info":info,
			"course_id" :course_id
		},
		dataType:"json",
		success : function(data){
			alert("search");
			var members=data.members;
			$("#result-body").empty();
			for(var member in members){
				var tr="<tr>"+"<th>"+member.student_id+"</th>"+"<th>"+members.student_name+"</th>"
						+"<th><a class=\"btn-floating red\"><i class=\"material-icons\" onclick="
						+"\"add('"+member.student_id +"','this')"+">add</i></a></th>";
				$("#result-body").append(tr);
			}
		}
	});
}
function addMember(student_id,obj){
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
			alert("add");
			$(obj).remove(); //增加后删除
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
	if(min>count||max<count) {
		alert("学生数不匹配");
		return;
	}
	$("#modal1").modal('close');
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
			$("#m-course_name").text(data.course_name);
			$("#m-team_id").text(data.team.team_id);
			$("#m-monitor_id").text(data.team.monitor_id);
			$("#m-max").text(data.teamConfig.team_max);
			$("#m-min").text(data.teamConfig.team_min);
			$("#m-email").text(data.team.email);
			//todo :add Members
		    var members=data.memebers;
		    $("#member-body").empty();
		    for(var member in members){
				var tr="<tr> <th>"+member.student_id+"</th>"+"<th>"+member.student_name+"</th>"+"<th>"+member.class_id
						+"</th> </tr>";
				$("#member-body").append(tr);
		    }
		}
	});
}

function createTeamInfoCard(){
		var card_id=$(".card").length+1;
		var card="<div class=\"col l6\">"+
		  "<div class=\"card\" id=\""+card_id+"\">"+
		  	"<div class=\"card-content\">"+
		  	"<a class=\"card-title\" ><span class=\"badge\"></span></a> "+
		  	"<table><thead><tr><th>成员学号</th> <th>成员姓名</th><th>成员班级</th></tr></thead>"+
			 " <tbody></tbody></table></div>"+			 		  	
		  	"<div class=\"card-action\">"+
		  	  "<a href=\"#\" id=\"c-email\"></a></div></div></div>";
		$("#team_cards").append(card);
		return card_id;
}

function showTeamInfo(){
	var team_id=$("#m-team_id").text();
	$.ajax({
		type:"get",
		url:"student/getTeamInfo",
		data:{
			"team_id":team_id
		},
		dataType:"json",
		success : function(data){
			var card_id=createTeamInfoCard();
			var cardContent=$("#"+card_id).children(".card-content");
			var cardAction=$("#"+card_id).children(".card-actoin");
			
			//add members
			var members=data.memebers;
			var tbody=cardContent.find("tbody");
		    for(var member in members){
				var tr="<tr> <th>"+member.student_id+"</th>"+"<th>"+member.student_name+"</th>"+"<th>"+member.class_id
						+"</th> </tr>";
				tbody.append(tr);
		    }
		    cardContent.find(".card-titile").text(data.course_name);
		    cardContent.find(".badge").text(data.team.team_id);
		    cardAction.find("c-email").text(data.team.email);		    
			
		}
	});
}
