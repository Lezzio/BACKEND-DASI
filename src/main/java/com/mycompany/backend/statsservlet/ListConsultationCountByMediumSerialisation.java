/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.statsservlet;
import com.mycompany.backend.Serialisation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author maxim
 */
public class ListConsultationCountByMediumSerialisation extends Serialisation{

    private final Gson gson = new Gson();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject container = new JsonObject(); // Objet "conteneur JSON" pour structurer les données à sérialiser

        Map<String, Integer> mapConsultationByMedium = (Map<String, Integer>)request.getAttribute("mapConsultationByMedium");
        System.out.println("On affiche le nombre de consultation par médium");
        
        JsonArray jsonNumberConsultations = new JsonArray () ; // Création du tableau de médiums
        JsonArray jsonNames = new JsonArray();
        if (mapConsultationByMedium != null) {
            for (Map.Entry mapentry : mapConsultationByMedium.entrySet()) {
                jsonNumberConsultations.add((Number) mapentry.getValue());
                jsonNames.add((String) mapentry.getKey());
            }
        }
        container.add("labels", jsonNames);
        container.add("data", jsonNumberConsultations);
        
       // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}