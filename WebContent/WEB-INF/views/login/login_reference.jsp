<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
     <c:param name="content">
<c:if test="${errors != null}">
    <div id="flush_error">
       入力内容にエラーがあります<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>

<h2>口座番号　パスワード　入力画面</h2>
<form method="POST" action= "<c:url value='/login/reference'/>">

<label for="numbers">口座番号</label>
<br />
<input type="text" name="numbers" value="${accounts.numbers}" />
<br />
<br />

<label for="password">パスワード</label><br />
<input type="password" name="password" value="${accounts.password}" />
<br />

<input type="hidden" name="_token" value="${_token}" /><br />
<button type="submit">ログイン</button>
<br />

</form>

</c:param>
</c:import>

