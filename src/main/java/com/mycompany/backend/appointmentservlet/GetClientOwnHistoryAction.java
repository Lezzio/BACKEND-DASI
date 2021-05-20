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
import com.mycompany.td2.dasi.utils.AstroNetApi;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aurélien
 */
public class GetClientOwnHistoryAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute("clientId");
        //clientId = 119L;
        System.out.println("CLIENT ID : " + clientId);
        System.out.println("================");
        
        EntityService entityService = new EntityService();        
        
        AppointmentService appointmentService = new AppointmentService();
        Client client = entityService.searchClientById(clientId);
        System.out.println("CLIENT : " + client);
        System.out.println("================");
        List<Consultation> consultations = appointmentService.fetchClientHistory(client);
        
        List<String> mediumNames = new ArrayList<>();
        List<String> mediumTypes = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<Long> durations = new ArrayList<>();
        
        
        for(Consultation consultation : consultations) {
            mediumNames.add(consultation.getMedium().getName());
            System.out.println("mediumName : " + consultation.getMedium().getName());
            mediumTypes.add(consultation.getMedium().getClass().getSimpleName().toLowerCase());
            System.out.println("mediumType : " + consultation.getMedium().getClass().getSimpleName().toLowerCase());
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            dates.add(dt.format(consultation.getStartDate()));
            System.out.println("start date : " + dt.format(consultation.getStartDate()));
            
            long diffInMillies = consultation.getEndDate().getTime() - consultation.getStartDate().getTime();
            durations.add(TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS));
            System.out.println("duration : " + TimeUnit.MINUTES.convert(diffInMillies,TimeUnit.MILLISECONDS));
        }
        
        request.setAttribute("MediumsNames", mediumNames);
        request.setAttribute("MediumsTypes", mediumTypes);
        request.setAttribute("Dates", dates);
        request.setAttribute("Minutes", durations);
                
    }
}
