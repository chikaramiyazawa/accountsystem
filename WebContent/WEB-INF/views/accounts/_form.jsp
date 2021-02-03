<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>

    </div>
</c:if>
<label for="numbers">口座番号</label>
<br />
<input type="text" name="name" value="${accounts.numbers}" />
<br />
<br />

<label for="password">パスワード</label>
<br />
<input type="text" name="password" value="${accounts.password}" />
<br />
<br />

<laber for="remainder">入金</laber><br />
<input type="text" name="remainder" value=1000 />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>