/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.forms;

import com.artsfx.beans.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boukhoulda
 */
public class ConnexionUtilisateur {
       private static final String CHAMP_USER = "username";
    private static final String CHAMP_PASS = "password";
private static final String CHAMP_CELLULE = "cellule";
private static final String CHAMP_COULEUR = "couleur";


    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();
    
    public String getResultat() {
    return resultat;
    }
    
    public Map<String, String> getErreurs() {
    return erreurs;
    }
    Utilisateur utilisateur = new Utilisateur();
    
    public Utilisateur connecterUtilisateur(HttpServletRequest request) {
        String username = getValeurChamp(request, CHAMP_USER);
        String password = getValeurChamp(request, CHAMP_PASS);
        String couleur = getValeurChamp(request, CHAMP_COULEUR);
        try {
            validationUtilisateur(username);
        } catch (Exception e) {
            setErreurs(CHAMP_USER, e.getMessage());
        }
        
        utilisateur.setEmail(username);
        try {
            validationMotDePasse(password);
        } catch (Exception e) {
            setErreurs(CHAMP_PASS, e.getMessage());
        }
        utilisateur.setMotDePasse(password);
        utilisateur.setCouleur(couleur);
       //if (erreurs.isEmpty()) {
      // resultat = "Vous êtes connecté";
       //} else resultat = "Échec de connexion";
           
        return utilisateur;

    }
    
   
    
    private static void validationUtilisateur(String email) throws Exception {
        
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse email valide.");
            } 
        } else {
                throw new Exception("Veuillez saisir votre adresse email.");
            }
    }
    
    private static void validationMotDePasse(String motDePasse) throws Exception {
        if (motDePasse != null ) {
                if (motDePasse.trim().length() < 3) {
                    throw new Exception("Veuillez entrer un mot de passe valide.");
                } 
        } else {
            throw new Exception("Veuillez SVP entrer un mot de passe.");
        }
    }
    
    
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    private void setErreurs(String champ, String message) {
        erreurs.put(champ, message);
    }
    
}
