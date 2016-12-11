	

/**
 * 老师界面：向服务器提交课程信息，并给界面的隐藏label添加生成的course_id
 */
function addCourseInfo(teacher_id){
	var course_name=$("#course_name").val();
	var description=$("#description").val();
	$.ajax({
		type : "post",
		url :　"teacher/addCourse",
		data :{
			"teacher_id" : teacher_id,
			"course_name" : course_name,
			"description" : description
		},
		dataType :"json",
		success : function(data){
			$("#course_id").val(data);
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

function homeWorkArrangeTab(){
	 var num=parseInt($("#tab_contents").attr("name"))+1;
	 $("#tab_contents").attr("name",num)
	 num=num.toString();
	 
	 var form ="<div class=\"row\" id=\"test\""+num+">"+"<div class=\"card-panel teal\">"+
	   "<form class=\"col s12\">"+"<span class=\"white-text\">"+"作业"+num+"内容"+"</span>"+
	 "<div class=\"row\">"+
	    "<div class=\"input-field col s6\">"+
		   	"<input id=\"homework_name\" type=\"text\" class=\"validate\">"+
		 	"<label for=\"homework_name\">作业名称</label>" +
	 	"</div>" +
	    "<div class=\"col s6\">"+
	    	"<h6 class=\"white-text\">请选择作业成绩比例</h5>"+
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
	$(".card-panel teal").each(function(index,obj){
		var home_work_name=$(this).children("#homework_name").val()
		var ratio = parseInt($(this).children("#ratio").val())
		var descript = $(this).children("#email").val()
		var fin_date = $(this).children(".datepicker").val()
		var json_item={
			""
		}
		info_list.push(json_item)
	})
	$.ajax({
		type :"get",
		url :"teacher/homeWorksUpdateTab",
		data :{
			"course_id" : course_id,
			"info_list":JSON.stringify(info_list)
		},
		dataType :"html",
		success : function(data){
			homeWorksListUpdate(json_item)
		}			
	});
}
function homeWorksListUpdate(json_item){
	for(var item in json_item){
		var form  = 
			"<li>"+
			    "<div class=\"collapsible-header\"><span class=\"badge\">1</span>"+item.+"</div>"+
			    "<div class=\"collapsible-body\"><p>"+item.+"</p></div>"+
		    "</li>"+
		$("#home_work_show").append(form)
	}
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




				  	