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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TopFiveMediumSerialisation2 extends Serialisation{

    private final Gson gson = new Gson();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Integer> mapTopFiveMedium = (Map<String, Integer>)request.getAttribute("mapTopFiveMedium");
        JsonObject container = new JsonObject();

        System.out.println("On affiche le nombre de consultation par médium");
        if (mapTopFiveMedium != null) {

            JsonArray jsonListeMedium = new JsonArray () ;
            for (Map.Entry mapentry : mapTopFiveMedium.entrySet()) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("Nom", (String) mapentry.getKey());
                jsonMedium.addProperty("NombreClientUnique", (Number) mapentry.getValue());
                jsonListeMedium.add(jsonMedium);
            }

            String listeMedium = gson.toJson(jsonListeMedium);
            container.addProperty("statsTospFiveMediumNonVide", "true");
            container.addProperty("listeMedium", listeMedium);
        }
        else
        {
            container.addProperty("statsTospFiveMediumNonVide", "flase");
        }
        System.out.println("On est au bout");
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}