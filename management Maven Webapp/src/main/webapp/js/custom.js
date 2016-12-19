	

/**
 * 老师界面：向服务器提交课程信息，并给界面的隐藏label添加生成的course_id
 */
function addCourseInfo(teacher_id){
	var course_name=$("#course_name").val();
	var description=$("#description").val();
	var total=$("#course_rollCall").val();
	if(course_name==""||description==""||total==""){
		alert("输入信息存在空值");
		$("#modal1").modal('open');
		return;
	}				
	$.ajax({
		type : "post",
		url :　"teacher/addCourse",
		data :{
			"teacher_id" : teacher_id,
			"course_name" :course_name,
			"description" : description,
			"total":total
		},
		dataType :"json",
		success : function(data){
			$("#course_id").val(data);
			$("#modal1").modal('close');
			$("#modal2").modal('open');
		}
	});

}
/**
 * 老师界面：向服务器添加小组配置
 */
function addTeamConfig(){
	var course_id=$("#course_id").val();
	var team_max=$("#t_max").val();
	var team_min=$("#t_min").val();
	var year=$("#t_year").val();
	var class_id=$("#t_class").val();
	if(course_id==""||team_max==""||team_min==""||year==""||class_id==""){
		alert("输入信息存在空值");
		return;
	}
	$.ajax({
		type : "post",
		url :　"teacher/addTeamConfig",
		data :{
			"course_id" : course_id,
			"team_max" : team_max,
			"team_min" : team_min,
			"year" : year,
			"class_id" : class_id
		},
		dataType :"json",
		success : function(data){
			alert("配置完成");
		}
	});	
	$("#modal3").modal('close');
}

/*
 * 老师界面： 实现界面主体内容的加载刷新
 * @param url :请求的页面的地址
 */
function courseTab(url){
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	$.ajax({
		type :"get",
		url :url,
		data :{
			"course_id" : course_id
		},
		dataType :"html",
		success : function(data){
			$(".main").empty();
			$(".main").html(data);
		}			
	});
}
function rollCallTab1(url){
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
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
function rollCallTab(url,roll_order){
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	alert(roll_order)
	alert(course_id)
	$("[name='rollCallTab']").empty();
	$.ajax({
		type :"get",
		url :url,
		data :{
			"course_id" : course_id,
			"roll_order":parseInt(roll_order)
		},
		dataType :"html",
		success : function(data){
			$("[name='rollCallTab']").empty();
			$("[name='rollCallTab']").html(data);
		}			
	});
}
function showStudentResult(data){
	for(index in data){
		x=data[index]
		student_id=data[index].student.student_id
		$("#"+student_id+"1").empty()
		$("#"+student_id+"2").empty()
		for(subitem in x.homeworkGrade){
			homeworkGrade=x.homeworkGrade[subitem]
			for(item in x["homeworkGrade"][subitem])
			if(homeworkGrade.status==2)
				grade=homeworkGrade.grade
			else if(homeworkGrade.status==1)
				grade="未批改"
			else
				grade="未提交";	
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
function homeWorkArrangeTab(){
	 var num = $("#home_work_show").children("li").length+$("#tab_contents").children("#test").length
	 num=num.toString();
	 var form ="<div class=\"row\" id=\"test\">"+"<div class=\"card-panel teal\" id=\"form_content\" name="+num+">"+
	   "<form class=\"col s12\">"+"<span class=\"white-text\">"+"第"+num+"次作业"+"</span>"+
	 "<div class=\"row\">"+
	    "<div class=\"input-field col s6\">"+
		   	"<input id=\"homework_name\" type=\"text\" class=\"validate\">"+
		 	"<label for=\"homework_name\">作业名称</label>" +
	 	"</div>" +
	    "<div class=\"col s6\">"+
	    	"<h5 class=\"white-text\">请选择作业成绩比例</h5>"+
		    "<form action=\"#\">"+
		    	"<p class=\"range-field\">"+
		    		"<input type=\"range\" id=\"ratio\" min=\"0\" max=\"100\" />"+
		    	"</p>"+
		    "</form>"+
	 	"</div>" +
	 "</div>"+
	 "<div class=\"row\">"+
	   "<div class=\"input-field col s12\">"+
	 "<input id=\"email\" type=\"email\" class=\"validate\">"+
	 "<h5 class=\"white-text\">请选择作业提交日期</h5>"+
	 "<input type=\"date\" class=\"datepicker\">"+
	 "<label for=\"email\">基本描述或要求</label></div></div></form></div></div>"
	 $("#tab_contents").append(form)
}

function homeWorksUpdateTab(){
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	var info_list = []
	$("#form_content").each(function(i,obj){
		var home_work_name = $(this).find("#homework_name").val()
		var ratio = parseInt($(this).find("#ratio").val())/100.0
		var homework_num  = parseInt($(this).attr("name"))
		var descript = $(this).find("#email").val()
		var upload_time = $(this).find(".datepicker").val()
		var json_item={
			"homework_name":home_work_name,
			"ratio":ratio,
			"description":descript,
			"upload_time":upload_time,
			"course_id":course_id,
			"homework_id":course_id+parseInt(homework_num)
		}
		info_list.push(json_item)
	})
	$.ajax({
		type :"post",
		url :"teacher/homeWorksUpdateTab",
		//contentType: "application/json",
		contentType: "application/json; charset=utf-8",
		data :JSON.stringify(info_list),
		dataType :"json",
		success : function(data){
			homeWorksListUpdate(info_list)
		}			
	});
}

function homeWorksListUpdate(info_list){
	for(var item in info_list){
		var form  = 
			"<li id=\""+info_list[item].homework_id+"\">"+
			    "<div class=\"collapsible-header\"><span class=\"new badge\" data-badge-caption=\"-百分比\" onclick=\"openModifyRatio(this)\">"+info_list[item].ratio+"</span>"+info_list[item].homework_name+"</div>"+
			    "<div class=\"collapsible-body\"><p>"+info_list[item].description+"</p></div>"+
		    "</li>"
		$("#home_work_show").append(form)
	}
	$("#tab_contents").empty()
}

function getHomeworksInfoByCourseID(){
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	$.ajax({
		type :"get",
		url :"teacher/getHomeworksInfoByCourseID",
		data :{
			"course_id" : course_id
		},
		dataType :"json",
		success : function(data){
			var info_list = data.homworkList;
			homeWorksListUpdate(info_list);
		}			
	});
}

function openModifyRatio(obj){
	$("#modal1").modal("open");
	$('#modal1').attr("name",$(obj).parent().parent().attr("id"));
}

function changeRollCallTime(i){
	$("#rollCallTimeIdentifier").attr("name",i)
	$("#studentListTitle").text("第"+i+"次点名学生名单")
}

function modifyStudentRollCallStat(roll_order){
	//To-do
	updateStudentRollCallStat()
	var rollCallTime=roll_order
	var course_id=$(".course_id").val();
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}
	var mydata=[]
	
	$("[name=\"checkbox\"]").each(function(i,obj){
		if($(this).prop("checked"))
			status=1
		else
			status=0
		var json_item={"course_id":course_id,"roll_order":rollCallTime,"status":status,"student_id":$(this).attr("id")}
		mydata.push(json_item)
	})
	
	$.ajax({
		type :"post",
		url :"teacher/modifyStudentRollCallStat",
		contentType: "application/json; charset=utf-8",
		data :JSON.stringify(mydata),
		dataType :"json",
		success : function(data){
			alert("点名成功")
		}			
	});
}

function updateStudentRollCallStat(student_ids){
	$("[name=\"checkbox\"]").each(function(i,obj){
		if($.inArray($(this).attr("id"),student_ids)!=-1)
			$(this).attr("checked","checked")
	})
}

function modifyHomeworkRatio(obj){
	var ratio = parseInt($("#modal1").find("[name='leftratio']").val())/100.0
	var homework_id = $('#modal1').attr("name")
	if(course_id==""){
		alert("请选择一门课程");
		return;
	}

	$.ajax({
		type :"get",
		url :"teacher/modifyHomeworkRatio",
		data :{
			"homework_id" : homework_id,
			"ratio":ratio
		},
		dataType :"json",
		success : function(data){
			var info_list = data.homeworkList;
			homeWorksListUpdate(info_list);
			$("#modal1").modal("close");
		}			
	});
}

//改变助教
function changeAssistant(team_id,obj){
	var assistant_id=$(obj).find("option:selected").val();
	$.ajax({
		type:'post',
		url:'teacher/setTeamAssistant',
		data:{
			"team_id":team_id,
			"assistant_id":assistant_id
		},
		dataType:"json"
	});
	
}

function showHomeworkList(items){
	teamHomeworks=items.teamHomeworks
	homework_names=items.homework_names
	homework_ratios=items.homework_ratios
	for(var i in teamHomeworks){
		teamHomeworks[i].homework_name=homework_names[i];
		teamHomeworks[i].ratio=homework_ratios[i];
		teamHomeworks[i].submit_time=teamHomeworks[i].submit_time.substr(0,10)
	}
	
	items=teamHomeworks
    $("#table-body").empty();
    for(var item in items){
    	var tr="<tr><td>"+items[item].team_id+"</td>"+
    	"<td>"+items[item].submit_time+"</td>"+
    	"<td><a class='waves-effect waves-light btn' name='modify'>"+items[item].homework_name+"</a></td>"+
    	"<td>"+items[item].submitter+"</td>"+
    	"<td>"+items[item].grade+"</td>"+
    	"<td>"+items[item].correctinfo+"</td>"+
    	"<td>"+items[item].student_comment+"</td>" +
    	"<td>"+items[item].ratio*items[item].grade+"</td></tr>"
		$("#table-body").append(tr);
    }
}

function conditionSearch(obj){
	var time=$("#search_time1").val()
	var homework_name=$("#homework_name1").val()
	var team_number=$("#team_number1").val()
	
  	var tr="<tr>"
  	  "<th>提交时间</th>"
  	  "<th data-field=\"name\">小组编号</th>"+
  	  "<th data-field=\"pwd\">作业名称</th>"+
  	  "<th data-filed=\"role\">提交人姓名</th>"+
  	  "<th>成绩</th>"+
  	  "<th>批改说明</th>"+
  	  "<th>学生意见</th>"+
  	  "<th>期末占分</th>"+
  	"</tr>"
  	$("#table-head").empty();
  	$("#table-head").append(tr);
	alert(homework_name)
	$.ajax({
		type:"post",
		url:"teacher/conditionSearch",
		dataType:"json",
		data:{
			"submit_time":time,
			"homework_name":homework_name,
			"team_id":team_number
		},
		success: function(data){
			alert(data.homework_names.length)
		    var items=data;
		    showHomeworkList(items);
		}
	});
}

function allSearch(){
  	var tr="<tr>"+
    	  "<th>提交时间</th>"+
    	  "<th data-field=\"name\">小组编号</th>"+
    	  "<th data-field=\"pwd\">作业名称</th>"+
    	  "<th data-filed=\"role\">提交人姓名</th>"+
    	  "<th>成绩</th>"+
    	  "<th>批改说明</th>"+
    	  "<th>学生意见</th>"+
    	  "<th>期末占分</th>"+
    	"</tr>"
	$("#table-head").empty();
	$("#table-head").append(tr);
	$.ajax({
		type:"get",
		url:"teacher/allSearch",
		dataType:"json",
		success: function(data){
		    var items=data;
		    showHomeworkList(items);
		}
	});
}

function computeStudentFinalGrade(){
  	var tr="<tr>"+
  	  "<th>提交时间</th>"+
  	  "<th>小组编号</th>"+
  	  "<th>作业名称</th>"+
  	  "<th>提交人姓名</th>"+
  	  "<th>成绩</th>"+
  	  "<th>批改说明</th>"+
  	  "<th>学生意见</th>"+
  	  "<th>期末占分</th>"+
  	"</tr>"
	$("#table-head").empty();
	$("#table-head").append(tr);
}			



