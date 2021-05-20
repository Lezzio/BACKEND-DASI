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


public class TopFiveMediumSerialisation extends Serialisation{

    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Integer> mapTopFiveMedium = (Map<String, Integer>)request.getAttribute("mapTopFiveMedium");
        JsonObject container = new JsonObject();

        System.out.println("On affiche le nombre de consultation par médium");
        if (!mapTopFiveMedium.isEmpty()) {
            System.out.println("MAPTOP FIVE ========================");
            JsonArray jsonListeMedium = new JsonArray () ;
            for (Map.Entry mapentry : mapTopFiveMedium.entrySet()) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("name", (String) mapentry.getKey());
                jsonMedium.addProperty("numberUniqueClients", (Number) mapentry.getValue());
                jsonListeMedium.add(jsonMedium);
                System.out.println("Name : " + mapentry.getKey());
                System.out.println("Value : " + mapentry.getValue());
                System.out.println("---------");
            }

            //String listeMedium = gson.toJson(jsonListeMedium);
            container.addProperty("statsTopFiveMediumNonVide", true);
            container.add("listeMedium", jsonListeMedium);
        }
        else
        {
            container.addProperty("statsTopFiveMediumNonVide", false);
        }
        System.out.println("On est au bout");
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        gson.toJson(container, out);
        out.close();
    }
}