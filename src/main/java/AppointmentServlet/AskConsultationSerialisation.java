/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentServlet;
import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Julien
 */

public class AskConsultationSerialisation extends Serialisation {
    
    private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Lecture des attributs de la requête (stockés par action)
    Consultation appointment = (Consultation)request.getAttribute("Consultation");
    
    JsonObject container = new JsonObject();
    if(appointment != null)
    {    
        // Ajout de propriétés au conteneur JSON
        container.addProperty("Consultation", String.valueOf(appointment.getId()));
        container.addProperty("StartDate", String.valueOf(appointment.getStartDate()));
        container.addProperty("EndDate", String.valueOf(appointment.getEndDate()));
        container.addProperty("Commentary", appointment.getCommentary());
        container.addProperty("Medium", appointment.getMedium().getName());
        container.addProperty("ClientFirst", appointment.getClient().getFirstName());   
        container.addProperty("ClientLast", appointment.getClient().getLastName());
        container.addProperty("EmployeFirst", appointment.getEmployee().getFirstName());
        container.addProperty("EmployeLast", appointment.getEmployee().getLastName());
    
    }
    
    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    PrintWriter out = response.getWriter();
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    gson.toJson(container, out);
    out.close();
    }
}
