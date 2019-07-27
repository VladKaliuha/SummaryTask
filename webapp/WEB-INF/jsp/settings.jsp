<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <c:set var="title" value="Settings"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <form id="change_locale_form" action="controller" method="post">
        <input hidden name="command" value="viewSettings">
        <input hidden name="command_name" value="changeLocale">
        <fmt:message key="settings_jsp.label.set_locale"/>
        : <select name="locale">
        <c:forEach items="${applicationScope.locales}" var="locale">
            <c:set var="selected"
                   value="${locale.key == currentLocale ? 'selected' : '' }"/>
            <option value="${locale.key}" ${selected}>${locale.value}</option>
        </c:forEach>
    </select> <input type="submit"
                     value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

    </form>
</table>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>