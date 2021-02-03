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
        <h2>登録口座一覧</h2>

        <table id="accounts_list">
            <tbody>
                <tr>
                    <th>口座番号</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="accounts" items="${accounts}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${accounts.numbers}" /></td>
                        <td><a
                            href="<c:url value='/accounts/show?id=${accounts.id}' />">詳細を表示</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${accounts_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((accounts_count - 1) / 15) + 1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/accounts/index?page=${i}' />"><c:out
                                value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>


            <p>
                <a href="<c:url value='/accounts/new'/>">口座開設</a>
            <p>
                <a href="<c:url value='/toppage' />">トップページに戻る</a>
            </p>

        </div>

    </c:param>


</c:import>