/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatsServlet;

import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Medium;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TopFiveSerialisation extends Serialisation{
    
    private final Gson gson = new Gson();
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        List<Medium> listMedium = (List<Medium>)request.getAttribute("mapConsultationByMedium");
        PrintWriter out = response.getWriter();
        System.out.println("On affiche le nombre de consultation par médium");
        if (mapConsultationByMedium != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            JsonArray jsonListeMediumConsultation = new JsonArray () ;
            for (Map.Entry mapentry : mapConsultationByMedium.entrySet()) {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("NomMedium", (String) mapentry.getKey());
                jsonMedium.addProperty("NombreConsultation", (Number) mapentry.getValue());
                jsonListeMediumConsultation.add(jsonMedium);
            }
            String listeMediumConsultation = gson.toJson(jsonListeMediumConsultation);
            out.print("{");
            out.println("\"statsNonVide\": true,");
            out.print("\"listeMediumConsultation\": ");
            out.print(listeMediumConsultation);
            out.println("}");
            out.flush();
        }
        else
        {
            out.print("{");
            out.println("\"statsNonVide\": false,");
            out.println("}");
            out.flush();
        }
        System.out.println("On est au bout");*/
    }
}
