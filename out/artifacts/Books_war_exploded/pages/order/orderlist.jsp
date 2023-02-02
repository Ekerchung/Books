<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2022/12/15
  Time: 下午 02:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>天天來網路書店-訂單管理</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>

<div id="header">
    <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>

    <%--        靜態包含login_bar頁面--%>
    <%@ include file="/pages/common/login_bar.jsp"%>
</div>


<div id="main_orderlist">
<%--    <c:forEach items="${ requestScope.orders }" var="order">--%>
<%--        ${ item } <br>--%>
<%--    </c:forEach>--%>
<%--    ${requestScope.orders}--%>
            <c:if test="${empty requestScope.page.items}">
        <span class="empty_cart">目前沒有訂單</span>
    </c:if>

        <c:if test="${not empty requestScope.page.items}">
            <c:forEach items="${requestScope.page.items}" var="order">
            <table>
                    <Tr>
                        <th colspan="2">訂單編號</th>
                        <th colspan="2">日期</th>
                        <th>訂單狀態</th>
                        <th>總價</th>
                        <th colspan="2">其他</th>
                    </Tr>
                    <tr>
                        <td colspan="2">${order.orderId}</td>
                        <td colspan="2">${order.createTime}</td>
                        <td>
                        <c:choose>
                            <c:when test="${ order.status == 0 }">
                                未出貨
                            </c:when>
                            <c:when test="${ order.status == 1 }">
                                已出貨
                            </c:when>
                            <c:when test="${ order.status == 2 }">
                                訂單完成
                            </c:when>
                        </c:choose>
                        </td>
                        <td>${order.price}</td>
                        <td>
                            <a href="${basePath}orderServlet?action=showOrderDetail&orderId=${order.orderId}&totalPrice=${order.price}">訂單明細</a>
                            <span>|</span>
                            <a href="${basePath}orderServlet?action=sendOrder&orderId=${order.orderId}">發貨</a>
                        </td>
                    </tr>
            </table>
            </c:forEach>
            <%--        靜態包含分頁條--%>
            <%@ include file="/pages/common/page_nav.jsp"%>
            <br><br>
        </c:if>

        </div>


        <%--        靜態包含末尾頁面--%>
        <%@ include file="/pages/common/footer.jsp"%>
</body>
</html>
