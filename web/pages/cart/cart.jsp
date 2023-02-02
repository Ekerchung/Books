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
    <title>天天來網路書店-購物車</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $(".clearCartItem").click(function () {
                return confirm("確定要清空購物車?");
            });

            $(".updateCount").change(function () {
                //獲取商品ID
                var id = $(this).attr("bookId");
                //獲取修改後的商品數量
                var count = this.value;
                //指向cartServlet中的updateCount方法，並提供參數id & count
                location.href="${pageScope.basePath}/cartServlet?action=updateCount&id=" + id + "&count=" + count;
            });

            $("#btn_checkout").click(function () {
                //指向orderServlet中的createOrder方法
                location.href="${pageScope.basePath}/orderServlet?action=createOrder";
            });


        });
    </script>

</head>
<body>

        <div id="header">
            <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>

            <%--        靜態包含login_bar頁面--%>
            <%@ include file="/pages/common/login_bar.jsp"%>
        </div>


        <div id="main_cartlist">

            <c:if test="${empty sessionScope.cart.items}">
                <span class="empty_cart">目前購物車內沒有商品</span>
            </c:if>

            <c:if test="${not empty sessionScope.cart.items}">
                <table>
                    <Tr>
                        <th colspan="2">商品明細</th>
                        <th>價格</th>
                        <th>數量</th>
                        <th>小計</th>
                        <th>操作</th>
                    </Tr>
                    <c:forEach items="${sessionScope.cart.items}" var="entry">
                        <tr>
                            <td><img class="cart_img" alt="" src="${entry.value.imgPath}"/></td>
                            <td>${entry.value.name}</td>
                            <td>${entry.value.price}</td>
                            <td>
                                <input class="updateCount" bookId="${entry.value.id}" type="text" value="${entry.value.count}">

                            </td>
                            <td>${entry.value.totalPrice}</td>
                            <td><a class="deleteCartItem" href="${pageScope.basePath}cartServlet?action=deleteItem&id=${entry.value.id}">刪除</a></td>
                        </tr>
                    </c:forEach>
                    <tr style="height: 100px">
                        <td><a class="clearCartItem" href="${pageScope.basePath}cartServlet?action=clear">清空購物車</a></td>
                        <td colspan="2" class="cart_span">共&nbsp; ${sessionScope.cart.totalCount} &nbsp;件商品</td>
                        <td colspan="2" class="cart_span">總金額<span id="totalPrice">$${sessionScope.cart.totalPrice}</span>元</td>
                        <td><input type="button" id="btn_checkout" value="去買單"></td>
                    </tr>
                </table>
            </c:if>
        </div>
        <%--        靜態包含末尾頁面--%>
        <%@ include file="/pages/common/footer.jsp"%>
</body>
</html>
