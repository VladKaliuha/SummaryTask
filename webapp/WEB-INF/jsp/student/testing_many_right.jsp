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
<div class="question_div">
    <form id="user_answer_form" action="controller" method="post">

        <p><b>${question_text}</b></p>
        <p>

            <c:forEach var="answer" items="${answer_list}">
                <input type="checkbox" name="user_answer" value="${answer.text}">${answer.text}<Br>
            </c:forEach>
            <input type="hidden" name="command" value="checkAnswer"/>
            <input type="submit" class="button" value="Ответить"></p>
    </form>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
