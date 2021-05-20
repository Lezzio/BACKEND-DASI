/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.backend.Serialisation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PredictionsClientSerialisation extends Serialisation {

    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Lecture des attributs de la requête (stockés par action)
    String lovePred = (String) request.getAttribute("lovePred");
    String healthPred = (String) request.getAttribute("healthPred");
    String workPred = (String) request.getAttribute("workPred");

    // Ajout de propriétés au conteneur JSON
    JsonObject container = new JsonObject();
    if(lovePred != null) {   
        container.addProperty("love", lovePred);    
    }
    if(healthPred != null) {
        container.addProperty("health", healthPred);
    }
    if(workPred != null) {
        container.addProperty("work", workPred);
    }

    // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
    PrintWriter out = response.getWriter();
    gson.toJson(container, out);
    out.close();
    }

}