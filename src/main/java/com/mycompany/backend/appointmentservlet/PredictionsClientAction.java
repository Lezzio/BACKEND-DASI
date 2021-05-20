/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.AstralProfile;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import com.mycompany.td2.dasi.utils.AstroNetApi;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PredictionsClientAction extends Action {
    
   @Override
    public void executer(HttpServletRequest request){
        String love = request.getParameter("loveScore");
        String health = request.getParameter("healthScore");
        String work = request.getParameter("workScore");
        
        //Scores asked by the employee
        Integer healthScore = Integer.parseInt(health);
        Integer workScore = Integer.parseInt(work);
        Integer loveScore = Integer.parseInt(love);
        
        //Fields related to the astral profile of the client in live consultation
        HttpSession session = request.getSession();
        Long employeeId = (Long) session.getAttribute("employeeId");
        
        AppointmentService appointmentService = new AppointmentService();
        EntityService entityService = new EntityService();
        
        Employee employee = entityService.searchEmployeeById(employeeId);
        Consultation consultation = appointmentService.getEmployeeActiveConsultation(employee);
        
        Client client = consultation.getClient();
        AstralProfile astralProfile = client.getAstralProfile();
        
        String color = astralProfile.getColor();
        String animal = astralProfile.getTotemAnimal();
        
        //Call the astronet api to generate the predictions
        AstroNetApi astroNet = new AstroNetApi();
       try {
           List<String> predictions = astroNet.getPredictions(color, animal, loveScore, healthScore, workScore);
           //Set predictions in the request attributes
           request.setAttribute("lovePred", predictions.get(0) );
           request.setAttribute("healthPred", predictions.get(1));
           request.setAttribute("workPred", predictions.get(2));
       } catch (IOException ex) {
           Logger.getLogger(PredictionsClientAction.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Error while calling the prediction action ");
       }
    }

}