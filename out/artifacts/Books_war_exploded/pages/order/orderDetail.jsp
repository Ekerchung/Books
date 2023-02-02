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
    <title>天天來網路書店-訂單明細</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>

<div id="header">
    <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>

    <%--        靜態包含login_bar頁面--%>
    <%@ include file="/pages/common/login_bar.jsp"%>
</div>


<div id="main_orderDetail">
<%--    <c:forEach items="${ requestScope.orders }" var="order">--%>
<%--        ${ item } <br>--%>
<%--    </c:forEach>--%>
<%--    ${requestScope.orders}--%>
        <c:if test="${empty requestScope.orderItems}">
            <span class="empty_cart">訂單沒有商品內容</span>
        </c:if>

        <c:if test="${not empty requestScope.orderItems}">
            <table>
                    <tr>
                        <th colspan="6">訂單編號：${requestScope.orderId}</th>
                    </tr>
                <c:forEach items="${requestScope.orderItems}" var="orderItem">
                    <tr>
                        <td><img class="orderDetail_img" alt="" src="${orderItem.imgPath}"/></td>
                        <td colspan="4">${orderItem.name}<br><br>
                                        x${orderItem.count}
                        </td>
                        <td style="text-align: center">$${orderItem.price}</td>
                    </tr>
                </c:forEach>
                    <tr>
                        <td colspan="6" style="text-align: right"><b>訂單金額：</b><span style="text-align: right;font-size: 24px; color: red "> $${requestScope.totalPrice}</span></td>
                    </tr>
            </table>

        </c:if>

        </div>
        <%--        靜態包含末尾頁面--%>
        <%@ include file="/pages/common/footer.jsp"%>
</body>
</html>w
