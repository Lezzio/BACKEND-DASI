/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthentificationServlet;
import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Aurélien
 */
public class loginClientSerialisation2 extends Serialisation{
    private final Gson gson = new Gson();
    
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client client = (Client) request.getAttribute("client");
        JsonObject container = new JsonObject();
        if (client != null) {
            container.addProperty("connexion", "true");
            container.addProperty("ClientId", client.getId());   
        } else {
            container.addProperty("connexion", "false");
            container.addProperty("ClientId", ""); 
            System.out.println("Identification échouée");
        }
        
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
