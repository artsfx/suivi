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
import java.util.Hashtable;
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
public class CreerProjet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String VUE = "/Affichage"; ///WEB-INF/pages/creationprojet.jsp";

    private final static String ATT_MESSAGES = "messages";
    private final static String ATT_SESSION = "sessionUtilisateur";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20 * 60);
        ProjetForm form = new ProjetForm();
        Projet projet = form.creerProjet(request);
        ConnectBDD test = new ConnectBDD();
                Hashtable<String, Object> dataUtilisateur = test.selectDataUtilisateur(request);

        List<String> messages = test.insertNouveauProjet(request);
        String statut = test.testStatut(request);
        request.setAttribute("statut", statut);

        request.setAttribute(ATT_MESSAGES, messages);
        request.setAttribute("form", form);
        if (session.getAttribute(ATT_SESSION) == null) {
            response.sendRedirect(request.getContextPath() + "/Connexion");
        } else {
  
            session.getAttribute("cellreq");
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
