<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/10
  Time: 下午 03:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>結算頁面</title>
    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>

    <%--        靜態包含login_bar頁面--%>
    <%@ include file="/pages/common/login_bar.jsp"%>
</div>


<div id="main">
    <h1>您的訂單已結算，訂單號為${sessionScope.orderId}</h1>
</div>

<%--        靜態包含末尾頁面--%>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>
