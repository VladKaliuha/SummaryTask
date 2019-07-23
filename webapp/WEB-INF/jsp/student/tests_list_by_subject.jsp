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
        <h3>${subject_name} tests</h3>
    </div>

    <table id="test" class="table_test">
        <thead>
        <tr>

            <c:if test="${doneSortByName=='true'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=Z->A">Name</a>
                </th>
            </c:if>
            <c:if test="${doneSortByName=='false'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=A->Z">Name</a>
                </th>
            </c:if>


            <c:if test="${doneSortByComplexity=='true'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=E->H">Complexity</a>
                </th>
            </c:if>
            <c:if test="${doneSortByComplexity=='false'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=H->E">Complexity</a>
                </th>
            </c:if>


            <c:if test="${doneSortBySize=='false'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=S->B">Size</a>
                </th>
            </c:if>
            <c:if test="${doneSortBySize=='true'}">
                <th>
                    <a href="controller?command=testsListBySubject&subject_id=${subject_id}&subject_name=${subject_name}&sort_by=B->S">Size</a>
                </th>
            </c:if>

        </tr>
        </thead>

        <c:forEach var="test" items="${testsList}">
            <c:set var="k" value="${k+1}"/>
            <tr>
                <td>
                    <a href="controller?command=startTest&test_id=${test.id}">${test.name}</a><br></td>
                <td>${test.complexity}</td>
                <td>${test.size}</td>
                <td></td>
            </tr>
        </c:forEach>


        <%-- CONTENT --%>


    </table>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</div>


</body>
</html>
