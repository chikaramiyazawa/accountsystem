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


<h2>現在の残高　<c:out value="${credits.remainder}"/>円</h2>
<label for="numbers">口座番号</label>
<c:out value="${sessionScope.login_accounts.numbers}"/>
<input type="hidden" name="numbers" value="${sessionScope.login_accounts.numbers}" />
<br />

<input type="hidden" name="remainder" value="${credits.remainder}" />
<br />

<label for="cash">入金</label><br />
<input type="text" name="cash" value=0  />
<br />




<input type="hidden" name="_token" value="${_token}" /><br />
<button type="submit">入金</button>
<br />