/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aurélien
 */
public class GetClientHistoryAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        Long clientId = Long.parseLong(request.getParameter("clientId"));
        
        EntityService entityService = new EntityService();        
        
        AppointmentService appointmentService = new AppointmentService();
        Client client = entityService.searchClientById(clientId);
        List<Consultation> consultations = appointmentService.fetchClientHistory(client);
        List<String> mediumNames = new ArrayList<>();
        for(Consultation consultation : consultations) {
            mediumNames.add(consultation.getMedium().getName());
        }
        request.setAttribute("Consultations", consultations);
        request.setAttribute("MediumsNames", mediumNames);
        
    }
}
