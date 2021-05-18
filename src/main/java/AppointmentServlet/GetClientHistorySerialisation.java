/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentServlet;

import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class GetClientHistorySerialisation extends Serialisation{
    private final Gson gson = new Gson();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        
        JsonArray history = new JsonArray();
        // Lecture des attributs de la requête (stockés par action)
        List<Consultation> consultations = (List<Consultation>) request.getAttribute("Consultations");
        List<String> mediumNames = (List<String>) request.getAttribute("MediumsNames");
        
        int i = 0;
        for(Consultation consultation : consultations){
            JsonObject element = new JsonObject();
            element.addProperty("endDate", consultation.getEndDate().toString());
            element.addProperty("mediumName", mediumNames.get(i));
            element.addProperty("commentary", consultation.getCommentary());
            i++;
            history.add(element);
        }

        // Ajout de propriétés au conteneur JSON
        container.addProperty("history", gson.toJson(history));
        
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
