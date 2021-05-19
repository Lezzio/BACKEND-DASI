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
import com.mycompany.td2.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aguigal
 */
public class HasActiveConsultationSerialisation extends Serialisation {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // Lecture des attributs de la requête (stockés par action)
        Consultation appointment = (Consultation) request.getAttribute("consultation");

        JsonObject container = new JsonObject();
        
        Boolean activeConsultation = appointment != null;
        
        // Ajout de propriétés au conteneur JSON
        container.addProperty("hasActiveConsultation", activeConsultation);    

        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        gson.toJson(container, out);
        out.close();
    }
}