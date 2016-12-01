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
	$.ajax({
		type :"get",
		url:"student/result",
		data:{
			"info":info
		},
		dataType:"json",
		success : function(data){
			alert("search");
			//这里添加显示搜索结果的html拼接代码
		}
	});
}
function addMember(student_id){
	$.ajax({
		type :"get",
		url:"student/getStudent",
		data:{
			"info":info
		},
		dataType:"json",
		success : function(data){
			alert("add");
			//这里在成员栏增加相应tr
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
function initTeamInfo(){
	
}

function createTeamInfoCard(){
	
}
