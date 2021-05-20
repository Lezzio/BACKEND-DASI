/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.statsservlet;

import com.mycompany.backend.Action;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.services.StatsService;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author maxim
 */

public class ListConsultationCountByMediumAction extends Action {
   
    private final Gson gson = new Gson();
    StatsService statsService = new StatsService();
    
    @Override
    public void executer(HttpServletRequest request)
    {
        // Instanciation de la classe de Service
        StatsService statsService = new StatsService();

        // Appel des Services Métiers (= méthodes de la classe de Service)
        Map<String, Integer> mapConsultationByMedium = statsService.numberConsultationByMedium();

        // Stockage des Résultats dans les Attributs de la Requête
        request.setAttribute("mapConsultationByMedium", mapConsultationByMedium);
        
    }
        
}
