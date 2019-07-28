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
    <%--===========================================================================
         This way we define the ADMIN MENU.
         ===========================================================================--%>
    <c:choose>
        <c:when test="${userRole.name == 'admin' }">

            <table id="test" class="table_test">
                <thead>
                <tr>
                    <th><fmt:message key="subject_list_jsp.label.subject_names"/></th>
                </tr>
                </thead>

                <c:forEach var="subject" items="${subjectList}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td>
                            <a href="controller?command=testsListBySubject&subject_id=${subject.id}">${subject.name}</a><br>
                        </td>
                        <td>
                            <a href="controller?command=forwardTo&command_name=update_subject&subject_id=${subject.id}">Edit subject</a><br>
                        </td>
                        <td>
                            <a href="controller?command=deleteSubject&subject_id=${subject.id}">Delete subject</a><br>
                        </td>
                    </tr>
                </c:forEach>

                <tr>
                    <td>
                        <a href="controller?command=forwardTo&command_name=create_subject">+Add new subject</a>
                    </td>
                </tr>
            </table>

        </c:when>
    </c:choose>


</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
