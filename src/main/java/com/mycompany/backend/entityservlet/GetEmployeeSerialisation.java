/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.entityservlet;

import com.mycompany.backend.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.td2.dasi.metier.modele.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class GetEmployeeSerialisation extends Serialisation {

    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Employee employee = (Employee) request.getAttribute("employee");
        gson.toJson(employee, out);
        out.close();
    }
    
}