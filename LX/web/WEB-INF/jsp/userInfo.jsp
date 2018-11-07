<%--
  Created by IntelliJ IDEA.
  User: Dk502可分
  Date: 2018/11/3
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
 <table border="1">
     <tr>
         <th>id</th>
         <th>姓名</th>
         <th>密码</th>
         <th colspan="2">操作</th>
     </tr>
     <c:forEach items="${page.userList}" var="user1">
         <tr>
             <td>${user1.uid}</td>
             <td>${user1.uname}</td>
             <td>${user1.upwd}</td>
             <td><a href="toUpdate?uid=${user1.uid}">修改</a></td>
             <td><a href="deleteUser?uid=${user1.uid}">删除</a></td>
         </tr>
     </c:forEach>
 </table>
<p><a href="toAdd">增加</a></p>
 <c:choose>
     <c:when test="${page.currentPageNo>1}">
         <a href="toUserList?currentPageNo=1">首页</a>
         <a href="toUserList?currentPageNo=${page.currentPageNo-1}">上一页</a>
     </c:when>
     <c:otherwise>
         <a style="color: #a0a0a0">首页</a>
         <a style="color: #a0a0a0">上一页</a>
     </c:otherwise>
 </c:choose>
 [${page.currentPageNo}/${page.totalPageSize}]
 <c:choose>
     <c:when test="${page.currentPageNo<page.totalPageSize}">
         <a href="toUserList?currentPageNo=${page.currentPageNo+1}">下一页</a>
         <a href="toUserList?currentPageNo=${page.totalPageSize}">末页</a>
     </c:when>
     <c:otherwise>
         <a style="color: #a0a0a0">下一页</a>
         <a style="color: #a0a0a0">末页</a>
     </c:otherwise>
 </c:choose>
</body>
</html>
