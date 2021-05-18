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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author victo
 */

public class AskConsultationSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    JsonObject container = new JsonObject();
    
    // Lecture des attributs de la requête (stockés par action)
    String appointment = (String)request.getAttribute("Consultation");
    
    // Ajout de propriétés au conteneur JSON
    container.addProperty("Consultation", appointment);
    
    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    PrintWriter out = this.getWriter(response);
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    gson.toJson(container, out);
    out.close();
    }
}
