<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容を確認してください。<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>


<h2>新規口座入金画面</h2>
<label for="numbers">口座番号</label>
<c:out value="${sessionScope.login_accounts.numbers}"/>
<input type="hidden" name="login_accounts" value="${sessionScope.login_accounts.numbers}" />
<br />

<label for="numbers">入金</label>
<input type="text" name="remainder" value="${credits.remainder}" />
<br />

<input type="hidden" name="_token" value="${_token}" /><br />
<button type="submit">入金</button>
<br />