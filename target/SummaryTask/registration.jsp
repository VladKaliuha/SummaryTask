<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Registration"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
    #test:invalid {
        color: red
    }
    table{
        margin: auto;
    }
    .content {
        text-align: center;
    }
    input[type=text] {
        padding: 5px;
        border: none;
        border-bottom: 2px solid blue;
    }

    input[type=password] {
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

<body>

<%--===========================================================================
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>
<table id="main-container">

    <%--===========================================================================
    This is the HEADER, containing a top menu.
    header.jspf contains all necessary functionality for it.
    Just included it in this JSP document.
    ===========================================================================--%>

    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>

    <%--===========================================================================
    This is the CONTENT, containing the main part of the page.
    ===========================================================================--%>
    <div class="content">
        <tr>
            <td class="content center">
                <%-- CONTENT --%>

                <%--===========================================================================
                Defines the web form.
                ===========================================================================--%>
                <form id="registration_form" action="controller" method="post">

                    <%--===========================================================================
                    Hidden field. In the query it will act as command=login.
                    The purpose of this to define the command name, which have to be executed
                    after you submit current form.
                    ===========================================================================--%>
                    <input type="hidden" name="command" value="registration"/>
                    <br>
                    <br>
                    <br>
                    <fieldset>
                        <legend>Name</legend>
                        <input type="text" name="first_name" required/><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Surname</legend>
                        <input type="text" name="last_name" required/><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Login</legend>
                        <input type="text" name="login" required/><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Email</legend>
                        <input type="text" name="email" pattern="^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$"
                               required><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Password</legend>
                        <input type="password" name="password" required minlength="6" , maxlength="20"/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Male</legend>
                        <select name="male">
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </fieldset>
                    <br/>

                    <input type="submit" value="Registration">
                </form>

                <%-- CONTENT --%>

            </td>
        </tr>
    </div>


</table>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>