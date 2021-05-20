/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.services.AuthentificationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aurélien
 */
public class LoginClientAction extends Action {
    private final AuthentificationService authentificationService = new AuthentificationService();
    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Client client = authentificationService.authentificateClient(login, password);
        System.out.println("Client = " + client);
        request.setAttribute("client", client);
        
        //Update the session client id
        if(client != null) {
            HttpSession session = request.getSession();
            session.setAttribute("clientId", client.getId());
        }
    }
}