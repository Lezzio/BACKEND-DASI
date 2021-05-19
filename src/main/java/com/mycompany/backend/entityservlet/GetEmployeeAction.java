/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.entityservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aurélien
 */
public class GetEmployeeAction extends Action{
    EntityService entityService = new EntityService();
    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        Long employeeId = (Long) session.getAttribute("employeeId");
        
        Employee employee = entityService.searchEmployeeById(employeeId);
        System.out.println("Employee = " + employee);
        request.setAttribute("employee", employee);
        
    }
}