<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import = "java.util.List,com.chainsys.jspproject.pojo.Employee,java.util.ArrayList"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
  int result = (int)request.getAttribute("addemp");
  %>
  <div>Add Employee: <%=result%></div>
</body>
</body>
</html>