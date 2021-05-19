/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author victo
 */
public class EmployeeActiveConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request){
        String employee = request.getParameter("Employee");
        long idEmp = parseLong(employee);
        
        EntityService entityService = new EntityService();        
        
        AppointmentService appointmentService = new AppointmentService();
        
        request.setAttribute("Consultation", appointmentService.getEmployeeActiveConsultation(entityService.searchEmployeeById(idEmp)));
    }
}
