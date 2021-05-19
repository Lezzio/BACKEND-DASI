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
public class ClientDistributionByEmployeeSerialisation2 extends Serialisation {

    private final Gson gson = new Gson();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<Employee, Integer> mapDistributionClientByEmployee = (Map<Employee, Integer>)request.getAttribute("mapDistributionClientByEmployee");
        JsonObject container = new JsonObject();

        System.out.println("On affiche le nombre de consultation par médium");
        if (mapDistributionClientByEmployee != null) {
            container.addProperty("statsTopFiveMediumNonVide", "true");

            JsonArray jsonListeMedium = new JsonArray () ;
            /*TODO : error line 43
            for (Map.Entry mapentry : mapDistributionClientByEmployee.entrySet()) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("First", (String) mapentry.getKey().getFirstName());
                jsonMedium.addProperty("NombreClientUnique", (Number) mapentry.getValue());
                jsonListeMedium.add(jsonMedium);
            }*/

            String listeMedium = gson.toJson(jsonListeMedium);
            container.addProperty("listeMedium", listeMedium);
        }
        else
        {
            container.addProperty("statsTopFiveMediumNonVide", "false");
        }
        System.out.println("On est au bout");

        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }



}