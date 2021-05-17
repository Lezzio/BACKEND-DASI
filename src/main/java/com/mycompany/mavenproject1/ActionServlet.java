/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import Abstract.Action;
import Abstract.Serialisation;
import AppointmentServlet.AskConsultationAction;
import AppointmentServlet.AskConsultationSerialisation;
import AuthentificationServlet.loginClientAction;
import AuthentificationServlet.loginClientSerialisation;
import AuthentificationServlet.loginEmployeeAction;
import AuthentificationServlet.loginEmployeeSerialisation;
import AuthentificationServlet.signupClientAction;
import AuthentificationServlet.signupClientSerialisation;
import EntityServlet.getClientAction;
import EntityServlet.getClientSerialisation;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.dao.JpaUtil;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.AuthentificationService;
import com.mycompany.td2.dasi.utils.Gender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aguigal
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    private final Gson gson = new Gson();
    AuthentificationService authentificationService = new AuthentificationService();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);

        String todo = request.getParameter("todo");

        System.out.println("Todo = " + todo);

        Action action = null;
        Serialisation serialisation = null;
        
        switch (todo) {
            case "signIn":
                System.out.println("Call signIn servlet");
                String userType = request.getParameter("userType");
                switch(userType){
                    case "Client":
                        action = new loginClientAction();
                        serialisation = new loginClientSerialisation();
                        break;
                    case "Employee":
                        action = new loginEmployeeAction();
                        serialisation = new loginEmployeeSerialisation();
                        break;
                    default:
                        System.out.println("Error, neither Employee or Client for this selection : " + userType);
                        break;                          
                }
                break;
            case "signUp":
                System.out.println("Call signUp servlet");
                action = new signupClientAction();
                serialisation = new signupClientSerialisation();
                break;
            case "getClient":
                System.out.println("Call getClient servlet");
                action = new getClientAction();
                serialisation = new getClientSerialisation();
                break;
            case "askAppointment":
                System.out.println("Call AskConsultation servlet");
                action = new AskConsultationAction();
                serialisation = new AskConsultationSerialisation();
                break;
            default:
                System.out.println("Invalid Todo : " + todo);
                break;
        }
        
        if(action != null && serialisation != null){
            action.executer(request);
            serialisation.serialiser(request, response);
        }else{
            System.out.println("Error");
            System.out.println("Action : " + action);
            System.out.println("Serialisation : " + serialisation);
        }

    }


    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
        Client client1 = new Client("Lovelace", "Ada", "Mme", "ada.lovelace@insa-lyon.fr", "ada1", new Date(), "0668574620", "12 rue Poussin", "Davezieux" ,"07430");
        authentificationService.signupClient(client1);
        Employee employee1 = new Employee(Gender.MALE,"leo", "dupont", "leo@leo.fr","mdp","0505050505");
        authentificationService.signupEmployee(employee1);
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
