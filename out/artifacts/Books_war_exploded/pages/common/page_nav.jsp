<%--
  Created by IntelliJ IDEA.
  User: b8954
  Date: 2023/1/6
  Time: 下午 04:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--        分頁條的開始--%>
<div id="page_nav">
    <%--            若當前頁碼大於第一頁，才顯示首頁及上一頁--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首頁</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一頁</a>
    </c:if>

    <%--            頁碼輸出--%>
    <%--            狀況1：當總頁碼小於等於5頁--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
            <%--                    <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">--%>
            <%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
            <%--                            <span class="page_current">${i}</span>--%>
            <%--                        </c:if>--%>
            <%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
            <%--                            <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>--%>
            <%--                        </c:if>--%>
            <%--                    </c:forEach>--%>
        </c:when>

        <%--            狀況2：當總頁碼大於5頁--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--狀況2.1：當前頁碼為前3個，只顯示前五個頁碼--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                    <%--                    <c:forEach begin="1" end="5" var="i">--%>
                    <%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
                    <%--                            <span class="page_current">${i}</span>--%>
                    <%--                        </c:if>--%>
                    <%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
                    <%--                            <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>--%>
                    <%--                        </c:if>--%>
                    <%--                    </c:forEach>--%>
                </c:when>
                <%--狀況2.2：當前頁碼為末3個，只顯示後五個頁碼--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                    <%--                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">--%>
                    <%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
                    <%--                            <span class="page_current">${i}</span>--%>
                    <%--                        </c:if>--%>
                    <%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
                    <%--                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>--%>
                    <%--                        </c:if>--%>
                    <%--                    </c:forEach>--%>
                </c:when>
                <%--狀況2.3：其他，顯示當前頁碼加減兩碼--%>
                <c:otherwise>

                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                    <%--                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">--%>
                    <%--                        <c:if test="${i == requestScope.page.pageNo}">--%>
                    <%--                            <span class="page_current">${i}</span>--%>
                    <%--                        </c:if>--%>
                    <%--                        <c:if test="${i != requestScope.page.pageNo}">--%>
                    <%--                            <a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>--%>
                    <%--                        </c:if>--%>
                    <%--                    </c:forEach>--%>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--            優化代碼：將上面頁碼重複的foreach統一寫在這，上面只記錄變量begin、end--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            <span class="page_current">${i}</span>
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--            若當前頁碼小於總頁碼，才顯示末頁及下一頁--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一頁</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末頁</a>
    </c:if>

    跳到第 <input value="${empty param.pageNo? 1 :param.pageNo }" name="pn" id="pn_input"/>頁"
    <input id="searchPageBtn" type="button" value="確定"><br>
    第${requestScope.page.pageNo} / ${requestScope.page.pageTotal}頁，共${requestScope.page.pageTotalCount}筆

    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo

            })
        })
    </script>

</div>
<%--        分頁條的結束--%>