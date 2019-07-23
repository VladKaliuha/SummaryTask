<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Subject" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>



</style>
<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <%@ include file="/WEB-INF/jspf/user_answer_list.jspf" %>

</div>
<div>
    <p><b>${question_text}</b></p>
    <p>

        <c:set var="k" value="0"/>

        <c:forEach var="answer" items="${answer_list}">
            <c:set var="k" value="${k+1}"/>

        <input type="checkbox" name="user_answer">${answer.text}<Br>
        </c:forEach>
        <a href="controller?command=checkAnswer">Ответить</a><Br>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
