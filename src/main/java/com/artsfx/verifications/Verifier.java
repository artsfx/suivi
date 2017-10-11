/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artsfx.verifications;

import com.artsfx.bdd.ConnectBDD;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boukhoulda
 */
public class Verifier {
            private static final String EMAIL_CHAMP = "username";
        private static final String PASS_CHAMP = "password";
  HttpServletRequest request;
  ConnectBDD test = new ConnectBDD();
String email = request.getParameter(EMAIL_CHAMP);
String motDePasse = request.getParameter(PASS_CHAMP);

}
