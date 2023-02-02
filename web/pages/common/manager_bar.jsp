<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/7
  Time: 下午 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
        <span class="manager_word">
            <span><button id="btn_manager" onclick="location.href='manager/bookServlet?action=page';">圖書管理</button></span>
            <span>|</span>
            <span><button id="btn_manager" onclick="location.href='orderServlet?action=pageForManager';">訂單管理</button></span>
            <span>|</span>
            <span><button id="btn_indexpage" onclick="location.href='/Books/index.jsp';">返回商城</button></span>
        </span>
</div>