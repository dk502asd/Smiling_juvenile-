<%--
  Created by IntelliJ IDEA.
  User: Dk502可分
  Date: 2018/11/5
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="modifyUser" method="post">
    <p><input type="hidden" name="uid" value="${user.uid}"></p>
    <p>用户名：<input type="text" name="uname" value="${user.uname}"> </p>
    <p>密码：<input type="text" name="upwd" value="${user.upwd}"> </p>
    <p><input type="submit" value="确定"> </p>
</form>
</body>
</html>
