<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diagnostic Test</title>
</head>
<body>

    <h2>Diagnostic Test</h2>

    <form action="diagnostic" method="post">
        <label for="reponses">Réponses (séparées par des virgules) :</label>
        <input type="text" name="reponses" id="reponses" placeholder="1,0,2,1,0" />

        <br/>

        <input type="submit" value="Tester le diagnostic" />
    </form>

</body>
</html>
