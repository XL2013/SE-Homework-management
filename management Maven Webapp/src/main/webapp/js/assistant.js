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