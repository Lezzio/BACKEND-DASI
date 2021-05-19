/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;
import com.mycompany.backend.Serialisation;
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
public class LoginClientSerialisation2 extends Serialisation{
    private final Gson gson = new Gson();


    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client client = (Client) request.getAttribute("client");
        JsonObject container = new JsonObject();
        if (client != null) {
            container.addProperty("connexion", "true");
            container.addProperty("Client", gson.toJson(client));   
        } else {
            container.addProperty("connexion", "false");
            container.addProperty("Client", gson.toJson(null)); 
            System.out.println("Identification échouée");
        }

        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}