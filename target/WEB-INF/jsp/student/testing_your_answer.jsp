<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Subject" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
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

    body {
        text-align: center;
        font-family: sans-serif;
        font-weight: 100;
    }

    .countdown-title {
        color: #2a3899;
        font-weight: 100;
        font-size: 40px;
        margin: 40px 0px 20px;
    }

    .countdown {
        font-family: sans-serif;
        color: #fff;
        display: inline-block;
        font-weight: 100;
        text-align: center;
        font-size: 30px;
    }

    .countdown-number {
        padding: 10px;
        border-radius: 3px;
        background: #6479bf;
        display: inline-block;
    }

    .countdown-time {
        padding: 15px;
        border-radius: 3px;
        background: #97a3d2;
        display: inline-block;
    }

    .countdown-text {
        display: block;
        padding-top: 5px;
        font-size: 16px;
    }
</style>
<body>
<div id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <%@ include file="/WEB-INF/jspf/user_answer_list.jspf" %>

</div>
<div>
    <p><b>${question_text}</b></p>
    <p>

        <input name="user_answer"/><Br>

        <a href="controller?command=checkAnswer">Ответить</a><Br>
</div>
<div>
    <h1 class="countdown-title">Осталось</h1>
    <div id="countdown" class="countdown">

        <div class="countdown-number">
            <span class="minutes countdown-time"></span>
            <span class="countdown-text">Minutes</span>
        </div>
        <div class="countdown-number">
            <span class="seconds countdown-time"></span>
            <span class="countdown-text">Seconds</span>
        </div>
    </div>
</div>

<script>
    var minutesleft = 0; //give minutes you wish
    var secondsleft = 30; // give seconds you wish
    var finishedtext = "Countdown finished!";
    var end1;
    if (localStorage.getItem("end1")) {
        end1 = new Date(localStorage.getItem("end1"));
    } else {
        end1 = new Date();
        end1.setMinutes(end1.getMinutes() + minutesleft);
        end1.setSeconds(end1.getSeconds() + secondsleft);

    }
    var counter = function () {
        var now = new Date();
        var diff = end1 - now;

        diff = new Date(diff);

        var sec = parseInt((diff / 1000) % 60)
        var mins = parseInt((diff / (1000 * 60)) % 60)

        if (mins < 10) {
            mins = "0" + mins;
        }
        if (sec < 10) {
            sec = "0" + sec;
        }
        if (now >= end1) {
            clearTimeout(interval);
            // localStorage.setItem("end", null);
            localStorage.removeItem("end1");
            localStorage.clear();
            document.getElementById('divCounter').innerHTML = finishedtext;
            if (confirm("TIME UP!")) {
            }
        } else {
            var value = mins + ":" + sec;
            localStorage.setItem("end1", end1);
            document.getElementById('divCounter').innerHTML = value;
        }
    }
    var interval = setInterval(counter, 1000);
</script>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
