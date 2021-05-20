/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.backend.Serialisation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aguigal
 */
public class LogoutSerialisation extends Serialisation {

    private final Gson gson = new Gson();
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Boolean done = (Boolean) request.getAttribute("done");
        JsonObject container = new JsonObject();
        container.addProperty("logout", done);
        gson.toJson(container, out);
        out.close();
    }
    
}