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
        <h2>ようこそJava銀行へ</h2>



        <table id="accounts_list">
            <tbody>
                <tr>

                    <th><a href="<c:url value='/login/deposit' />">お預入れ</a></th>
                    <th><a href="<c:url value='/login/drawer'/>">お引き出し</a></th>
                    <th><a href="<c:url value='/login/reference'/>">残高照会</a></th>
                </tr>



            </tbody>
        </table>
         <p>
            <a href="<c:url value='/account/index'/>">登録口座一覧を見る</a>
        </p>

    </c:param>


</c:import>