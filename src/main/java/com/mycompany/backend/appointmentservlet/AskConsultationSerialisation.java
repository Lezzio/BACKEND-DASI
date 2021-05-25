/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.backend.Serialisation;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AskConsultationSerialisation extends Serialisation {
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    JsonObject container = new JsonObject();
    
    // Lecture des attributs de la requête (stockés par action)
    Long appointmentId = (Long) request.getAttribute("consultation");
    Boolean alreadyBooked = (Boolean) request.getAttribute("alreadyBooked");
    
    String status;
    
    if(alreadyBooked) {
        status = "alreadyBooked";
    } else if(appointmentId == null) {
        status = "notAvailable";
    } else {
        status = "available";
    }
    
    // Ajout de propriétés au conteneur JSON
    container.addProperty("status", status);
    
    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    try {
        PrintWriter out = this.getWriter(response);
        gson.toJson(container, out);
        out.close();
     } catch (Exception e) {
        e.printStackTrace();
     }
    
    }
    
}