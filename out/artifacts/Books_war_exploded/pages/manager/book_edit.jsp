<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/3
  Time: 下午 02:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-編輯圖書</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>
<div id="login_header">
    <a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
    <%--            靜態包含後台管理頭部頁面--%>
    <%@ include file="/pages/common/manager_bar.jsp"%>
</div>


<div id="main">
    <form action="manager/bookServlet" method="get">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="action" value="${empty param.b_id ? "add":"update"}">
        <input type="hidden" name="b_id" value="${requestScope.book.b_id}">

        <table>
            <Tr>
                <th>書名</th>
                <th>作者</th>
                <th>價格</th>
                <th>銷量</th>
                <th>庫存</th>
                <th colspan="2">操作</th>
            </Tr>
                <tr>
                    <td><input type="text" name="b_name" value="${requestScope.book.b_name}"></td>
                    <td><input type="text" name="b_author" value="${requestScope.book.b_author}"></td>
                    <td><input type="text" name="b_price" value="${requestScope.book.b_price}"></td>
                    <td><input type="text" name="b_sales" value="${requestScope.book.b_sales}"></td>
                    <td><input type="text" name="b_stoke" value="${requestScope.book.b_stoke}"></td>
                    <td colspan="2"><input type="submit" value="提交"></td>

                </tr>
        </table>
    </form>
</div>

</body>
</html>