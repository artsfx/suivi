/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.servlets;

import com.artsfx.bdd.ConnectBDD;
import com.artsfx.beans.Projet;
import com.artsfx.beans.Tache;
import com.artsfx.forms.ProjetForm;
import com.artsfx.forms.TacheForm;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author boukhoulda
 */
public class Affichage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String VUE = "/WEB-INF/pages/creationprojet.jsp";
    private final static String ATT_SESSION = "sessionUtilisateur";
    private final static String ATT_SESSION_DB_PROJET = "projetdb";
    private final static String ATT_SESSION_DB_TACHE = "tachedb";
    private final static String ATT_SESSION_DB_USER_CELL = "utilisateurscell";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20 * 60);
        ConnectBDD test = new ConnectBDD();

        List<Object> tachedb = test.selectTaches(request);
        String statut = test.testStatut(request);
        request.setAttribute("statut", statut);
        // Mettre les champs de formulaire de creation de projet dans un attribut

        // Mettre les messages de BDD dans un attribut
        List<Object> projetdb = test.selectDataProjet(request);
        List<Object> utilisateursCell = test.selectUtilisateursCellule(request);
        if (session.getAttribute(ATT_SESSION) == null) {
            response.sendRedirect(request.getContextPath() + "/Connexion");
        } else {
            request.getAttribute("cellreq");
            session.setAttribute(ATT_SESSION_DB_TACHE, tachedb);
            session.setAttribute(ATT_SESSION_DB_PROJET, projetdb);
            session.setAttribute(ATT_SESSION_DB_USER_CELL, utilisateursCell);
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
