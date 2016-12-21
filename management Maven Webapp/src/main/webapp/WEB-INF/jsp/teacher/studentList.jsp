<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 
 <div >
 	<div class="row">
 	  <div class="col s3">
	    <!-- Grey navigation panel -->
	    <ul class="collapsible" data-collapsible="accordion">
    	<li>	
	        <div class="collapsible-header" id="rollCallTimeIdentifier" name="${rollcall_max}">请选择点名次数</div>
	        <div class="collapsible-body">
	        	<ul class="collection">
		        <c:forEach  var="i" begin="1" end="${rollcall_max}">
		        	<a href="#!" class="collection-item waves-effect waves-teal" onclick="rollCallTab('teacher/studentRollCall',${i})">
		        	<i class="material-icons">send</i>
		        		第${i}次
		        	</a>
		        </c:forEach>
		        </ul>
	        </div>
        </li>
        <li>
        	<div class="collapsible-header" id="ResultsIdentifier" onclick="rollCallTab1('teacher/studentResult1')">查看学生成绩</div>
        </li>
        <li>
        	<div class="collapsible-header" onclick="getTotalGrade()" >生成最终成绩</div>
        	<div class="collapsible-body">
        		<ul class="collection" id="grade_file">       			
        		</ul>
        	</div>
        </li>
        </ul>
	  </div>
	  
 	  <div class="col s9" name="rollCallTab">
	  	
 	  </div>
 	</div>
 </div>
 
 <script type="text/javascript">
	  $(document).ready(function(){
	    $('.collapsible').collapsible();
 	  });
 </script>
