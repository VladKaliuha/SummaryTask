<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Subject" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>

    a.button7 {
        font-weight: 700;
        font-size: 25px;
        color: white;
        text-decoration: none;
        padding: .8em 1em calc(.8em + 3px);
        border-radius: 3px;
        background: #3B5CC7;
        box-shadow: 0 -3px #0917D2 inset;
        transition: 0.2s;
        height: 0px;
        display: flex;
        align-items: center;
        justify-content: center;

    }

    a.button7:hover {
        background: #2D31A7;
    }

    a.button7:active {
        background: #003399;
        box-shadow: 0 3px #003399 inset;
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

    div.container6 {
        margin-top: 100px;
        height: 10em;
        display: flex;
        align-items: center;
        justify-content: center
    }


    div.container6 h1 {
        color: #2d31a7;
        font-size: 25px;
    }

    div.container6 h3 {
        color: #7073a7;
        font-size: 16px;
    }


</style>
<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container6">

        <div class="test_info">
            <h1>Test: "${test_name}"</h1>
            <h3>Your result: ${result}%</h3>


            <p></p>

        </div>
    </div>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
