<%-- 
    Document   : creationprojet
    Created on : 3-Dec-2016, 9:04:32 PM
    Author     : boukhoulda
--%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<% pageContext.setAttribute("newT", "\t");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       
       
        <title>Suivi projets</title>
        <link href="${pageContext.request.contextPath}/style/mycss.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="projets_script.js" type="text/javascript"></script>
        <script>

            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <script>

            $(function () {
                $(".datepicker").datepicker();
                $.datepicker.setDefaults({
                    dateFormat: 'yy-mm-dd'
                });
            });
        </script>
    </head>
    <body>
        <div class="container">
            <c:if test="${!empty sessionScope.sessionUtilisateur}">
                <p> Vous êtes connecté <b> <c:out value="${sessionScope.sessionUtilisateur['email_utilisateur']}"/></b> / <b>${sessionScope.sessionUtilisateur['cellule_utilisateur']}</b></p> 

                <a href="${pageContext.request.contextPath}/Deconnexion" > Se déconnecter </a>
            </c:if>
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Ajouter un projet</a>
                        </h4>
                    </div>

                    <div id="collapse1" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <form action="CreerProjet" method="POST">
                                <div class="form-group">
                                    <div class="col-xs-2"> 
                                        <form  method="POST" action="CreerProjet" id="creerprojetform">
                                            <label for="graphe">Numéro de graphe: <i>(ex: X12345)</i></label>
                                            <input type="text"  name="graphe" id="graphe" value="<c:out value="${projet.graphe}" />"/> ${form.erreurs['graphe']} <br>
                                            <label for="CLLI">CLLI: <i>(ex: TOROON01)</i></label>
                                            <input type="text"  name="clli" id="clli" value="<c:out value="${projet.clli}" />"/> ${form.erreurs['clli']}<br>
                                            <label for="homepass">Nombre de homepasses:</label>
                                            <input type="text"  name="homepass" id="homepass" value="<c:out value="${projet.hp}" />"/> ${form.erreurs['homepass']}<br>
                                            <input type="hidden" name="cellule" id="cellule" value="<c:out value="${sessionScope.sessionUtilisateur['cellule_utilisateur']}"/>"/> 
                                            <button type="submit" class="btn btn-primary"> Valider </button>
                                        </form>
                                    </div>
                                </div>

                        </div>
                        <div class="panel-footer">

                        </div>
                    </div>
                </div>
            </div> 
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th rowspan="2"> Projet </th>
                        <th align= "center" colspan=2>À faire</th>
                        <th rowspan="2"> En cours </th>
                        <th rowspan="2"> Complété </th>
                    </tr>
                    <tr>
                        <th>24H</th>
                        <th>la Semaine</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.projetdb}" var="projet"> 
                        <tr> 
                            <td>
                                <b> Graphe: </b> <c:out value="${projet.graphe}"/>  <br>
                                <b> CLLI: </b> <c:out value="${projet.clli}"/>  <br>
                                <b> Nombre de HP: <a href="#" data-toggle="modal" data-target="#hp_projet${projet.graphe}"><c:out value="${projet.hp}"/> </a><br>
                                    <b> Date création:  </b><c:out value="${projet.date}"/> </a><br/>
                                    <!-- Modal pour modification du nombre de homepasses -->
                                    <div class="modal fade" id="hp_projet${projet.graphe}" role="dialog">
                                        <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Modifier </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="UpdateDonnees" method="POST">
                                                        <input type="hidden" value="<c:out value="${projet.graphe}"/>" name="graphe"/>
                                                        <input type="text" value="<c:out value="${projet.hp}"/>" name="homepass"/>
                                                        <input type="hidden" value="<c:out value="${projet.moisLivraison}"/>" name="mois_livraison"/>
                                                        <button type="submit" class="btn btn-default">Modifier</button>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <b> Mois livraison:  <a href="#" data-toggle="modal" data-target="#mois_livraison_projet${projet.graphe}"><c:out value="${projet.moisLivraison}"/> </a></b>
                                    <!-- Modal pour modification du -nombre de homepasses -->
                                    <div class="modal fade" id="mois_livraison_projet${projet.graphe}" role="dialog">
                                        <div class="modal-dialog modal-sm">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Modifier </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form  id="livraison${projet.graphe}" method="POST" action="UpdateDonnees">
                                                        <input type="hidden" id="ID" name="x" value="livraison${projet.graphe}"> 
                                                        <input type="hidden" id="IDLIV" name="y" value="mois_liv${projet.graphe}"> 


                                                        <select id="mois_liv${projet.graphe}" name="mois_livraison" >
                                                            <option value="<c:out value="${projet.moisLivraison}"/>" selected><c:out value="${projet.moisLivraison}"/></option>
                                                            <option value="Janvier">Janvier</option>
                                                            <option value="Février">Février</option>
                                                            <option value="Mars">Mars</option>
                                                            <option value="Avril">Avril</option>
                                                            <option value="Mai">Mai</option>
                                                            <option value="Juin">Juin</option>
                                                            <option value="Juiillet">Juillet</option>
                                                            <option value="Août">Août</option>
                                                            <option value="Septembre">Septembre</option>
                                                            <option value="Octobre">Octobre</option>
                                                            <option value="Novembre">Novembre</option>
                                                            <option value="Décembre">Décembre</option>


                                                        </select>
                                                        <input type="hidden" name="graphe" value="<c:out value="${projet.graphe}"/>"/>
                                                        <input type="hidden" name="homepass" value="<c:out value="${projet.hp}"/>"/>
                                                        <button type="submit" class="btn btn-default">Modifier</button>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                                        <br/>                 
                            </td>
                            
                                 <td>

                                <c:forEach items="${sessionScope.tachedb}" var="tache">
                                    <c:if test="${(tache.graphe == projet.graphe) && (tache.statut == '24')}">
                                        <c:set var="divcolor" value="${tache.couleur}"/>
                                        <div class="<c:out value="${divcolor}div"/>"/>
                                        <span data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}24s">
                                            <a href="#" data-toggle="tooltip"   title="${tache.commentaire}">${tache.taches}</a><br/>
                                        </span>

                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal${projet.graphe}${tache.id}24s" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modifier tâche</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="UpdateTache" method="POST">
                                                            <div class="form-group">
                                                                <input type="hidden" value="${tache.statut}" name="statut"/>
                                                                <input type="hidden"  value="${tache.id}" name="id" />
                                                                <label for="tache">Tache:</label><input type="text" class= "form-control" value="${tache.taches}" name="tache"/>
                                                                <label for="date_eventuelle">Date éventuelle:</label><input type="text" class= "datepicker" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                                <label for="date_reelle">Date réelle:</label><input type="text" class= "datepicker" value="${tache.date_reelle}" name="date_reelle"/>
                                                                <input type="hidden" value="${tache.date_creation}" name="date_creation"/>
                                                                <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                                <label for="commentaire">Commentaire:</label><textarea  rows="5" class= "form-control" name="commentaire">${tache.commentaire}</textarea>
                                                                <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                                <button type="submit" class="btn btn-default">Modifier</button>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </form>
                                                    </div>

                                                </div>
                                            </div>                     
                                        </div>

                                        Date création: <c:out value="${tache.date_creation}"/><br/>
                                        <!-- Trigger the modal with a link -->
                                        <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}24">Déplacer vers</a>

                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal${projet.graphe}${tache.id}24" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modal Header</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="DeplacerTache" method="POST">
                                                            <select name="statut">
                                                                <option value="Semaine">Semaine</option>
                                                                <option value="En cours">En cours</option>
                                                                <option value="Complété">Complété</option>
                                                            </select>
                                                            <input type="hidden"  value="${tache.id}" name="id" />
                                                            <input type="hidden" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                            <input type="hidden" value="${tache.date_reelle}" name="date_reelle"/>
                                                            <input type="hidden" value="${tache.creation}" name="date_creation"/>
                                                            <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                            <input type="hidden" value="${tache.commentaire}" name="commentaire"/>
                                                            <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                            <button type="submit" class="btn btn-default">Déplacer vers</button>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                        </form>
                                                    </div>

                                                </div>
                                            </div>                     
                                        </div>
                                        </div>

                                    </c:if>
                                </c:forEach>

                                <!-- Trigger the modal with a link -->
                                <br>
                                <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}24">Ajouter</a>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal${projet.graphe}24" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Ajouter une tâche</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form action="AjouterTache" method="post">
                                                    <label for="tache">Tâche:</label> <input type="text" name="tache" id="tache"  value=""/><br/>

                                                    <input type="hidden" name="date_eventuelle" value="2017-03-08"/>
                                                    <input type="hidden" name="date_reelle" value="2017-03-08"/>
                                                    <input type="hidden" name="graphe" value="${projet.graphe}"/>
                                                    <label for="commentaire">Commentaire:</label>
                                                    <textarea  rows="5" class= "form-control" name="commentaire">${tache.commentaire}</textarea>                                                    <label for="pour_utilisateur">Destinée à:</label>
                                                    <select name="pour_utilisateur" id="pour_utilisateur">
                                                        <option value="<c:out value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>" onselect=""> <c:out value="${sessionScope.sessionUtilisateur['email_utilisateur']}"/> </option>
                                                        <c:forEach items="${sessionScope.utilisateurscell}" var="userscell">
                                                            <option value="<c:out value="${userscell.couleur_utilisateur}"/>"> <c:out value="${userscell.email_utilisateur}"/> </option> 
                                                        </c:forEach>
                                                    </select><br/>
                                                    <input type="hidden" name="couleur" value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>
                                                    <input type="hidden" name="statut" value="24"/>
                                                    <button type="submit" class="btn btn-default">Ajouter</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>                    
                            </td>
                            <td>
                                <c:forEach items="${sessionScope.tachedb}" var="tache">
                                    <c:if test="${(tache.graphe == projet.graphe) && (tache.statut == 'Semaine')}">
                                        <c:set var="divcolor" value="${tache.couleur}"/>
                                        <div class="<c:out value="${divcolor}div"/>"/>
                                        <span data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}Semaines">
                                            <a href="#" data-toggle="tooltip"   title="${tache.commentaire}">${tache.taches}</a><br/>
                                        </span>

                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal${projet.graphe}${tache.id}Semaines" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modifier tâche</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="UpdateTache" method="POST">
                                                            <div class="form-group">
                                                                <input type="hidden" value="${tache.statut}" name="statut"/>
                                                                <input type="hidden"  value="${tache.id}" name="id" />
                                                                <label for="tache">Tache:</label><input type="text" class= "form-control" value="${tache.taches}" name="tache"/>
                                                                <label for="date_eventuelle">Date éventuelle:</label><input type="text" class= "datepicker" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                                <label for="date_reelle">Date réelle:</label><input type="text" class= "datepicker" value="${tache.date_reelle}" name="date_reelle"/>
                                                                <input type="hidden" value="${tache.date_creation}" name="date_creation"/>
                                                                <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                                <label for="commentaire">Commentaire:</label><textarea  rows="5" class= "form-control" name="commentaire">${tache.commentaire}</textarea>
                                                                <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                                <button type="submit" class="btn btn-default">Modifier</button>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </form>
                                                    </div>

                                                </div>
                                            </div>                     
                                        </div>

                                        <!-- Trigger the modal with a link -->
                                        <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}Semaine">Déplacer vers</a>

                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal${projet.graphe}${tache.id}Semaine" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modal Header</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="DeplacerTache" method="POST">
                                                            <select name="statut">
                                                                <option value="24">24h</option>
                                                                <option value="En cours">En cours</option>
                                                                <option value="Complété">Complété</option>
                                                            </select>
                                                            <input type="hidden"  value="${tache.id}" name="id" />
                                                            <input type="hidden" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                            <input type="hidden" value="${tache.date_reelle}" name="date_reelle"/>
                                                            <input type="hidden" value="${tache.creation}" name="date_creation"/>
                                                            <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                            <input type="hidden" value="${tache.commentaire}" name="commentaire"/>
                                                            <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                            <button type="submit" class="btn btn-default">Déplacer vers</button>
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                                                        </form>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <!-- Trigger the modal with a link -->
                                <br>
                                <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}Semaine">Ajouter</a>

                               <!-- Modal -->
                                <div class="modal fade" id="myModal${projet.graphe}Semaine" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Ajouter une tâche</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form action="AjouterTache" method="post">
                                                    <label for="tache">Tâche:</label> <input type="text" name="tache" id="tache"  value=""/><br/>

                                                    <input type="hidden" name="date_eventuelle" value="2017-03-08"/>
                                                    <input type="hidden" name="date_reelle" value="2017-03-08"/>
                                                    <input type="hidden" name="graphe" value="${projet.graphe}"/>
                                                    <label for="commentaire">Commentaire:</label>
                                                    <textarea  rows="5" class= "form-control" name="commentaire">${tache.commentaire}</textarea>                                                    <label for="pour_utilisateur">Destinée à:</label>
                                                    <select name="pour_utilisateur" id="pour_utilisateur">
                                                        <option value="<c:out value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>" onselect=""> <c:out value="${sessionScope.sessionUtilisateur['email_utilisateur']}"/> </option>
                                                        <c:forEach items="${sessionScope.utilisateurscell}" var="userscell">
                                                            <option value="<c:out value="${userscell.couleur_utilisateur}"/>"> <c:out value="${userscell.email_utilisateur}"/> </option> 
                                                        </c:forEach>
                                                    </select><br/>
                                                    <input type="hidden" name="couleur" value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>
                                                    <input type="hidden" name="statut" value="Semaine"/>
                                                    <button type="submit" class="btn btn-default">Ajouter</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>                     
                            </td>
                            <td>
                                <c:forEach items="${sessionScope.tachedb}" var="tache">
                                    <c:if test="${(tache.graphe == projet.graphe) && (tache.statut == 'En cours')}">
                                        <c:set var="divcolor" value="${tache.couleur}"/>
                                        <div class="<c:out value="${divcolor}div"/>"/>
                                        <span data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}Encourss">
                                            <a href="#" data-toggle="tooltip"   title="${tache.commentaire}">${tache.taches}</a><br/>
                                        </span>



                                        </div>
                                        <!-- Modal -->
                                        <div class="modal fade" id="myModal${projet.graphe}${tache.id}Encourss" role="dialog">
                                            <div class="modal-dialog modal-lg" >

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modifier tâche</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div id="affichcomm${tache.id}" >
                                                            <c:set var="data" value="${tache.commentaire}"/>
                                                            <c:set var="rows" value="${fn:split(data, newLineChar)}"/>
                                                            <div style="overflow-x: scroll;overflow-y: hidden">
                                                            <table class="table table-bordered">
                                                                <c:forEach items="${rows}" var="row">
                                                                    <c:set var="columns" value="${fn:split(row, newT)}"/>
                                                                    <tr>
                                                                        <c:forEach items="${columns}" var="column">

                                                                            <td>
                                                                                <p style=" white-space: nowrap; font-size: 100%">${column}</p>   
                                                                            </td>
                                                                        </c:forEach>

                                                                    </tr>   
                                                                </c:forEach>
                                                            </table>
                                                            </div>
                                                            <form action="UpdateTache" method="POST">
                                                                <div class="form-group">
                                                                    <input type="hidden" value="${tache.statut}" name="statut"/>
                                                                    <input type="hidden"  value="${tache.id}" name="id" />
                                                                    <label for="tache">Tache:</label><input type="text" class= "form-control" value="${tache.taches}" name="tache"/>
                                                                    <label for="date_eventuelle">Date éventuelle:</label><input type="text" class= "datepicker" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                                    <label for="date_reelle">Date réelle:</label><input type="text" class= "datepicker" value="${tache.date_reelle}" name="date_reelle"/>
                                                                    <input type="hidden" value="${tache.date_creation}" name="date_creation"/>
                                                                    <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                                    <label for="commentaire">Commentaire:</label><textarea wrap="off" style=" overflow-x: auto; overflow-y: scroll" rows="5" class= "form-control" id="commentaireid${tache.id}" name="commentaire">${tache.commentaire}</textarea>
                                                                    <input type="hidden" id="copieid" value="commentaireid${tache.id}"/>

                                                                    <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                                    <button type="submit"  class="btn btn-default">Modifier</button>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </div>                     
                                            </div>

                                            <!-- Trigger the modal with a link -->
                                            <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}Encours">Déplacer vers</a>

                                            <!-- Modal -->
                                            <div class="modal fade" id="myModal${projet.graphe}${tache.id}Encours" role="dialog">
                                                <div class="modal-dialog">

                                                    <!-- Modal content-->
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            <h4 class="modal-title">Modal Header</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="DeplacerTache" method="POST">
                                                                <select name="statut">
                                                                    <option value="24">24h</option>
                                                                    <option value="En cours">Semaine</option>
                                                                    <option value="Complété">Complété</option>
                                                                </select>
                                                                <input type="hidden"  value="${tache.id}" name="id" />
                                                                <input type="hidden" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                                <input type="hidden" value="${tache.date_reelle}" name="date_reelle"/>
                                                                <input type="hidden" value="${tache.creation}" name="date_creation"/>
                                                                <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                                <input type="hidden" value="${tache.commentaire}" name="commentaire"/>
                                                                <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                                <button type="submit" class="btn btn-default">Déplacer vers</button>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>                       
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <!-- Trigger the modal with a link -->
                                <br>
                                <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}Encours">Ajouter</a>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal${projet.graphe}Encours" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Modal Header</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form action="AjouterTache" method="post">
                                                    <div class="form-group">
                                                        <label for="tache">Tâche:</label>
                                                        <textarea class="form-control" rows="5" name="tache" id="tache"></textarea>
                                                    </div>
                                                    <label for="commentaire">Commentaire</label> <textarea  rows="5" class= "form-control" id="commentaireid${tache.id}" name="commentaire">${tache.commentaire}</textarea><br/>
                                                    <label for="date_eventuelle">Date éventuelle:</label> <input  type="text" class="datepicker" id="date_eventuelle" name="dateEventuelle" id="dateEventuelle$projet.graphe" onclick="datepicker()" value=""/><br/>
                                                    <label for="date_reelle">Date réelle:</label> <input type="text" class="datepicker" id="date_reelle" name="dateReelle" onclick="datepicker()" id="dateRelle${ptojet.graphe}" value=""/><br/>
                                                    <input type="hidden" name="graphe" value="${projet.graphe}"/>
                                                    <input type="hidden" name="couleur" value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>
                                                    <input type="hidden" name="statut" value="24"/>
                                                    <button type="submit" class="btn btn-default">Ajouter</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>                    
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#affichTachesCompl${projet.graphe}">Tâches complétées</button>
                                <div id="affichTachesCompl${projet.graphe}" class="collapse">
                                    <c:forEach items="${sessionScope.tachedb}" var="tache">
                                        <c:if test="${(tache.graphe == projet.graphe) && (tache.statut == 'Complété')}">
                                            <c:set var="divcolor" value="${tache.couleur}"/>

                                            <div class="<c:out value="${divcolor}div"/>"/>
                                            <c:out value="${tache.taches}" />
                                            <!-- Trigger the modal with a link -->
                                            <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}${tache.id}Complete">Déplacer vers</a>

                                            <!-- Modal -->
                                            <div class="modal fade" id="myModal${projet.graphe}${tache.id}Complete" role="dialog">
                                                <div class="modal-dialog">

                                                    <!-- Modal content-->
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                            <h4 class="modal-title">Modal Header</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="DeplacerTache" method="POST">
                                                                <select name="statut">
                                                                    <option value="24">24h</option>
                                                                    <option value="Semaine">Semaine</option>
                                                                    <option value="En cours">En cours</option>
                                                                </select>
                                                                <input type="hidden"  value="${tache.id}" name="id" />
                                                                <input type="hidden" value="${tache.date_eventuelle}" name="date_eventuelle"/>
                                                                <input type="hidden" value="${tache.date_reelle}" name="date_reelle"/>
                                                                <input type="hidden" value="${tache.creation}" name="date_creation"/>
                                                                <input type="hidden" value="${tache.couleur}" name="couleur"/>
                                                                <input type="hidden" value="${tache.commentaire}" name="commentaire"/>
                                                                <input type="hidden" value="${tache.graphe}" name="graphe"/>
                                                                <button type="submit" class="btn btn-default">Déplacer vers</button>
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                            </form>
                                                        </div>

                                                    </div>
                                                </div>                     
                                            </div>                    
                                        </div>
                                    </c:if>
                                </c:forEach>
                                </div>
                                <!-- Trigger the modal with a link -->
                                <br>
                                <a href= "#" data-toggle="modal" data-target="#myModal${projet.graphe}Complete">Ajouter</a>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal${projet.graphe}Complete" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Ajouter une tâche</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form action="AjouterTache" method="post">
                                                    <label for="tache">Tâche:</label> <input type="text" name="tache" id="tache"  value=""/><br/>

                                                    <input type="hidden" name="date_eventuelle" value="2017-03-08"/>
                                                    <input type="hidden" name="date_reelle" value="2017-03-08"/>
                                                    <input type="hidden" name="graphe" value="${projet.graphe}"/>
                                                    <label for="commentaire">Commentaire:</label>
                                                    <textarea  rows="5" class= "form-control" name="commentaire">${tache.commentaire}</textarea>                                                    <label for="pour_utilisateur">Destinée à:</label>
                                                    <select name="pour_utilisateur" id="pour_utilisateur">
                                                        <option value="<c:out value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>" onselect=""> <c:out value="${sessionScope.sessionUtilisateur['email_utilisateur']}"/> </option>
                                                        <c:forEach items="${sessionScope.utilisateurscell}" var="userscell">
                                                            <option value="<c:out value="${userscell.couleur_utilisateur}"/>"> <c:out value="${userscell.email_utilisateur}"/> </option> 
                                                        </c:forEach>
                                                    </select><br/>
                                                    <input type="hidden" name="couleur" value="${sessionScope.sessionUtilisateur['couleur_utilisateur']}"/>
                                                    <input type="hidden" name="statut" value="Complete"/>
                                                    <button type="submit" class="btn btn-default">Ajouter</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>                             
                            </td>
                   
</tr>

                    </c:forEach>

     
                </tbody>
            </table>     
            ${statut}
    
        </body>
</div>
</html>
