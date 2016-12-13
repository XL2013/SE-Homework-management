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



function getHomeworks(){
	var team_id=$("#team_id").val();
	$.ajax({
		type:"get",
		url:"student/getTeamHomeworks",
		data:{
			"team_id":team_id
		},
		dataType:"json",
		success: function(data){
			var homeworks=data.homeworks;
			$("#t-homeworkBody").empty();
			for(var i in homeworks){
				var tr="<tr><td>"+homeworks[i].homework_name+"</td><td>"+homeworks[i].homework_id+"</td><td>"+homeworks[i].status+"</td><td>"+homeworks[i].grade+
					"</td><td><a id=\"showHomework\" class=\"btn-floating red\" onclick=\"showHomeworkInfo(this)\">  <i class=\"material-icons\" >visibility</i></a></td>"
				  +"<td>"+
				   "<a id=\"submitHomework\" class=\"btn-floating red\" onclick=\"submitHomeworkInfo(this)\">  <i class=\"material-icons\" >present_to_all</i></a>"+
				   "</td>"+
				   "<td>"+
				   "<a id=\"commentHomeWork\" class=\"btn-floating red\" onclick=\"commentHomework(this)\">  <i class=\"material-icons\" >comment</i></a>"+
				    "  </td></tr>";
				$("#t-homeworkBody").append(tr);
			}
		}
	});
}

function showHomeworkInfo(obj){
	var team_id=$("#team_id").val();
	var homework_id=$(obj).parent().parent().children("td:eq(1)").text();
	$.ajax({
		type:"get",
		url:"student/getTeamHomework",
		data:{
			"team_id":team_id,
			"homework_id":homework_id
		},
		dataType:"json",
		success: function(data){
			//这里是作业本身信息
			var homework=data.homework;
			var ratio=homework.ratio;
			$("#m-releaseTime").text(homework.release_time.substr(0,10));
			$("#m-submitTime").text(homework.upload_time.substr(0,10));
			$("#m-homework_name").text(homework.homework_name);
			
			//这里是小组作业信息
			var team_homework=data.team_homework;
			$("#m-homework_id").text(team_homework.homework_id);
			$("#m-correctInfo").text(team_homework.correctInfo);
			$("#m-comment").text(team_homework.student_comment);
		    var grade=team_homework.grade;
		    var total=grade*ratio;
			$("#m-homework_grade").text(grade);
			$("#m-homework_total").text(total);
			$("#m-homework_submitter").text(team_homework.submitter);
			//这里是作业已提交的文件信息
			//todo :设置文件下载链接
			var files=data.files;
			$("#m-files").empty();
			for(var i in files){
				var li="<li><a href=\"#!\" onclick=\"fileDownload(this,'"+team_id+"','"+team_homework.homework_id+"')\">"+files[i].file_name+"</a></li>";
				$("#m-files").append(li);
			}
			
			
		}
		
	});
	$('#homeworkInfo-modal').modal('open');
	
}
function submitHomeworkInfo(obj){
	var team_id=$("#team_id").val();
	var homework_id=$(obj).parent().parent().children("td:eq(1)").text();
	$.ajax({
		type:"get",
		url:"student/getTeamHomework",
		data:{
			"team_id":team_id,
			"homework_id":homework_id
		},
		dataType:"json",
		success: function(data){
			//这里是作业本身信息
			var homework=data.homework;
			var ratio=homework.ratio;
			$("#s-releaseTime").text(homework.release_time.substr(0,10));
			$("#s-submitTime").text(homework.upload_time.substr(0,10));
			$("#s-homework_name").text(homework.homework_name);
			console.log(homework);
			//这里是小组作业信息
			var team_homework=data.team_homework;
			$("#s-homework_id").text(team_homework.homework_id);
			//这里是作业已提交的文件信息
			//todo :设置文件下载链接
			var files=data.files;
			$("#m-submit_file").empty();
			$("#f-team_id").val(team_id);
			$("#f-homework_id").val(team_homework.homework_id);
			for(var i in files){
				var li="<li><a href=\"#!\" onclick=\"fileDownload(this,'"+team_id+"','"+team_homework.homework_id+"')\">"+files[i].file_name+"</a></li>";
				$("#m-submit_file").append(li);
			}						
		}
		
	});
	$('#homeworkSubmit-modal').modal('open');
}
function commentHomework(obj){

	var homework_id=$(obj).parent().parent().children("td:eq(1)").text();
	$("#comment-homework_id").val(homework_id);
	$("#comment-modal").modal('open');
}
function submitComment(){
	var team_id=$("#team_id").val();
	var homework_id=$("#comment-homework_id").val();
	var comment=$("#comment-text").val();
	$.ajax({
		type:"post",
		url:"student/setTeamHomeworkComment",
		data:{
			"comment":comment,
			"team_id":team_id,
			"homework_id":homework_id
		},
		dataType:"json",	
	});
}

//表单方面

function checkForm(){
	var file_names=$(".file-path").val();
	var team_id=$("#f-team_id").val();
	var homework_id=$("#f-homework_id").val();
	var isExist=false;
	if(file_names==""){
		alert("请选择一个文件");
		return false;
	}
	else{//测试文件是否存在
		$.ajax({
			type:"post",
			async:false,
			url:"student/checkHomeworkFile",
			data:{
				"team_id":team_id,
				"homework_id":homework_id,
				"file_names":file_names
			},
			dataType:"json",
			success: function(data){
				isExist=data.isExist;
			}
		});
		if(isExist)
			return confirm("文件已存在，是否覆盖？");
	}
}
function showSubmitFile(team_id,homework_id){
	console.log("这里更新了 文件列表");
}
function submitTeamHomework(){
	var team_id=$("#f-team_id").val();
	var homework_id=$("#f-homework_id").val();
	$.ajax({
		type:"post",
		url:"student/updateTeamHomework_status",
		data:{
			"team_id":team_id,
			"homework_id":homework_id
		}
	});
}
function fileDownload(obj,team_id,homework_id){
	var file_name=$(obj).text();

	var url="file/download?team_id="+team_id+"&homework_id="+homework_id+"&file_name="+file_name;
	window.location.href=url;
}


function showHomeworkGrade(course_id,student_id){
	$.ajax({
		 type:"get",
		 url:"student/getStudentHomeworkGrade",
		 data:{
			"course_id":course_id,
			"student_id":student_id
		 },
		 dataType:"json",
		 success : function(data){
			 var gradeBody="	<h4>成绩列表</h4>"+
				  	"<div class=\"divider\"></div>"+
				  	"<table><thead><tr><th>作业序号</th><th>作业名字</th><th>成绩</th></tr></thead>"+
					 "<tbody id=\"t-homework_grade_body\"></tbody></table>"+		
					"<div class=\"divider\"></div>"+
					"<div class=\"row\">"+
					 "<div class=\"col s4\">"+
					  " <h6>总点到次数： <a id=\"rollCallTimes\">4</a></h6>"+		  
					 "</div>"+
					" <div class=\"col s4\">"+
					 "  <h6>缺勤次数： <a id=\"absenceTimes\">3</a></h6>	"+	  
					" </div>"+
					" <div class=\"col s4\">"+
					   "<h6>课程总成绩：<a id=\"totalGrade\">90</a></h6></div></div>	"  			 
					 ;
			}
		
	});
	
}
