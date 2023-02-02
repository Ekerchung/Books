<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-後台管理</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>
        <div id="login_header">
            <a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
            <span class="wel_word">後台管理系統</span>
<%--            靜態包含後台管理頭部頁面--%>
            <%@ include file="/pages/common/manager_bar.jsp"%>
        </div>


        <div id="main">
            <h1 id="manager_welcome">歡迎管理員進入後台管理系統</h1>
        </div>

</body>
</html>