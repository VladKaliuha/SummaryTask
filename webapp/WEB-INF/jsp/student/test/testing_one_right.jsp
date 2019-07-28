<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Testing" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
    * {
        font-family: 'Roboto', sans-serif;
    }

    @keyframes click-wave {
        0% {
            height: 40px;
            width: 40px;
            opacity: 0.35;
            position: relative;
        }
        100% {
            height: 200px;
            width: 200px;
            margin-left: -80px;
            margin-top: -80px;
            opacity: 0;
        }
    }

    .option-input {
        -webkit-appearance: none;
        -moz-appearance: none;
        -ms-appearance: none;
        -o-appearance: none;
        appearance: none;
        position: relative;
        top: 5px;
        right: 0;
        bottom: 0;
        left: 0;
        height: 20px;
        width: 20px;
        transition: all 0.15s ease-out 0s;
        background: #cbd1d8;
        border: none;
        color: #fff;
        cursor: pointer;
        display: inline-block;
        margin-right: 0.5rem;
        outline: none;
        position: relative;
        z-index: 1000;
    }

    .option-input:hover {
        background: #999dff;
    }

    .option-input:checked {
        background: #2d41e0;
    }

    .option-input:checked::before {
        height: 40px;
        width: 40px;
        position: absolute;
        display: inline-block;
        font-size: 26.66667px;
        text-align: center;
        line-height: 40px;
    }

    .option-input:checked::after {
        -webkit-animation: click-wave 0.65s;
        -moz-animation: click-wave 0.65s;
        animation: click-wave 0.65s;
        background: #2d41e0;
        display: block;
        position: relative;
        z-index: 100;
    }

    .option-input.radio {
        border-radius: 50%;
    }

    .option-input.radio::after {
        border-radius: 50%;
    }

    table.test {
        min-width: 300px; /* Ширина таблицы */
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }

    td {
        text-align: left; /* Выравниваем текст по центру ячейки */
    }

    th {
        text-align: center; /* Выравниваем текст по центру ячейки */
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


            <c:forEach var="answer" items="${answer_list}">
                <tr>
                    <td>
                        <input type="radio" class="option-input radio" name="user_answer"
                               value="${answer.text}">${answer.text}
                    </td>
                </tr>
            </c:forEach>

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
