<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Amplitude: Logon</title>
    <link rel="stylesheet" href="amplitude.css" type="text/css" media="screen"/>
</head>
<body id="firstrun">
<div id="welcome" class="welcome">
    Welcome to Amplitude!
    <div id="setupFormContainer">
        <form action="setup" method="post">
            <fieldset>
                <div><label for="password" id="passwordlabel" class="row">Administrative Password </label><input
                        id="password" name="password" type="password"/></div>
                <div><label for="directory" id="directorylabel" class="row">Root Directory for Music </label><input
                        id="directory" name="directory" type="text"/></div>
                <input id="setup" class="row center" type="submit" value="Begin Setup"/>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>