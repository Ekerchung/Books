<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>天天來網路書店</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            $("#btn_login").click(function (){
                window.location("pages/user/login.jsp");

            })

            //使用ajax方法將商品加入購物車
            $(".addToCart").click(function (){
                var bookId = $(this).attr("bookId");
                $.getJSON("${basePath}cartServlet","action=ajaxAddItem&id=" + bookId,function (data){
                    $("#btn_cart").text("購物車(" + data.totalCount + ")")
                })
            })
        })



    </script>
</head>
<body>

    <div id="header">
        <a href="index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
<%--        靜態包含login_bar頁面--%>
        <%@ include file="/pages/common/login_bar.jsp"%>

    </div>

    <div id="main">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                價格：<input id="min" type="text" name="min" value="${empty param.min ?"":param.min}">元
                ~<input id="max" type="text" name="max" value="${empty param.max ?"":param.max}">元
                <input type="submit" value="查詢">
            </form>
        </div>

        <div id="book">

            <c:forEach items="${requestScope.page.items}" var="book">
                <div class="book_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.b_imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp_book_name">${book.b_name}</span>
                    </div>
                    <div></div>
                    <div class="book_author">
                        <span class="sp1">作者：</span>
                        <span class="sp2">${book.b_author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">定價：</span>
                        <span class="sp2">${book.b_price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">銷量：</span>
                        <span class="sp2">${book.b_sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">庫存：</span>
                        <span class="sp2">${book.b_stoke}</span>
                    </div>
                        <button bookId = ${book.b_id} class="addToCart">加入購物車</button>
                </div>
                </div>

            </c:forEach>


        </div>
    </div>
    <%--        靜態包含分頁條--%>
    <%@ include file="/pages/common/page_nav.jsp"%>
    <br><br>


<%--    <div id="bottom">--%>
<%--        <div class="bottom_div">--%>
<%--            <span class="bottom_info">--%>
<%--                地址：221 台灣新北市汐止區福德路50號1樓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                客服專線：(02)2688-8888&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                服務時間：週一至週五 9:30~12:30；14:00~17:30 (國定例假日除外)--%>
<%--            </span>--%>
<%--        </div>--%>
<%--        <div class="bottom_div">--%>
<%--            <small> Copyright© 2000–2022 金石網絡股份有限公司</small>--%>
<%--        </div>--%>
<%--    </div>--%>
    <%--        靜態包含末尾頁面--%>
    <%@ include file="/pages/common/footer.jsp"%>
</body>
</html>