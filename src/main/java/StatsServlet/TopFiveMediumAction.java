/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatsServlet;

import Abstract.Action;
import com.google.gson.Gson;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.StatsService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author maxim
 */


public class TopFiveMediumAction extends Action{
    
    private final Gson gson = new Gson();
    
    @Override
    public void executer(HttpServletRequest request)
    {
  // Instanciation de la classe de Service
        StatsService statsService = new StatsService();

        // Appel des Services Métiers (= méthodes de la classe de Service)
        Map<String, Integer> mapTopFiveMedium = statsService.topFiveMedium();

        // Stockage des Résultats dans les Attributs de la Requête
        request.setAttribute("mapTopFiveMedium", mapTopFiveMedium);
        
    }
        
}