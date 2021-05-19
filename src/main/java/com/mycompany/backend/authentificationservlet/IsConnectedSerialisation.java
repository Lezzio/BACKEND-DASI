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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aurélien
 */
public class IsConnectedSerialisation extends Serialisation{
    private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        boolean isConnected = (boolean) request.getAttribute("isConnected");
        String userType = (String) request.getAttribute("userType");
        container.addProperty("isConnected", isConnected);
        container.addProperty("userType", userType);
        PrintWriter out = response.getWriter();
        gson.toJson(container, out);
    }
    
}
