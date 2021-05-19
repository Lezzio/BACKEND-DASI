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
import static java.lang.Long.parseLong;
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
        Client client = (Client) request.getAttribute("Client");
        
        EntityService entityService = new EntityService();        
        
        AppointmentService appointmentService = new AppointmentService();
        List<Consultation> consultations = appointmentService.fetchClientHistory(client);
        List<String> mediumNames = new ArrayList<>();
        for(Consultation consultation : consultations){
            mediumNames.add(consultation.getMedium().getName());
        }
        request.setAttribute("Consultations", consultations);
        request.setAttribute("MediumsNames", mediumNames);
        
    }
}
