<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2022/12/14
  Time: 下午 05:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/Books/">
</head>
<body>

</body>
</html>
<%--此頁面只做請求轉發--%>
<jsp:forward page="/client/bookServlet?action=page"></jsp:forward>