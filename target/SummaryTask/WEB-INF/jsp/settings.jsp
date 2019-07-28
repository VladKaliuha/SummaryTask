<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<style>
    table{
        margin-left: 100px;
    }

    *{
        color: #003399;
    }
    input[type=text] {
        padding: 5px;
        border: none;
        border-bottom: 2px solid blue;
    }

    input[type=submit] {
        padding: 5px 70px;
        background: #ccc;
        border: 0 none;
        cursor: pointer;
        -webkit-border-radius: 5px;
        border-radius: 5px;
    }

    input[type=submit]:hover {
        background: #6B9CD2;
    }

    input[type=submit]:active {
        background: #0917d2;
    }
</style>
<head>
    <c:set var="title" value="Settings"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>

<table id="main-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</br>
</br>
</br>
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
    </select>
        <input type="submit"
                     value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

    </form>
</table>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>