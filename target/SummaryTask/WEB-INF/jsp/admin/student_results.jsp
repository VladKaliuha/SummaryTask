<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Subject" scope="page"/>
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
            <th><fmt:message key="user_info.label.user_result.date"/></th>
            <th><fmt:message key="user_info.label.user_result.test_name"/></th>
            <th><fmt:message key="user_info.label.user_result.result"/></th>
        </tr>
        </thead>

        <c:forEach var="result" items="${result}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td>
                    <legend>${result.date}</legend>
                </td>
                <td>
                    <legend>${result.testName}</legend>
                </td>
                <td>
                    <legend>${result.result}%</legend>
                </td>

            </tr>
        </c:forEach>

    </table>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>