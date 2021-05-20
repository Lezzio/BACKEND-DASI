/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.backend.Serialisation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class GetClientOwnHistorySerialisation extends Serialisation{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        
        JsonArray history = new JsonArray();
        // Lecture des attributs de la requête (stockés par action)
     
        List<String> mediumNames = (List<String>) request.getAttribute("MediumsNames");
        List<String> mediumTypes = (List<String>) request.getAttribute("MediumsTypes");
        List<String> dates = (List<String>) request.getAttribute("Dates");
        List<Long> durations = (List<Long>) request.getAttribute("Minutes");
        
        for(int i = 0; i < mediumNames.size(); i++){
            JsonObject element = new JsonObject();
            
            element.addProperty("mediumType", mediumTypes.get(i));
            element.addProperty("day", dates.get(i));
            element.addProperty("mediumName", mediumNames.get(i));
            element.addProperty("minutes", durations.get(i));
           
            history.add(element);
        }

        // Ajout de propriétés au conteneur JSON
        container.add("history", history);
        
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = this.getWriter(response);
       
        gson.toJson(container, out);
        out.close();
    }
}
