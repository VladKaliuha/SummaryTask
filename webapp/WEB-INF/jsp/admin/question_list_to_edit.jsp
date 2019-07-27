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
        top: 7px;
        right: 0;
        bottom: 0;
        left: 0;
        height: 20px;
        width: 20px;
        transition: all 0.15s ease-out 0s;
        background: #cacfd8;
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
        background: #6194e0;
        content: '';
        display: block;
        position: relative;
        z-index: 100;
    }

    table.test {
        width: auto;
        margin: auto; /* Выравниваем таблицу по центру окна  */
    }


    .button7 {
        font-weight: 700;
        font-size: 15px;
        color: white;
        padding: .8em 1em calc(.8em + 2px);
        background: #3B5CC7;
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
<c:if test="${not empty questionList}">

    <form id="user_answer_form" action="controller" method="post">
        <table id="test" class="table test">
            <thead>
            <tr>
                <th></th>
                <th>${question.text}</th>
                <th><a href="controller?command=forwardTo&command_name=editQuestion&question_id=${question.id}">Edit</a>
                </th>
                <th><a href="controller?command=deleteQuestion&question_id=${question.id}">Delete</a></th>
            </tr>
            </thead>

            <c:forEach var="answer" items="${answer_list}">
                <tr>
                    <td>${answer.correct}</td>
                    <td>${answer.text}</td>
                    <td></td>
                    <td>
                        <a href="controller?command=deleteAnswer&answer_id=${answer.id}&question_id=${question.id}">Delete
                            answer</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td>
                    <a href="controller?command=forwardTo&command_name=create_answer&question_id=${question.id}&question_num=${question_num}">+Add
                        new
                        answer</a>
                </td>
            </tr>

            <input type="hidden" name="command" value="showQuestion"/>
            <input type="hidden" name="question_num" value="${question_num+1}"/>
            <tr>
                <td></td>
                <td>
                    <input type="submit" class="button7" value="Next">
                </td>
            </tr>
        </table>
    </form>
</c:if>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
