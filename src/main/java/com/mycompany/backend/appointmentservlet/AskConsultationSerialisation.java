/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;
import com.mycompany.backend.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
    Long appointmentId = (Long) request.getAttribute("consultation");
    
    // Ajout de propriétés au conteneur JSON
    String status = appointmentId != null ? "available" : "unavailable";
    
    container.addProperty("status", status);
    
    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    try {
    PrintWriter out = this.getWriter(response);
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    gson.toJson(container, out);
    out.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
