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
        <h2>通帳記入</h2>

        <table id="accounts_list">
            <tbody>
                <tr>
                    <th>口座番号</th>
                    <th>お預入金額</th>
                    <th>引き出し金額</th>
                    <th>残高</th>
                    <th>日付</th>



                </tr>
                <c:forEach var="credits" items="${credits}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${sessionScope.login_accounts.numbers}"/></td>
                        <td>
                        <c:choose>
                                    <c:when test="${credits.deposit == 0}">  </c:when>
                                    <c:otherwise>
                                    <c:out value="￥${credits.deposit}" />
                                    </c:otherwise>
                        </c:choose>
                        </td>
                        <td>
                         <c:choose>
                                    <c:when test="${credits.drawer == 0}">  </c:when>
                                    <c:otherwise>
                                    <c:out value="￥${credits.drawer}" />
                                    </c:otherwise>
                        </c:choose>
                        </td>
                        <td><c:out value="￥${credits.remainder}" /></td>
                        <td> <c:out value="${credits.created_at}" /></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

         <div id="pagination">
                        (全 ${numbers_count}　件)<br />
                       <c:forEach var="i" begin="1" end="${((numbers_count - 1) / 15) + 1}" step="1">
                        <c:choose>
                            <c:when test="${i == page}">
                                <c:out value="${i}" />&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/passbookentry?page=${i}'/>"><c:out value="${i}"/></a>&nbsp;
                                </c:otherwise>
                                </c:choose>
                                </c:forEach>
                                     </div>

      <p><a href= "<c:url value='/toppage' />">トップページに戻る </a></p>
         </c:param>

</c:import>
