/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.forms;

import com.artsfx.beans.Tache;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boukhoulda
 */
public class TacheForm {

    private static final String CHAMP_TACHE = "tache";
    private static final String CHAMP_GRAPHE = "graphe";
    private static final String CHAMP_STATUT = "statut";
    private static final String CHAMP_ID = "id";
    private static final String CHAMP_DATE_EVENTUELLE = "date_eventuelle";
    private static final String CHAMP_DATE_REELLE = "date_reelle";
    private static final String CHAMP_COMMENTAIRE = "commentaire";

    Tache tache = new Tache();

    public Tache ajouterTache(HttpServletRequest request) {
        String ajoutTache = request.getParameter(CHAMP_TACHE);
        String ajoutGraphe = request.getParameter(CHAMP_GRAPHE);
        String ajoutStatut = request.getParameter(CHAMP_STATUT);
        String dateEventuelle = request.getParameter(CHAMP_DATE_EVENTUELLE);
        String dateReelle = request.getParameter(CHAMP_DATE_REELLE);
        String commentaire = request.getParameter(CHAMP_COMMENTAIRE);
        LocalDate ajoutDateEventuelle = getLocalDate(dateEventuelle);
        LocalDate ajoutDateReelle = getLocalDate(dateReelle);

        tache.setTache(ajoutTache);
        tache.setGraphe(ajoutGraphe);
        tache.setStatut(ajoutStatut);
        tache.setDateCreation(LocalDateTime.now());
        tache.setDateEventuelle(ajoutDateEventuelle);
        tache.setDateReelle(ajoutDateReelle);
        tache.setCommentaire(commentaire);
        return tache;
    }

    public LocalDate getLocalDate(String dateparam) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateparam, dtf);
        return date;
    }
}
