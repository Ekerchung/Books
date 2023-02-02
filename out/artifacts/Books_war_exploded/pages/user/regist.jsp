<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-會員註冊</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        //使用ajax請求判斷用戶名是否可用
        $(function (){
            $("#username").blur(function (){
                var username = this.value;
                $.getJSON("${basePath}userServlet","action=ajaxExistUsername&username=" + username,function (data){
                    if(data.existUsername){
                        $(".errormsg").text("用戶名已存在")
                    }else{
                        $(".errormsg").text("用戶名可用")
                    }
                })
            })
        })



    </script>
</head>
<body>
<%--    <div align="center">--%>
<%--        <span class="errorMsg">用戶名不正確</span>--%>
<%--    </div>--%>
<%--<table align="center">--%>
<%--    <tr>--%>
<%--        <td colspan="2"><a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td><span><a href="pages/user/login.jsp">會員登入</a></span></td>--%>
<%--        <td><span>立即註冊</span></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--        <td>--%>
<%--            <form action="registServlet" method="post">--%>
<%--                會員帳號：<input type="text" name="username" id="username"><br>--%>
<%--                會員密碼：<input type="password" name="password" id="password"><br>--%>
<%--                確認密碼：<input type="password" name="checkpassword" id="checkpassword"><br>--%>
<%--                電子信箱：<input type="text" name="email" id="email"><br>--%>
<%--                驗證碼：<input type="text" name="checknumber" id="checknumber"><br>--%>
<%--                <input type="submit" value="註冊" id="registbuttom">--%>
<%--            </form>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--</table>--%>
<div id="login_header">
    <a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
</div>


<div id="content">
    <div class="rigist_form">
        <div>
            <span class="login_title">註冊</span>
            <span class="errormsg">"請輸入帳號密碼"</span>
        </div>
        <div class="form">
            <form action="userServlet" method="post">
                <input type="hidden" name="action" value="regist">
                <input class="itxt" type="text" placeholder="會員帳號" name="username" id="username"><br>
                <input class="itxt" type="password" placeholder="會員密碼" name="password" id="password"><br>
                <input class="itxt" type="password" placeholder="確認密碼" name="checkpassword" id="checkpassword"><br>
                <input class="itxt" type="text" placeholder="電子信箱" name="email" id="email"><br>
                <%--                確認密碼：<input type="password" name="checkpassword" id="checkpassword"><br>--%>
                <%--                電子信箱：<input type="text" name="email" id="email"><br>--%>
                <input class="verification_code" type="text" placeholder="請輸入驗證碼" name="checknumber" id="checknumber">
                <img class="token" src="Kaptcha.jpg"><br>
                <input type="submit" value="註冊" id="loginbuttom">
            </form>
        </div>
        <div class="rigist_account">
            <span>已經有帳號了?</span>
            <span><a href="pages/user/login.jsp">登入</a></span>
        </div>
    </div>
</div>

</body>
</html>