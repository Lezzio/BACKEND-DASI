/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthentificationServlet;
import Abstract.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.AuthentificationService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aurélien
 */
public class signupClientAction extends Action{
    AuthentificationService authentificationService = new AuthentificationService();
    @Override
    public void executer(HttpServletRequest request) {        
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String civility = request.getParameter("civility");
        String mail = request.getParameter("login");
        String password = request.getParameter("password");
        //Date birthDate = new SimpleDateFormat("yy-MM-dd").parse(request.getParameter("birthdate"));
        String phone = request.getParameter("phone");
        String dateString = request.getParameter("birthdate");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        try {
            Date birthDate = format.parse(dateString);
            Client client = new Client(lastName, firstName, civility, mail, password, birthDate, phone);
            System.out.println("Client : " + client);
            Long id = authentificationService.signupClient(client);
            if(id != null){
                request.setAttribute("signup", true);
                request.setAttribute("id", id);
                request.setAttribute("client", client);
            }else{
                request.setAttribute("signup", false);
                request.setAttribute("id", -1);
            }
            
        } catch (ParseException ex) {
            System.out.println("Impossible to parse this date : "+ dateString);
            request.setAttribute("signup", false);
        }      
    }
    
}
