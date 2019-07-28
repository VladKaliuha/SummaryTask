<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Test" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
    .text {
        font-size: 22px;
        color: #003399;
    }

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

<body>
<div class="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="text">
    </div>
    <c:choose>
        <c:when test="${userRole.name == 'admin' }">
            <table id="test" class="table_test">
                <thead>
                <tr>
                    <th><fmt:message key="test_list_jsp.link.test_names"/>
                    </th>
                </tr>
                </thead>

                <c:forEach var="test" items="${testsList}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td>
                            <a href="controller?command=startEditQuestion&test_id=${test.id}">${test.name}</a><br>
                        </td>
                        <td>
                            <a href="controller?command=forwardTo&command_name=update_test&test_id=${test.id}">Edit
                                test</a><br>
                        </td>
                        <td>
                            <a href="controller?command=deleteTest&test_id=${test.id}">Delete test</a><br>
                        </td>
                    </tr>
                    </tr>
                </c:forEach>

                <tr>
                    <td>
                        <a href="controller?command=forwardTo&command_name=create_test">+Add new test</a>
                    </td>
                </tr>
            </table>
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </c:when>
    </c:choose>
</div>


</body>
</html>
