<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>口座開設ページ</h2>

        <form method="POST" action="<c:url value='/account/create'/>">
            <c:import url="_form.jsp" />
        </form>

        <p>
            <a href="<c:url value='/account/index'/>">一覧に戻る</a>
        </p>
    </c:param>
</c:import>