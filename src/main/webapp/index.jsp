<%--
  Created by IntelliJ IDEA.
  User: Kuang-Yu
  Date: 2021/8/4
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <p> Home Page </p>
    <a href="${pageContext.request.contextPath}/test/hello">Hello</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/api/students">Get Students</a>
</body>
</html>
