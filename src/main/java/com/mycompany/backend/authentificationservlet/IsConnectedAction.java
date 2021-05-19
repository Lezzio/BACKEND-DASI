/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;

import com.mycompany.backend.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aurélien
 */
public class IsConnectedAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("clientId");
        Long employeeId = (Long) session.getAttribute("employeeId");
        if(clientId != null){
            request.setAttribute("userType", "client");
            request.setAttribute("isConnected", true);
        } else if(employeeId != null){
            request.setAttribute("userType", "employee");
            request.setAttribute("isConnected", true);
        } else {
            request.setAttribute("isConnected", false);
            System.out.println("No employeeId or clientId found for this session : " + session);
        }
    }
    
}
