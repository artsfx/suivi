/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.servlets;

import com.artsfx.bdd.ConnectBDD;
import com.artsfx.beans.Utilisateur;
import com.artsfx.forms.InscriptionForm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author boukhoulda
 */

public class InscrireUtilisateur extends HttpServlet {
 public static String VUE = null;
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ATT_MESSAGES = "messages";
    public static final String ATT_STATUT = "statut";
    public static final String ATT_USER_DB = "userdb";


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* Stockage des résultats et des erreurs dans l'objet request */
        InscriptionForm form = new InscriptionForm();
        Utilisateur utilisateur = form.inscrireUtilisateur(request);
        request.setAttribute(ATT_USER, utilisateur);
        request.setAttribute(ATT_FORM, form);
        
           ConnectBDD test = new ConnectBDD();
           test.insertDataUtilisateur(request);
           Hashtable<String,Object> userdb =  test.selectDataUtilisateur(request);
   String statut = test.testStatut(request);
      request.setAttribute(ATT_STATUT, statut);
      request.setAttribute(ATT_USER_DB, userdb);
        /* Affichage de la page d'inscription */
        if (form.getErreurs().isEmpty()) {
           VUE = "/WEB-INF/pages/enregistrement.jsp";
        } else VUE = "/WEB-INF/pages/inscription.jsp";
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
      
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
