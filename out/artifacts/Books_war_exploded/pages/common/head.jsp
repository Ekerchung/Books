<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/6
  Time: 下午 04:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String basePath = request.getScheme() //協議名稱
            + "://"
            + request.getServerName() //服務器名稱/ip
            + ":"
            + request.getServerPort() //端口號
            + request.getContextPath() + "/" ; //工程路徑

    pageContext.setAttribute("basePath",basePath);
%>

<base href="<%= basePath %>">
<link rel="stylesheet" type="text/css" href="static/css/style.css"/>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/script/regist.js"></script>