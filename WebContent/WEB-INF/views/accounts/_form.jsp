<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${errors != null}">
    <div id="flush_error">
        下記の内容を確認してください。入金は1000円から<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>
<label for="numbers">口座番号</label>
<br />
<input type="text" name="numbers" value="${accounts.numbers}" />
<br />
<br />

<label for="password">パスワード</label><br />
<input type="password" name="password" value="${accounts.password}" />
<br />


<laber for="admin_flag">権限</laber><br />
<select name="admin_flag">
    <option value="0"<c:if test="${accounts.admin_flag == 0}">selected</c:if>>一般</option>
    <option value="1"<c:if test="${accounts.admin_flag == 1}">selected</c:if>>従業員</option>
    <option value="2"<c:if test="${accounts.admin_flag == 2}">selected</c:if>>管理者</option>
 </select>
 <br /><br />


<input type="hidden" name="_token" value="${_token}" /><br />
<button type="submit">登録</button>
<br />