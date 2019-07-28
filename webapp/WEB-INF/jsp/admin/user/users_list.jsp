<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
    .table_test {
        font-size: 18px;
        background: white;
        max-width: 70%;
        width: 70%;
        border-collapse: collapse;
        text-align: left;
        margin-left: 100px;
    }

    th {
        font-weight: normal;
        color: #039;
        border-bottom: 2px solid #6678b1;
        padding: 10px 8px;
    }

    td {
        color: #669;
        border-top: 1px solid #e8edff;
        padding: 10px 15px;
    }

    tr:hover td {
        background: #e8edff;
    }

    tr:hover td {
        color: #6699ff;
    }

    a {
        color: #2728ff;
        text-decoration: none;
    }
</style>
</head>


<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <table id="test" class="table_test">
        <thead>
        <tr>
            <th><fmt:message key="user_info.label.login"/></th>
            <th><fmt:message key="user_info.label.name"/></th>
            <th><fmt:message key="user_info.label.surname"/></th>
            <th><fmt:message key="user_info.label.cabinet"/></th>
        </tr>
        </thead>

        <c:forEach var="user" items="${users}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td>
                    <legend>${user.login}</legend>
                </td>
                <td>
                    <legend>${user.firstName}</legend>
                </td>
                <td>
                    <legend>${user.lastName}</legend>
                </td>
                <td>
                    <a href="controller?command=openUserCabinet&user_id=${user.id}">Open cabinet</a>
                </td>

            </tr>
        </c:forEach>

    </table>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>