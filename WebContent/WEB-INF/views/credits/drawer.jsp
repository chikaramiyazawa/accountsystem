<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>現金お引き出しページ</h2>

        <form method="POST" action="<c:url value='/drawer'/>">
            <c:import url="drawer_form.jsp" />
        </form>

        <p>
            <a href= "<c:url value='/toppage' />">トップページに戻る </a>
        </p>
    </c:param>
</c:import>