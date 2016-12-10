<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 <div class="container">
 	<div class="row">
 	  <div class="col offset-l2 l10">
 	  	<h4 class="center-align">小组名单</h4>
 	  	<div class="divider"></div>
 	    <table class="centered ">
 	  	  <thead>
 	  	  	<tr>
 	  	  	  <th>小组ID</th>
 	  	  	  <th >组长id</th>
 	  	  	  <th >助教</th>
 	  	  	</tr>
 	  	  </thead>
 	  	  <tbody >
 	  	  	<c:forEach var="teamInfo" items="${data.teamInfos}">
 	  	  	  <tr class="hoverable striped" id="1">
 	  	  	  	<td id="team_id">${teamInfo.team.team_id}</td>
 	  	  	  	<td>${teamInfo.team.leader_id }</td>
 	  	  	  	<td>  
					<select onchange="changeAssistant('${teamInfo.team.team_id}',this)" id="assistant_selector">
					      <option value="" disabled selected>${teamInfo.name}</option>
					      <c:forEach var="assistant" items="${data.assistants}">
					      	<option value="${assistant.assistant_id}" >${assistant.assistant_name }</option>
					      </c:forEach>
					 </select>
					 
  				</td>
 	  	  	  </tr>
 	  	  	</c:forEach>
 	  	  </tbody>
 	  	</table>
 	  	
 	  </div>
 	</div>
 </div>
<script>
  $(document).ready(function() {
    $('select').material_select();
  });

</script>
  				