<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/7
  Time: 下午 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${empty sessionScope.user.username}">
        <span><button id="btn_indexpage" onclick="location.href='/Books/index.jsp';">首頁</button></span>
        <span>|</span>
        <span><button id="btn_login" onclick="location.href='pages/user/login.jsp';">登入/註冊</button></span>
        <span>|</span>
        <span><button id="btn_cart" onclick="location.href='pages/cart/cart.jsp';">購物車(${empty sessionScope.cart.items? 0 :sessionScope.cart.totalCount})</button></span>
    </c:if>

    <c:if test="${not empty sessionScope.user.username}">
        <span><button id="btn_indexpage" onclick="location.href='/Books/index.jsp';">首頁</button></span>
        <span>|</span>
        <span id="btn_loginname">會員(${sessionScope.user.username})</span>
        <span>|</span>
        <c:if test="${1 == sessionScope.user.id}">
        <span><button id="btn_manager" onclick="location.href='pages/manager/manager.jsp';">後台管理</button></span>
        <span>|</span>
        </c:if>
        <span><button id="btn_manager" onclick="location.href='orderServlet?action=page';">我的訂單</button></span>
        <span>|</span>
        <span><button id="btn_cart" onclick="location.href='pages/cart/cart.jsp';">購物車(${empty sessionScope.cart.items? 0 :sessionScope.cart.totalCount})</button></span>
        <span>|</span>
        <span><button id="btn_logout" onclick="location.href='userServlet?action=logout';">登出</button></span>
    </c:if>
</div>