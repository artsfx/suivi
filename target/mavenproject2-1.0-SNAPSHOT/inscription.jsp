<%-- 
    Document   : inscription
    Created on : 3-Dec-2016, 11:48:37 PM
    Author     : boukhoulda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method = "post" action="InscrireUtilisateur">
            <fieldset>
                <legend> Inscription: </legend>
                <label for="nom">Nom:</label>
                <input type="text" name="nom" value="<c:out value="${utilisateur.nom}" />" /> ${form.erreurs['nom']}<br>
                <label for="prenom">Prénom:</label>
                <input type="text" name="prenom" value="<c:out value="${utilisateur.prenom}" />" />${form.erreurs['prenom']}<br>     
                <label for="email">E-mail:</label>
                <input type="text" name="email" value="<c:out value="${utilisateur.email}" />" />${form.erreurs['email']}<br>
                <label for="motdepaasse">Mot de passe:</label>
                <input type="password" name="motdepasse" value=""/>${form.erreurs['motdepasse']}<br>
                <label for="password">Confirmation: </label>
                <input type="password" name="confirmation" value=""/><br>
                <label for="cellule">Cellule:</label>
                <label for="couleur">Confirmation: </label>
                <select name="couleur">
                    <option value="lightgreen">Vert</option>
                    <option value="deepskyblue">Bleu</option>
                    <option value="hotpink">Rose</option>
                    <option value="yellow">Jaune</option>
                </select>
                <select name="cellule">
                    <option value="lightcell">LightCell</option>
                    <option value="duracell">Duracell</option>

                </select><br>
                <input type="submit" name="inscription" value="Inscription">
            </fieldset>
        </form>
    </body>
</html>
