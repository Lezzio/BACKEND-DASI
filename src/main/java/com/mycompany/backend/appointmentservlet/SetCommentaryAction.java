/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author aguigal
 */
public class SetCommentaryAction extends Action {
    
    private final AppointmentService appointmentService = new AppointmentService();
    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Consultation consultation = (Consultation) session.getAttribute("endedConsultation");
        if(consultation != null) {
            String commentary = request.getParameter("commentary");
            appointmentService.setCommentary(consultation, commentary);
            request.setAttribute("done", true);
            //Remove the endedConsultation attribute because the commentary is now set
            System.out.println("Commentary sent = " + commentary);
            session.setAttribute("endedConsultation", null);
        } else {
            request.setAttribute("done", false);
        }
        
    }
    
}