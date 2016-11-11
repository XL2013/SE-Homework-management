<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user_id=(String)request.getSession().getAttribute("user_id");
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>test mybatis</title>

  </head>
  <p>ceshi</p>
    ${message}
  <body>
  <p>${user_id}</p>
 
  </body>
</html>
