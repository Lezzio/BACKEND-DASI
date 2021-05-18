/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityServlet;

import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Astrolog;
import com.mycompany.td2.dasi.metier.modele.Cartomancian;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class GetListMediumsSerialisation extends Serialisation{
    private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<Medium> listMediums = (List<Medium>) request.getAttribute("listMediums");
        JsonArray jsonArray = new JsonArray();
        
        for (Medium medium : listMediums){
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("name", medium.getName());
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("presentation", medium.getPresentation());
            if(medium instanceof Astrolog){
                Astrolog astrolog = (Astrolog) medium;
                jsonMedium.addProperty("type", "astrolog");
                jsonMedium.addProperty("formation", astrolog.getFormation());
                jsonMedium.addProperty("promotion", astrolog.getPromotion());
            } else if(medium instanceof Cartomancian) {
                jsonMedium.addProperty("type", "cartomancian");
            } else if(medium instanceof Spirite){
                Spirite spirite = (Spirite) medium;
                jsonMedium.addProperty("type", "spirite");
                jsonMedium.addProperty("support", spirite.getSupport());
            }
            jsonArray.add(jsonMedium);
        }
        
        gson.toJson(jsonArray, out);
        out.close();
        
    }
}
