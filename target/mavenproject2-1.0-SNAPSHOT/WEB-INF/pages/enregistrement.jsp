<%-- 
    Document   : test_jdbc
    Created on : 21-Nov-2016, 11:27:35 PM
    Author     : boukhoulda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/form.css"/>" />
    </head>
    <body>
        <c:choose>
        <c:when test="${statut == '1'}">
            <p> Vous êtes inscrit(e)</p>
        </c:when>
            <c:otherwise>
                <p>Erreur de connexion, veuillez réessayer plus tard.</p>
            </c:otherwise>
        </c:choose>
                <c:out value="${userdb['nom_utilisateur']} ${userdb['nom_utilisateur']}"/> 
         
               <c:forEach items="${messages}" var="message" varStatus="boucle">
               <p> ${boucle.count}.${message}</p>
           </c:forEach>
    
    </body>
</html>
