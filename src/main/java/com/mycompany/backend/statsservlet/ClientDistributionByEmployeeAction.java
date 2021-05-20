/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.statsservlet;

import com.mycompany.backend.Action;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.EntityService;
import com.mycompany.td2.dasi.metier.services.StatsService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author maxim
 */
public class ClientDistributionByEmployeeAction extends Action{
     
    @Override
    public void executer(HttpServletRequest request)
    {
  // Instanciation de la classe de Service
        StatsService statsService = new StatsService();
        EntityService entityService = new EntityService();
        // Appel des Services Métiers (= méthodes de la classe de Service)
        Map<Long, Integer> mapDistributionClientByEmployeeRetour = statsService.clientDistributionByEmployee();
        Map<Employee, Integer> mapDistributionClientByEmployee = new HashMap<>();
        
        for (Map.Entry mapentry : mapDistributionClientByEmployeeRetour.entrySet()) {
            Employee employee = entityService.searchEmployeeById((Long)mapentry.getKey());
            mapDistributionClientByEmployee.put(employee, (Integer)mapentry.getValue());
        }
        
        // Stockage des Résultats dans les Attributs de la Requête
        request.setAttribute("mapDistributionClientByEmployee", mapDistributionClientByEmployee);
        
    }
        
}
