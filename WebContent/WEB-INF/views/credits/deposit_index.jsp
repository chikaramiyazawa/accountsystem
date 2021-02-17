<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
         <h2>残高確認</h2>

        <table id="credits_list">
            <tbody>
                <tr>
                    <th>口座番号</th>
                    <th>残高</th>
                    <th>ご入金</th>

                </tr>

                <c:forEach var="credits" items="${credits}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${sessionScope.login_accounts.numbers}"/></td>
                        <td><c:out value="￥${credits.remainder}" /></td>
                        <td><a href="<c:url value='/deposit/edit?id=${credits.id}' />">入金する</a></td>
                    </tr>
                    </c:forEach>

            <p><a href= "<c:url value='/toppage' />">トップページに戻る </a></p>



            </tbody>
        </table>

         </c:param>

</c:import>
