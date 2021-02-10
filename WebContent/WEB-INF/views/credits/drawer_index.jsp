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
        <h2>口座番号残高確認</h2>

        <table id="accounts_list">
            <tbody>
                <tr>
                    <th>口座番号</th>
                    <th>残高</th>
                    <th>お引き出し</th>

                </tr>
                <c:forEach var="credits" items="${credits}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${sessionScope.login_accounts.numbers}"/></td>
                        <td><c:out value="￥${credits.remainder}" /></td>
                        <td><a href="<c:url value='/drawer/edit?id=${credits.id}' />">残高引き出しする</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

      <p><a href= "<c:url value='/toppage' />">トップページに戻る </a></p>
         </c:param>

</c:import>
