/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppointmentServlet;

import Abstract.Action;
import com.mycompany.td2.dasi.metier.services.EntityService;
import com.mycompany.td2.dasi.utils.AstroNetApi;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 
 */
public class PredictionsClientAction extends Action{
    
   @Override
    public void executer(HttpServletRequest request){
        String love = request.getParameter("loveScore");
        String health = request.getParameter("healthScore");
        String work = request.getParameter("workScore");
        String color = request.getParameter("colorHappiness");
        String animal = request.getParameter("animalHappiness");
        int loveScore = parseInt(love);
        int healthScore = parseInt(health);
        int workScore = parseInt(work);
        
        
        // Appeler la méthode de génération des prédicitions
        
        
        EntityService entityService = new EntityService();       
        AstroNetApi astroNet = new AstroNetApi();
        
        List<String> predictions = new ArrayList<String>();
       try {
           predictions = astroNet.getPredictions(color, animal, loveScore, healthScore, workScore);
           // Renvoi des prédictions dans la request
            request.setAttribute("lovePred", predictions.get(0) );
            request.setAttribute("healthPred", predictions.get(1));
            request.setAttribute("workPred", predictions.get(2));
       } catch (IOException ex) {
           Logger.getLogger(PredictionsClientAction.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Erreur lors de l'appel de la méthode de prédictions ");
       }
    }
    
}
