<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-圖書管理</title>

    <%--    靜態包含頭部訊息，包含bathPath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
                return confirm("確定要刪除【" + $(this).parent().parent().find("td:first").next().text() + "】?");
            });


        });
    </script>
</head>
<body>
        <div id="login_header">
            <a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
            <%--            靜態包含後台管理頭部頁面--%>
            <%@ include file="/pages/common/manager_bar.jsp"%>
        </div>


        <div id="main">
            <table>
                <Tr>
                    <th>ID</th>
                    <th>書名</th>
                    <th>作者</th>
                    <th>價格</th>
                    <th>銷量</th>
                    <th>庫存</th>
                    <th colspan="2">操作</th>
                </Tr>
                <c:forEach items="${requestScope.page.items}" var="book">
                <tr>
                    <td>${book.b_id}</td>
                    <td>${book.b_name}</td>
                    <td>${book.b_author}</td>
                    <td>${book.b_price}</td>
                    <td>${book.b_sales}</td>
                    <td>${book.b_stoke}</td>
                    <td><a href="manager/bookServlet?action=getBook&b_id=${book.b_id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                    <td><a class="deleteClass" href="manager/bookServlet?action=delete&b_id=${book.b_id}&pageNo=${requestScope.page.pageNo}">刪除</a></td>
                </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加圖書</a></td>
                </tr>
            </table>
        </div>

        <%--        靜態包含分頁條--%>
        <%@ include file="/pages/common/page_nav.jsp"%>

</body>
</html>