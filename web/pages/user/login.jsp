<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>天天來網路書店-會員登入</title>

    <%--    靜態包含頭部訊息，包含basePath、css以及JQuery文件--%>
    <%@ include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function(){
             $(".code_img").click(function (){
                   this.src="${basePath}Kaptcha.jpg?d" + new Date()
             })
        })
    </script>

</head>
<body>
        <div id="login_header">
            <a href="/Books/index.jsp"><img class="book_logo_img" src="static/img/Books_logo.jpg"/></a>
        </div>


        <div id="content">
            <div class="login_form">
                <div class="login_title">會員登入</div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errormsg">
                        ${empty requestScope.msg ? "請輸入帳號密碼" : requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <input class="itxt" type="text" placeholder="會員帳號" name="username" id="username" value="${requestScope.username}"><br>
                        <input class="itxt" type="password" placeholder="會員密碼" name="password" id="password"><br>
                        <input class="verification_code" type="text" placeholder="請輸入驗證碼" name="code" id="code">
                        <img class="code_img" src="Kaptcha.jpg"><br>
                        <input type="submit" value="登入" id="loginbuttom">
                    </form>
                </div>
                <div class="rigist_account">
                    <span>還不是會員?</span>
                    <span><a href="pages/user/regist.jsp">立即註冊</a></span>
                </div>
            </div>
        </div>

</body>
</html>