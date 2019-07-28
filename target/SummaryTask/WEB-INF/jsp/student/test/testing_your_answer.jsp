<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Testing" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>

    table.test {
        min-width: 300px; /* Ширина таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }

    td {
        text-align: center; /* Выравниваем текст по центру ячейки */
    }
    * {
        font-family: 'Roboto', sans-serif;
    }
    th{
        text-align: center; /* Выравниваем текст по центру ячейки */
    }
    .input_area{
        min-width: 250px;
    }
    .button7 {
        font-weight: 700;
        font-size: 15px;
        color: white;
        padding: .8em 1em calc(.8em + 2px);
        background: #3B5CC7;
        box-shadow: 0 -3px #0917D2 inset;
        transition: 0.2s;
        line-height: 2px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: auto;
    }

    .button7:hover {
        background: #2D31A7;
    }

    .button7:active {
        background: #003399;
    }
</style>
<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <%@ include file="/WEB-INF/jspf/user_answer_list.jspf" %>

</div>
<div class="question_div">

    <form id="user_answer_form" action="controller" method="post">

        <table id="test" class="table test">
            <thead>
            <tr>
                <th>${question_text}</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input class="input_area" type="text" name="user_answer"/><Br>
                </td>
            </tr>

            <input type="hidden" name="command" value="checkAnswer"/>
            <tr>
                <td>
                    <input type="submit" class="button7" value="<fmt:message key="testing_jsp.link.answer"/>">
                </td>
            </tr>
        </table>
    </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
