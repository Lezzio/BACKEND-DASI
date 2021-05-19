/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.statsservlet;

import com.mycompany.backend.Action;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.metier.services.StatsService;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author maxim
 */
public class TopFiveMediumAction extends Action {

    private final Gson gson = new Gson();

    @Override
    public void executer(HttpServletRequest request) {
        // Instanciation de la classe de Service
        StatsService statsService = new StatsService();

        // Appel des Services Métiers (= méthodes de la classe de Service)
        HashMap<String, Integer> mapTopFiveMedium = (HashMap<String, Integer>) statsService.topFiveMedium();
        mapTopFiveMedium = sortWithValues(mapTopFiveMedium); 
        // Stockage des Résultats dans les Attributs de la Requête
        request.setAttribute("mapTopFiveMedium", mapTopFiveMedium);

    }

    public static HashMap<String, Integer> sortWithValues(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> result = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry : list){
            result.put(entry.getKey(), entry.getValue());
        }
            
        return result;
    }

    
}
