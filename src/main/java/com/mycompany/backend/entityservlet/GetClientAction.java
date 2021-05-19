/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.entityservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aurélien
 */
public class GetClientAction extends Action{
    EntityService entityService = new EntityService();
    @Override
    public void executer(HttpServletRequest request) {
        
        String clientIdStr = request.getParameter("id");
        try{
            Long clientId = Long.parseLong(clientIdStr);
            Client client = entityService.searchClientById(clientId);
            System.out.println("Client = " + client);
            request.setAttribute("client", client);
        } catch(NumberFormatException e){
            System.out.println("Error, bad ID");
            request.setAttribute("client", null);
        }
        
        
    }
}
