<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Cabinet" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
    * {
        font-size: 18px;
        color: #003399;
    }

    .table_test {
        font-size: 18px;
        background: white;
        max-width: 70%;
        width: 70%;
        border-collapse: collapse;
        text-align: left;
    }

    th {
        font-weight: normal;
        color: #039;
        border-bottom: 2px solid #6678b1;
        padding: 7px 8px;
    }

    td {
        color: #669;
        border-top: 1px solid #e8edff;
        padding: 10px 7px;
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

    .info {
        margin-left: 100px;
    }
</style>
<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="info">
        <h2><fmt:message key="user_info.label.user_info"/></h2>
        <legend><fmt:message key="user_info.label.name"/>${user.firstName}</legend>
        <br/>
        <legend><fmt:message key="user_info.label.surname"/> ${user.lastName}</legend>
        <br/>
        <legend><fmt:message key="user_info.label.male"/> ${user.male}</legend>
        <br/>
        <br/>
        <legend><fmt:message key="user_info.label.login"/> ${user.login}</legend>
        <br/>
        <legend><fmt:message key="user_info.label.email"/> ${user.email}</legend>
        <br/>
        <br/>
        <br/>

        <c:choose>
            <c:when test="${userRole.name == 'student' }">

                <h2><fmt:message key="user_info.label.user_result"/></h2>
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
            </c:when>
            <c:when test="${userRole.name == 'admin' }">
                <table id="test" class="table_test">
                    <thead>
                    <tr>
                        <th><a href="controller?command=showUserResults"><fmt:message key="user_info.link.user_result"/></a></th>
                        <th><a href="controller?command=showUsers"><fmt:message key="user_info.link.user"/></a></th>
                        <th><a href="#"><fmt:message key="user_info.link.find_user"/></a></th>
                    </tr>
                    </thead>
                </table>
            </c:when>
        </c:choose>
    </div>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
