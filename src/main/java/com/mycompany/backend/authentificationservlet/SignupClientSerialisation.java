/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;
import com.mycompany.backend.Serialisation;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Aurélien
 */
public class SignupClientSerialisation extends Serialisation {
    Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Client client = (Client) request.getAttribute("client");
        if (client != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String clientJson = gson.toJson(client);
            out.print("{");
            out.println("\"signup\": true,");
            out.print("\"client\": ");
            out.print(clientJson);
            out.println("}");
            out.flush();
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            out.print("{");
            out.println("\"signup\": false,");
            if((int) request.getAttribute("id") == -1){
                out.println("\"exists\": true,");
            }else{
                out.println("\"internError\": true,");
            }
            out.print("\"client\": \"null\"");
            out.println("}");
            out.flush();
        }
    }
    
}
