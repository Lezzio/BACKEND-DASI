/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.statsservlet;
import com.mycompany.backend.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maxim
 */
public class ClientDistributionByEmployeeSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<Employee, Integer> mapDistributionClientByEmployee = (Map<Employee, Integer>)request.getAttribute("mapDistributionClientByEmployee");
        JsonObject container = new JsonObject();
        JsonArray jsonNumberClients = new JsonArray();
        JsonArray jsonEmployeeNames = new JsonArray();
        System.out.println("On affiche le nombre de consultation par médium");
        if (mapDistributionClientByEmployee != null) {
            
            for (Map.Entry mapentry : mapDistributionClientByEmployee.entrySet()) {
                JsonObject jsonMedium = new JsonObject();
                jsonEmployeeNames.add(((Employee) mapentry.getKey()).getFirstName());
                jsonNumberClients.add((Number) mapentry.getValue());
            }            
        }
        
        container.add("labels", jsonEmployeeNames);
        container.add("data", jsonNumberClients);
        

        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}