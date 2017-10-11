/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.bdd;

import com.artsfx.beans.Utilisateur;
import com.artsfx.forms.ConnexionUtilisateur;
import com.artsfx.forms.InscriptionForm;
import com.artsfx.forms.ProjetForm;
import com.artsfx.forms.TacheForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.http.HttpSession;
import java.sql.Date;
/**
 *
 * @author boukhoulda
 */
public class ConnectBDD {

    String driverLink = "com.mysql.jdbc.Driver";
    private final List<String> messages = new ArrayList<>();
    private final Map<String, Object> dataUtilisateur = new Hashtable<>();
    private  List<Object> projets = new ArrayList<>();
    private  List<Object> listUtilisateurs = new ArrayList<>();
    private  List<Object> taches = new ArrayList<>();
    private final static String ATT_SESSION = "sessionUtilisateur";
    private String a;
    ResultSet resultat = null;


    /* Tester le statut de connexion a la bdd */
    public String testStatut(HttpServletRequest request) {
        return a;
    }

    private Connection getDBConnection() {
        /* ****************Chargement du driver JDBC pour MySQL******************* */
        Connection connexion = null;
        try {
            Class.forName(driverLink);
        } catch (ClassNotFoundException e) {
            messages.add("Erreur lors du chargement: Le driver n'a pas été trouvé dans le classpath ! <br/>" + e.getMessage());
        }
        /*  ************************************************************************ */

 /*  *********************Connexion à la base de données********************* */
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/suivi_projet?useUnicode=true&characterEncoding=utf-8";
            String user = "lokman";
            String pass = "Grinse16";
            connexion = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            messages.add("Erreur lors de la connexion <br/>" + e.getMessage());
        }
        return connexion;
    }
    
    public Hashtable<String, Object> selectDataUtilisateur(HttpServletRequest request) {
        
        InscriptionForm form = new InscriptionForm();
        ConnexionUtilisateur form_user = new ConnexionUtilisateur();
        String email = form_user.connecterUtilisateur(request).getEmail();
        Connection connexion = null;
        String selectSQL = "SELECT id, nom, prenom, email, mot_de_passe, cellule, couleur_utilisateur, date_inscription FROM Utilisateurs WHERE email= ?;";
        PreparedStatement preparedStatement = null;
        try {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            /* Execution d'une requête de lecture */
            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                int idUtilisateur = resultat.getInt("id");
                dataUtilisateur.put("id_utilisateur", idUtilisateur);
                String nomUtilisateur = resultat.getString("nom");
                dataUtilisateur.put("nom_utilisateur", nomUtilisateur);
                String prenomUtilisateur = resultat.getString("prenom");
                dataUtilisateur.put("prenom_utilisateur", prenomUtilisateur);
                String emailUtilisateur = resultat.getString("email");
                dataUtilisateur.put("email_utilisateur", emailUtilisateur);
                String motDePasseUtilisateur = resultat.getString("mot_de_passe");
                dataUtilisateur.put("mdp_utilisateur", motDePasseUtilisateur);
                String celluleUtilisateur = resultat.getString("cellule");
                dataUtilisateur.put("cellule_utilisateur", celluleUtilisateur);
                String couleurUtilisateur = resultat.getString("couleur_utilisateur");
                dataUtilisateur.put("couleur_utilisateur", couleurUtilisateur);
            }
        } catch (SQLException e) {
            messages.add("Erreur d'importation des données utilisateur."+ e.getMessage());
            /* Formatage des données pour affichage dans la jsp finale */
        } 
                finally {
            if (resultat != null) {
            try {
        resultat.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return (Hashtable<String, Object>) dataUtilisateur;
    }

        public List<Object> selectUtilisateursCellule(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ConnexionUtilisateur form_user = new ConnexionUtilisateur();
        String cellule = (String) session.getAttribute("cellreq");
        Connection connexion = null;
        String selectSQL = "SELECT email, couleur_utilisateur FROM Utilisateurs WHERE cellule= ?;";
        PreparedStatement preparedStatement = null;
        try {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(selectSQL);
            preparedStatement.setString(1, cellule);
            /* Execution d'une requête de lecture */
            resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
    
            Map<String, Object> utilisateursCellule = new HashMap<>();
                String emailUtilisateur = resultat.getString("email");
                utilisateursCellule.put("email_utilisateur", emailUtilisateur);
      
                String couleurUtilisateur = resultat.getString("couleur_utilisateur");
                utilisateursCellule.put("couleur_utilisateur", couleurUtilisateur);
                listUtilisateurs.add(utilisateursCellule);
            }
        } catch (SQLException e) {
            messages.add("Erreur d'importation des données utilisateur."+ e.getMessage());
            /* Formatage des données pour affichage dans la jsp finale */
        } 
                finally {
            if (resultat != null) {
            try {
        resultat.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return  listUtilisateurs;
    }

        public List<Object> selectDataProjet(HttpServletRequest request) {
           // ConnexionUtilisateur userConnect = new ConnexionUtilisateur();
            //Utilisateur utilisateur = userConnect.connecterUtilisateur(request);
           HttpSession session = request.getSession();
            String cellule = (String) session.getAttribute("cellreq");
         PreparedStatement preparedStatement = null;
        Connection connexion = null;
        String selectSQL = "SELECT * FROM Projets WHERE cellule = ?;";
        try {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(selectSQL);
                         preparedStatement.setString(1, cellule);


            /* Execution d'une requête de lecture */
          
            resultat = preparedStatement.executeQuery();
            
            while (resultat.next()) {
               Map<String, Object> dataProjets = new HashMap<>();
       //       Map<String, Object> dataTaches = new HashMap<>();
         //        dataProjets.put("graphe", resultat.getString("Graphe"));
                //dataProjets.put("taches", resultat.getString("Taches"));
                 dataProjets.put("graphe", resultat.getString("Graphe"));
                dataProjets.put("clli", resultat.getString("CLLI"));
                dataProjets.put("hp", resultat.getString("HP"));
                dataProjets.put("date", resultat.getDate("DateCreation")) ;
                dataProjets.put("moisLivraison", resultat.getString("mois_livraison"));
                projets.add(dataProjets);
                //dataProjets.put("date", (ArrayList<Date>) dateCreation_data);
            }
                preparedStatement.close();
                resultat.close();
        } catch (SQLException e) {
            messages.add("Erreur d'importation des données projets."+ e.getMessage());
            /* Formatage des données pour affichage dans la jsp finale */
        }
                finally {
            if (resultat != null) {
            try {
        resultat.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return  projets;
    }

        
            public List<Object> selectTaches(HttpServletRequest request) {
         PreparedStatement preparedStatement = null;
        Connection connexion = null;
        String selectSQL = "SELECT * FROM taches;";
        try {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(selectSQL);


            /* Execution d'une requête de lecture */
          
            resultat = preparedStatement.executeQuery();
            
            while (resultat.next()) {
               Map<String, Object> dataTaches = new HashMap<>();
               dataTaches.put("id", resultat.getInt("Id"));
                 dataTaches.put("graphe", resultat.getString("Graphe"));
                dataTaches.put("taches", resultat.getString("Taches"));
                dataTaches.put("statut", resultat.getString("Statut"));
                dataTaches.put("couleur", resultat.getString("couleur"));
              dataTaches.put("date_creation", resultat.getDate("date_creation")) ;
              dataTaches.put("date_eventuelle", resultat.getDate("date_eventuelle")) ;
              dataTaches.put("date_reelle", resultat.getDate("date_reelle")) ;
              dataTaches.put("commentaire", resultat.getString("commentaire"));
              //  dataProjets.put("taches", resultat.getString("Tache"));
                taches.add(dataTaches);
                //dataProjets.put("date", (ArrayList<Date>) dateCreation_data);
            }
        } catch (SQLException e) {
            messages.add("Erreur d'importation des données projets."+ e.getMessage());
            /* Formatage des données pour affichage dans la jsp finale */
        }
        finally {
            if (resultat != null) {
            try {
        resultat.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return  taches;
    }
    
    /* ******************************************************************* */
 /* **************** Créer l'objet formulaire d'inscription et rapporter les champs remplis par l'utilisateur */
    public List<String> insertDataUtilisateur(HttpServletRequest request) {
        InscriptionForm form = new InscriptionForm();
        String nom = form.inscrireUtilisateur(request).getNom();
        String prenom = form.inscrireUtilisateur(request).getPrenom();
        String email = form.inscrireUtilisateur(request).getEmail();
        String motDePasse = form.inscrireUtilisateur(request).getMotDePasse();
        String cellule = form.inscrireUtilisateur(request).getCellule();
        String couleur = form.inscrireUtilisateur(request).getCouleur();

        LocalDateTime dateInscription = form.inscrireUtilisateur(request).getDateInscription();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO Utilisateurs (nom, prenom, email,mot_de_passe, cellule, couleur_utilisateur, date_inscription) VALUES(?, ?, ?, MD5(?), ?,?, ?);";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(insertSQL);
            
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, motDePasse);
            preparedStatement.setString(5, cellule);
            preparedStatement.setString(6, couleur);

            preparedStatement.setTimestamp(7, Timestamp.valueOf(dateInscription));
            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);

            
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");

            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible d'inscrire l'utilisateur."+ e.getMessage());
        }
        
        finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return messages;
    }

        public List<String> insertNouveauProjet(HttpServletRequest request) {
        ProjetForm projetForm = new ProjetForm();
        String graphe = projetForm.creerProjet(request).getGraphe();
        String clli = projetForm.creerProjet(request).getClli();
        String hp = projetForm.creerProjet(request).getHp();
        String cellule = projetForm.creerProjet(request).getCellule();

        LocalDateTime dateCreation = projetForm.creerProjet(request).getDateCreation();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO Projets (Graphe, CLLI, HP, DateCreation, cellule) VALUES(?, ?, ?, ?, ?);";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
 if (projetForm.getErreurs().isEmpty()) {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(insertSQL);
            preparedStatement.setString(1, graphe);
            preparedStatement.setString(2, clli);
            preparedStatement.setString(3, hp);
            preparedStatement.setTimestamp(4, Timestamp.valueOf(dateCreation));
            preparedStatement.setString(5, cellule);

            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");
 }
            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible de créer le projet."+ e.getMessage());
        }
        finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return messages;
    }

         public List<String> insertNouvelleTache(HttpServletRequest request) {
        TacheForm tacheForm = new TacheForm();
        ConnexionUtilisateur connecterUtilisateur = new ConnexionUtilisateur();
        Utilisateur utilisateur = connecterUtilisateur.connecterUtilisateur(request);
        String tache = tacheForm.ajouterTache(request).getTache();
        String graphe = tacheForm.ajouterTache(request).getGraphe();
        String statutTache = tacheForm.ajouterTache(request).getStatut();
        String couleurTache = request.getParameter("pour_utilisateur");
        int id = tacheForm.ajouterTache(request).getId();
        LocalDateTime dateCreation = tacheForm.ajouterTache(request).getDateCreation();
        LocalDate dateEventuelle =  tacheForm.ajouterTache(request).getDateEventuelle();
        LocalDate dateReelle = tacheForm.ajouterTache(request).getDateReelle();
        String commentaire = tacheForm.ajouterTache(request).getCommentaire();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO taches (Graphe, Taches, Statut, couleur, Id, date_creation, date_eventuelle, date_reelle, commentaire) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
 //if (tacheForm.getErreurs().isEmpty()) {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(insertSQL);
            preparedStatement.setString(1, graphe);
            preparedStatement.setString(2, tache);
            preparedStatement.setString(3, statutTache);
            preparedStatement.setString(4, couleurTache);
            preparedStatement.setInt(5, id);
            preparedStatement.setTimestamp(6, Timestamp.valueOf(dateCreation));
            preparedStatement.setDate(7, Date.valueOf(dateEventuelle));
            preparedStatement.setDate(8, Date.valueOf(dateReelle));
            preparedStatement.setString(9, commentaire);



           // preparedStatement.setString(5, tacheCom);

            //preparedStatement.setTimestamp(4, Timestamp.valueOf(dateCreation));
            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");
 //}
            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible de créer le projet."+ e.getMessage());
        }
        finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return messages;
    }
         
                  public List<String> deplacerTache(HttpServletRequest request) {
        TacheForm tacheForm = new TacheForm();
        //ProjetForm projetForm = new ProjetForm();
        String graphe = tacheForm.ajouterTache(request).getGraphe();
        String statutTache = tacheForm.ajouterTache(request).getStatut();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        //LocalDateTime dateCreation = tacheForm.ajouterTache(request).getDateCreation();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String updateSQL = "UPDATE taches SET Statut=? WHERE Id = ?;";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
 //if (tacheForm.getErreurs().isEmpty()) {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(updateSQL);
            preparedStatement.setString(1, statutTache);
             preparedStatement.setInt(2, id);
           // preparedStatement.setString(5, tacheCom);

            //preparedStatement.setTimestamp(4, Timestamp.valueOf(dateCreation));
            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");
 //}
            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible de créer le projet."+ e.getMessage());
        } finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }

        return messages;
    }


        public List<String> updateDateCreaProjet(HttpServletRequest request) {
        ProjetForm projetForm = new ProjetForm();
        String graphe = projetForm.creerProjet(request).getGraphe();
        String hp = projetForm.creerProjet(request).getHp();
        String moisLivraison = projetForm.creerProjet(request).getMoisLivraison();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "UPDATE Projets SET HP=?, mois_livraison=? WHERE Graphe=?;";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
 if (projetForm.creerProjet(request).getGraphe() != null && projetForm.creerProjet(request).getHp() != null && projetForm.creerProjet(request).getMoisLivraison() != null) {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(insertSQL);
            preparedStatement.setString(1, hp);
            preparedStatement.setString(2, moisLivraison);
            preparedStatement.setString(3, graphe);
            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");
 }
            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible de créer le projet."+ e.getMessage());
        }
        finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return messages;
    }
         public List<String> updateTache(HttpServletRequest request) {
        TacheForm tacheForm = new TacheForm();
        ConnexionUtilisateur connecterUtilisateur = new ConnexionUtilisateur();
        Utilisateur utilisateur = connecterUtilisateur.connecterUtilisateur(request);
        String tache = tacheForm.ajouterTache(request).getTache();
        LocalDate dateEventuelle =  tacheForm.ajouterTache(request).getDateEventuelle();
        LocalDate dateReelle = tacheForm.ajouterTache(request).getDateReelle();
        String commentaire = tacheForm.ajouterTache(request).getCommentaire();        String statutTache = tacheForm.ajouterTache(request).getStatut();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        LocalDateTime dateCreation = tacheForm.ajouterTache(request).getDateCreation();

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        String updateSQL = "UPDATE taches set taches=?, date_eventuelle=?, date_reelle=?, commentaire = ? where id=?;";

        try {
            /* *********************************************** */
 /* Création de l'objet gérant les requêtes */
 //if (tacheForm.getErreurs().isEmpty()) {
            connexion = getDBConnection();
            preparedStatement = connexion.prepareStatement(updateSQL);
            preparedStatement.setString(1, tache);
            preparedStatement.setDate(2, Date.valueOf(dateEventuelle));
            preparedStatement.setDate(3, Date.valueOf(dateReelle));
            preparedStatement.setString(4, commentaire);
            preparedStatement.setInt(5, id);

           // preparedStatement.setString(5, tacheCom);

            //preparedStatement.setTimestamp(4, Timestamp.valueOf(dateCreation));
            int statut = preparedStatement.executeUpdate();
            a = Integer.toString(statut);
            //messages.add("Résultat de la requête d'insertion préparée : " + statut + ".");
 //}
            //messages.add("Requête \"SELECT id, nom, prenom, email, mot_de_passe, cellule, date_inscription FROM Utilisateurs;\" effectuée !");
        } catch (SQLException e) {
            messages.add("Impossible de créer le projet."+ e.getMessage());
        }
        finally {
        if (preparedStatement != null) {
            try {
        preparedStatement.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
         if (connexion != null) {
            try {
        connexion.close();
            } catch 
                (SQLException ignore) {
                    }
                    }
        }
        return messages;
    }


}
