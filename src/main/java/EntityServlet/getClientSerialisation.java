/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityServlet;

import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.td2.dasi.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class getClientSerialisation extends Serialisation{
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Client client = (Client) request.getAttribute("client");
        if (client != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String clientJson = gson.toJson(client);
            out.print("{");
            out.print("\"client\": ");
            out.print(clientJson);
            out.println("}");
            out.flush();
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{");
            out.print("\"client\": \"null\"");
            out.println("}");
            out.flush();
        }
    }
}
