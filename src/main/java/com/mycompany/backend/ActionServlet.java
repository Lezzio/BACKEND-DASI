/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend;

import com.mycompany.backend.appointmentservlet.AskConsultationAction;
import com.mycompany.backend.appointmentservlet.AskConsultationSerialisation;
import com.mycompany.backend.appointmentservlet.EmployeeActiveConsultationAction;
import com.mycompany.backend.appointmentservlet.EmployeeActiveConsultationSerialisation;
import com.mycompany.backend.appointmentservlet.GetClientHistoryAction;
import com.mycompany.backend.appointmentservlet.GetClientHistorySerialisation;
import com.mycompany.backend.authentificationservlet.LoginClientAction;
import com.mycompany.backend.authentificationservlet.LoginClientSerialisation;
import com.mycompany.backend.authentificationservlet.LoginEmployeeAction;
import com.mycompany.backend.authentificationservlet.LoginEmployeeSerialisation;
import com.mycompany.backend.authentificationservlet.SignupClientAction;
import com.mycompany.backend.authentificationservlet.SignupClientSerialisation;
import com.mycompany.backend.entityservlet.GetClientAction;
import com.mycompany.backend.entityservlet.GetClientSerialisation;
import com.mycompany.backend.entityservlet.GetEmployeeAction;
import com.mycompany.backend.entityservlet.GetEmployeeSerialisation;
import com.mycompany.backend.entityservlet.GetListMediumsAction;
import com.mycompany.backend.entityservlet.GetListMediumsSerialisation;
import com.mycompany.backend.statsservlet.TopFiveMediumAction;
import com.mycompany.backend.statsservlet.TopFiveMediumSerialisation;
import com.google.gson.Gson;
import com.mycompany.backend.appointmentservlet.PredictionsClientAction;
import com.mycompany.backend.appointmentservlet.PredictionsClientSerialisation;
import com.mycompany.td2.dasi.dao.ConsultationDao;
import com.mycompany.td2.dasi.dao.JpaUtil;
import com.mycompany.td2.dasi.dao.MediumDao;
import com.mycompany.td2.dasi.metier.modele.Astrolog;
import com.mycompany.td2.dasi.metier.modele.Cartomancian;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.modele.Spirite;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.AuthentificationService;
import com.mycompany.td2.dasi.utils.Gender;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
    AppointmentService appointmentService = new AppointmentService();
    
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
        response.addHeader("Access-Control-Allow-Origin", "*");

        HttpSession session = request.getSession();
        session.getAttribute("clientId");
        session.setAttribute("clientId", 1L);
        session.setAttribute("employeeId", 2L);

        String todo = request.getParameter("todo");

        System.out.println("Todo = " + todo);

        Action action = null;
        Serialisation serialisation = null;
        
        switch (todo) {
            case "signIn":
                System.out.println("Call signIn servlet");
                String userType = request.getParameter("userType");
                switch(userType) {
                    case "client":
                        action = new LoginClientAction();
                        serialisation = new LoginClientSerialisation();
                        break;
                    case "employee":
                        action = new LoginEmployeeAction();
                        serialisation = new LoginEmployeeSerialisation();
                        break;
                    default:
                        System.out.println("Error, neither Employee or Client for this selection : " + userType);
                        break;                          
                }
                break;
            case "signUp":
                System.out.println("Call signUp servlet");
                action = new SignupClientAction();
                serialisation = new SignupClientSerialisation();
                break;
            case "getClient":
                System.out.println("Call getClient servlet");
                action = new GetClientAction();
                serialisation = new GetClientSerialisation();
                break;
            case "getEmployee":
                System.out.println("Call getEmployee servlet");
                action = new GetEmployeeAction();
                serialisation = new GetEmployeeSerialisation();
                break;
            case "askAppointment":
                System.out.println("Call AskConsultation servlet");
                action = new AskConsultationAction();
                serialisation = new AskConsultationSerialisation();
                break;
            case "getEmployeeActiveConsultation":
                System.out.println("Call EmployeeActiveConsultation servlet");
                action = new EmployeeActiveConsultationAction();
                serialisation = new EmployeeActiveConsultationSerialisation();
                break;
            case "getPredicitionForClient":
                System.out.println("Call PredictionsClient servlet");
                action = new PredictionsClientAction();
                serialisation = new PredictionsClientSerialisation();
                break;
            case "topFiveMediums":
                System.out.println("Call topFiveMediums servlet");
                action = new TopFiveMediumAction();
                serialisation = new TopFiveMediumSerialisation();
                break;
            case "fetchActiveConsultation":
                System.out.println("Call fetchActiveConsultation servlet");
                action = new EmployeeActiveConsultationAction();
                serialisation = new EmployeeActiveConsultationSerialisation();
                break;
            case "getClientHistory":
                System.out.println("Call fetchActiveConsultation servlet");
                action = new GetClientHistoryAction();
                serialisation = new GetClientHistorySerialisation();
                break;
            case "listMediums":
                System.out.println("Call listMediums servlet");
                action = new GetListMediumsAction();
                serialisation = new GetListMediumsSerialisation();
                break;
            default:
                System.out.println("Invalid Todo : " + todo);
                break;
        }
        
        if(action != null && serialisation != null) {
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
        Client client = new Client("Chloé", "Pascal", "Mme.", "chloe.pascal@orange.fr", "mypasswordcool", new Date(), "0475009835", "12 rue Poussin", "Davezieux", "07430");
        Spirite spirite = new Spirite("Votre avenir est devant vous : regardons-le ensemble !", "Test", Gender.MALE, "Le support ici trop bien trop bien wesh");
        Cartomancian carto = new Cartomancian("Votre avenir est devant vous : regardons-le ensemble !", "Test", Gender.OTHER);
        Astrolog astrolog = new Astrolog("Basée à Champigny-sur-Marne, Serena vous révèle votre avenir pour éclairer votre passé", "Serena", Gender.FEMALE, "Institut National des Astrologues SUP", "2010");
        Employee employee = new Employee(Gender.MALE, "James", "McDonald", "james.mcdonald@orange.fr", "mcdo", "0799435634");
        Date startDate = new Date();
        Date endDate = new Date();
        Consultation consultation1 = new Consultation(null, null, "Très bonne séance", client, spirite, employee);
        Consultation consultation2 = new Consultation(startDate, endDate, "Séance intéressante", client, spirite, employee);
        
        authentificationService.signupClient(client);
        authentificationService.signupEmployee(employee);
        ConsultationDao consultationDao = new ConsultationDao();
        MediumDao mediumDao = new MediumDao();
        
        try {
        JpaUtil.creerContextePersistance();
        JpaUtil.ouvrirTransaction();
        mediumDao.create(spirite);
        mediumDao.create(carto);
        mediumDao.create(astrolog);
        //consultationDao.create(consultation1);
        //consultationDao.create(consultation2);
        JpaUtil.validerTransaction();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
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
