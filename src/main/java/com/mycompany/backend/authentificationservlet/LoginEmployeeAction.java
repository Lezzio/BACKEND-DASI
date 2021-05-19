/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;
import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.AuthentificationService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Aurélien
 */
public class LoginEmployeeAction extends Action{
    AuthentificationService authentificationService = new AuthentificationService();
    @Override
    public void executer(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Employee employee = authentificationService.authentificateEmployee(login, password);
        System.out.println("Employee = " + employee);
        request.setAttribute("employee", employee);
        
        //Update the session employee id
        HttpSession session = request.getSession();
        session.setAttribute("employeeId", employee.getId());
    }
    
}
