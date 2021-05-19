/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.appointmentservlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.backend.Serialisation;
import com.mycompany.td2.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aguigal
 */
public class StartConsultationSerialisation extends Serialisation {

    private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    
    Consultation consultation = (Consultation) request.getAttribute("consultation");
    String status = consultation.isLive() ? "ok" : "error"; //Check if the consultation state changed to the right one
    JsonObject container = new JsonObject();
    container.addProperty("status", status);
    gson.toJson(container, out);
    out.close();
    }

}