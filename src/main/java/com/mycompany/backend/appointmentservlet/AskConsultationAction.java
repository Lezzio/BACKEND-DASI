/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;
import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author victo
 */
public class AskConsultationAction extends Action {
    
    private final AppointmentService appointmentService = new AppointmentService();
        
    @Override
    public void executer(HttpServletRequest request){
        String mediumParam = request.getParameter("medium");
        Long mediumId = Long.parseLong(mediumParam);
        
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("clientId");
       
        
        EntityService entityService = new EntityService();        
        
        // La vérification de la disponibilité de l'employé se fait dans le service
        Client client = entityService.searchClientById(clientId);
        Medium medium = entityService.searchMediumById(mediumId);
        
        System.out.println("Client = " + client + " id = " + clientId);
        System.out.println("Medium = " + medium + " id = " + mediumId);
        
        Long idConsultation = appointmentService.askConsultation(client, medium);
        
        request.setAttribute("consultation", idConsultation);
    }
    
}