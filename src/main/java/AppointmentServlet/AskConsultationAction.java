/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentServlet;
import Abstract.Action;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author victo
 */
public class AskConsultationAction extends Action {
    
    private final AppointmentService appointmentService = new AppointmentService();
        
    @Override
    public void executer(HttpServletRequest request){
        String mediumParam = request.getParameter("medium");
        String clientParam = request.getParameter("client");
       
        Long idMedium = Long.parseLong(mediumParam);
        Long idClient = Long.parseLong(clientParam);
        
        EntityService entityService = new EntityService();        
        
        // La vérification de la disponibilité de l'employé se fait dans le service
        Client client = entityService.searchClientById(idClient);
        Medium medium = entityService.searchMediumById(idMedium);
        
        System.out.println("Medium = " + medium + " id = " + idMedium);
        System.out.println("Client = " + client + " id = " + idClient);
        
        Long idConsultation = appointmentService.askConsultation(client, medium);
        
        request.setAttribute("consultation", idConsultation);
    }
    
}