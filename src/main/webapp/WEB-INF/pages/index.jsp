<%-- 
    Document   : index
    Created on : 3-Dec-2016, 9:09:49 PM
    Author     : boukhoulda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/mycss.css" rel="stylesheet" type="text/css"/>
             <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Suivi projet v1.0 beta</h1>
        <div class="form-group">
        <form method="post" action="Connexion" id="connex_form">
            <fieldset>
                <legend id="connex_legend"> Connexion: </legend>
                <label for="username">Nom d'utilisateur</label>
                <input name="username" id="username" type="text" class="form-control" value=""><c:out value="${connexion.erreurs['username']}"/><br>
                 <label for="password">Mot de passe</label>
                 <input name="password" id="password" type="password" class="form-control" value=""><c:out value="${connexion.erreurs['password']}"/><br>
                <input name="connect" type="submit" class="form-control" value="Se connecter"><br>
                <p>Pas encore inscrit ? <a href="inscription.jsp"> Inscription </a></p>
            <c:out value="${messages}"/>
            </fieldset>
        </form>
            </div>
</body>
</html>
