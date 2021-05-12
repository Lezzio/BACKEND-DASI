/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentServlet;
import Abstract.Action;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.AppointmentService;
import com.mycompany.td2.dasi.metier.services.EntityService;
import static java.lang.Long.parseLong;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author victo
 */
public class AskConsultationAction extends Action{
    
    @Override
    public void executer(HttpServletRequest request){
        String mediumAIncarner = request.getParameter("Medium");
        String client = request.getParameter("Client");
        
        long idMedium = parseLong(mediumAIncarner);
        long idClient = parseLong(client);
        
        EntityService entityService = new EntityService();        
        
        // La vérification de la disponibilité de l'employé se fait dans le service
        AppointmentService appointmentService = new AppointmentService();
        long idConsultation = appointmentService.askConsultation(entityService.searchClientById(idClient), entityService.searchMediumById(idMedium));
        
        request.setAttribute("Consultation", idConsultation);
    }
    
}
