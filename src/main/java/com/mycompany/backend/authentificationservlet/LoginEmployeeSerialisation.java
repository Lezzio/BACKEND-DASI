/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.authentificationservlet;
import com.mycompany.backend.Serialisation;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.metier.modele.Client;
import com.mycompany.td2.dasi.metier.modele.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Aurélien
 */
public class LoginEmployeeSerialisation extends Serialisation{
     private final Gson gson = new Gson();
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Employee employee = (Employee) request.getAttribute("employee");
        if (employee != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String employeeJson = gson.toJson(employee);
            out.print("{");
            out.println("\"connexion\": true,");
            out.print("\"employee\": ");
            out.print(employeeJson);
            out.println("}");
            out.flush();
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            out.print("{");
            out.println("\"connexion\": false,");
            out.print("\"employee\": \"null\"");
            out.println("}");
            out.flush();
        }
    }
    }
    

