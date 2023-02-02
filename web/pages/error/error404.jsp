<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-error404</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>


</head>
<body>

    <div id="header">
        <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>

    </div>

    <div id="main">
        <div class="book_cond">
            <div class="error500">很抱歉，您訪問的頁面不存在，或已經被刪除!! (error 404)</div>
            <div><a class="return_home" href="/Books/index.jsp">返回首頁</a></div>
        </div>

    </div>
    <br><br>


    <%--        靜態包含末尾頁面--%>
    <%@ include file="/pages/common/footer.jsp"%>
</body>
</html>