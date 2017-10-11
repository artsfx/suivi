/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.servlets;

import com.artsfx.bdd.ConnectBDD;
import com.artsfx.beans.Utilisateur;
import com.artsfx.forms.ConnexionUtilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.artsfx.functions.HashPass;
import java.util.List;

/**
 *
 * @author boukhoulda
 */
public class Connexion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String VUE = null;
    private static final String ATT_USER = "utilisateur";
    private static final String ATT_FORM = "connexion";
    private static final String ATT_SESSION = "sessionUtilisateur";

    private static final String ATT_MESSAGES_CONN = "messages";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {

        response.setContentType("text/html;charset=UTF-8");
        // Déclarer une session
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20*60);
        ConnexionUtilisateur userConnect = new ConnexionUtilisateur();
        Utilisateur utilisateur = userConnect.connecterUtilisateur(request);
        ConnectBDD test = new ConnectBDD();
        Hashtable<String, Object> dataUtilisateur = test.selectDataUtilisateur(request);
        HashPass hashPass = new HashPass();

        String passDb = (String) dataUtilisateur.get("mdp_utilisateur");
        String username = utilisateur.getEmail();
        String userDb = (String) dataUtilisateur.get("email_utilisateur");
        String passwordToHash = utilisateur.getMotDePasse();
        if (session.getAttribute(ATT_SESSION) != null) {
            VUE = "/Affichage";
               this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
        else {
        if (userConnect.getErreurs().isEmpty()) {
            // Mot de passe entré par l'utilisateur
            // Mot de passe entré par l'utilisateur hashé
            String generatedpass = hashPass.hashPassword(passwordToHash);
            request.setAttribute("passhash", generatedpass);
            if (username.equals(userDb) && generatedpass.equals(passDb)) {
                
                String cellule = (String) dataUtilisateur.get("cellule_utilisateur");
                utilisateur.setCellule(cellule);
                session.setAttribute(ATT_SESSION, dataUtilisateur);

                session.setAttribute("cellreq", cellule);
                VUE = "/Affichage";
                String message = "Vous êtes connecté!";
                request.setAttribute(ATT_MESSAGES_CONN, message);


            }
        } else {
            session.setAttribute(ATT_SESSION, null);
            String message = "Le nom d'utilisateur ou le mot de passe est incorrect. ";
            request.setAttribute(ATT_MESSAGES_CONN, message);
            VUE = "/WEB-INF/pages/index.jsp";
        }
        }

                     //   userConnect.connecterUtilisateur(request).setCellule(cellule);

        request.setAttribute("user", username);
        request.setAttribute(ATT_FORM, userConnect);
                    //response.sendRedirect(request.getContextPath()+VUE);

       this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        //request.setAttribute(ATT_USER, utilisateur);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
