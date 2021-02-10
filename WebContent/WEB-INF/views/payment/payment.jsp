<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
    <c:param name="content">
<c:if test="${errors != null}">
    <div id="flush_error">
        下記の内容を確認してください。入金は1000円から<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<h2>新規口座入金</h2>
 <form method="POST" action="<c:url value='/account/payment'/>">

<label for="numbers">口座番号</label>
<br />
<c:out value="${accounts.numbers}"/>
<input type="hidden" name="numbers" value="${accounts.numbers}" />
<br />

<input type="hidden" name="password" value="${accounts.password}" />
<input type="hidden" name="admin_flag" value="${accounts.admin_flag}"/>

<input type="hidden" name="_token" value="${_token}" /><br />
<button type="submit">入金へ</button>
<br />

</form>
</c:param>
</c:import>