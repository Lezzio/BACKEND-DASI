/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victo
 */
public class EmployeeActiveConsultationAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long employeeId = (Long) session.getAttribute("employeeId");
        
        EntityService entityService = new EntityService();        
        
        AppointmentService appointmentService = new AppointmentService();
        
        Employee employee = entityService.searchEmployeeById(employeeId);
        request.setAttribute("consultation", appointmentService.getEmployeeActiveConsultation(employee));
    }
}