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
public class EmployeeActiveConsultationSerialisation extends Serialisation{
    
    private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    // Lecture des attributs de la requête (stockés par action)
    Consultation appointment = (Consultation)request.getAttribute("Consultation");
    
    JsonObject container = new JsonObject();
    if(appointment != null)
    {
        // Ajout de propriétés au conteneur JSON
        container.addProperty("ConsultationActive", String.valueOf(appointment.getId()));    
    }
    
    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    gson.toJson(container, out);
    out.close();
        
    }
    
    
}
