/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityServlet;

import Abstract.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.td2.dasi.metier.modele.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aurélien
 */
public class GetEmployeeSerialisation2 extends Serialisation {

    private final Gson gson = new Gson();

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        Employee employee = (Employee) request.getAttribute("employee");
        
        if (employee != null) {
            container.addProperty("EmployeFirst", employee.getFirstName());
            container.addProperty("EmployeLast", employee.getLastName());
        } else {
            container.addProperty("employee", "");
        }
        
        // Formatage de la structure de données JSON => Ecriture dur le flux de sortie de la réponse
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }
}
