function assistantTab(url){
	var assistant_id=$("#assistant_id").text();
	$.ajax({
		type :"get",
		url :url,
		data :{
			"assistant_id" : assistant_id
		},
		dataType :"html",
		success : function(data){
				$(".main").html(data);
		}
	}); 
}
function changeTeamLeader(student_id,team_id){
	$.ajax({
		type:"post",
		url:"assistant/changeTeamLeader",
		data:{
			"student_id":student_id,
			"team_id":team_id
		},
		
	});
	
}

function getTeamHomeworks(team_id){

	$.ajax({
		type:"get",
		url:"assistant/getTeamHomeworks",
		data:{
			"team_id":team_id
		},
		dataType:"json",
		success: function(data){
			var homeworks=data.homeworks;
			$("#t-homeworkBody").empty();
			for(var i in homeworks){
				var tr="<tr id=\""+homeworks[i].homework_id+"\"><td>"+homeworks[i].homework_name+"</td><td>"+homeworks[i].homework_id+"</td><td>"+homeworks[i].status+"</td><td>"+homeworks[i].grade+
					"</td><td><a id=\"showHomework\" class=\"btn-floating red\" onclick=\"showHomeworkInfo(this)\">  <i class=\"material-icons\" >visibility</i></a></td>"
				  +"<td>"+
				   "<a id=\"commentHomeWork\" class=\"btn-floating red\" onclick=\"commentHomework(this)\">  <i class=\"material-icons\" >comment</i></a>"+
				    "  </td></tr>";
				$("#t-homeworkBody").append(tr);
			}
		}
	});
}
//这里用studentController的方法，以后可能抽出来这些函数
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

function commentHomework(obj){
	var homework_id=$(obj).parent().parent().children("td:eq(1)").text();
	var status=$(obj).parent().parent().children("td:eq(2)").text();
	if(status=="未提交"){
		alert("作业未提交,无法评分");
		return;
	}
	$("#comment-homework_id").val(homework_id);
	$("#comment-modal").modal('open');
}
function evaluateTeamHomework(){
	var team_id=$("#team_id").val();
	var homework_id=$("#comment-homework_id").val();
	var comment=$("#comment-text").val();
	var grade=$("#comment-grade").val();
	var ex = /^\d+$/;
	if(!ex.test(grade)||grade<0||grade>100){
		alert("输入不合法");
		return;
	}
	var tr="#"+homework_id;
	$(tr).children("td:eq(2)").text("已批改");
	$(tr).children("td:eq(3)").text(grade);
	$.ajax({
		type:"post",
		url:"assistant/evaluateTeamHomework",
		data:{
			"comment":comment,
			"team_id":team_id,
			"homework_id":homework_id,
			"grade":grade
		},
		dataType:"json",	
	});
}

function fileDownload(obj,team_id,homework_id){
	var file_name=$(obj).text();

	var url="file/download?team_id="+team_id+"&homework_id="+homework_id+"&file_name="+file_name;
	window.location.href=url;
}



//引用老师界面的学生名单函数
function rollCallTab1(url,course_id){
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	$(".course_id").val(course_id);
	$.ajax({
		type :"get",
		url :url,
		dataType :"html",
		data :{
			"course_id" : course_id
		},
		success : function(data){
			$("[name='rollCallTab']").empty();
			$("[name='rollCallTab']").html(data);
		}			
	});
}
//引入的老师界面的函数
function showStudentResult(data){
	for(index in data){
		x=data[index]
		student_id=data[index].student.student_id
		$("#"+student_id+"1").empty()
		$("#"+student_id+"2").empty()
		for(subitem in x.homeworkGrade){
			homeworkGrade=x.homeworkGrade[subitem]
			for(item in x["homeworkGrade"][subitem])
			if(homeworkGrade.status==1)
				grade=homeworkGrade.grade
			else
				grade="未批改"
			var li="<li><a href=\"#!\">"+homeworkGrade.homework_name+" ："+grade+"</a></li>"+
			"<li class=\"divider\"></li>"
			$("#"+student_id+"1").append(li)
		}
		for(subitem in x.rollCall){
			rollCall=x.rollCall[subitem]
			if(rollCall.rollcall_state==2){
				rollcall="未点名"
			}
			else if(rollCall.rollcall_state==1){
				rollcall="到"
			}
			else{
				rollcall="缺席"
			}
			var li="<li><a href=\"#!\">"+"第"+rollCall.rollcall_ID+"次点名："+rollcall+"</a></li>"+
			"<li class=\"divider\"></li>"
			$("#"+student_id+"2").append(li)
		}
	}
	

}