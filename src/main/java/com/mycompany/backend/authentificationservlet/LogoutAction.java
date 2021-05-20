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
 * @author aguigal
 */
public class LogoutAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        //Just invalidate the current session so we forget the saved ids
        HttpSession session = request.getSession();
        session.invalidate();
        
        request.setAttribute("done", true);
    }
    
    
    
}