/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;
import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AskConsultationAction extends Action {
    
    private final AppointmentService appointmentService = new AppointmentService();
    private final EntityService entityService = new EntityService();       
        
    @Override
    public void executer(HttpServletRequest request) {
        String mediumParam = request.getParameter("medium");
        Long mediumId = Long.parseLong(mediumParam);
        
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("clientId");
        
        Client client = entityService.searchClientById(clientId);
        Medium medium = entityService.searchMediumById(mediumId);
        
        System.out.println("Client = " + client + " id = " + clientId.toString());
        System.out.println("Medium = " + medium + " id = " + mediumId.toString());
        
        Consultation activeConsultation = appointmentService.getClientActiveConsultation(client);
        
        if(activeConsultation != null) {
            //Checking if the medium and client are available is done in the service
            Long idConsultation = appointmentService.askConsultation(client, medium);
            request.setAttribute("consultation", idConsultation);
            request.setAttribute("alreadyBooked", false);
        } else {
            request.setAttribute("alreadyBooked", true);
        }
        
    }
    
}