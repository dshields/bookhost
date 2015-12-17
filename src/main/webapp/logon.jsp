<%@page contentType="text/html;charset=utf-8" %>
<%
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Amplitude: Logon</title>
    <link rel="stylesheet" href="amplitude.css" type="text/css" media="screen"/>
</head>
<body>
<div id="logonarea">
    <div id="logonlabel">Amplitude Logon</div>
    <div id="logon">
        <form action="/logon">
            <fieldset>
                <label for="userName">User Name:</label><input id="userName" type="text" size="20%"/><br/>
                <label for="password">Password:</label><input id="password" type="password" size="20%"/>
            </fieldset>
        </form>
    </div>
</div>

</body>
</html>