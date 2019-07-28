<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create" scope="page"/>
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


    a {
        color: #2728ff;
        text-decoration: none;
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
</head>


<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>


    <table id="test" class="table_test">
        <thead>
        <tr>
            <th>Create subject</th>
        </tr>
        </thead>
        <tr>
            <td>
                <form id="create_subject_form" action="controller" method="post">
                    <input type="hidden" name="command" value="createSubject"/>

                    <fieldset>
                        <legend>New subject name</legend>
                        <input type="text" name="new_subject_name" minlength="3" maxlength="100" required/>
                    </fieldset>
                    <br/>

                    <input type="submit" value="Create">
                </form>
            </td>
        </tr>

    </table>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
