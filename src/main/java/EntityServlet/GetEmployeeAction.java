/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityServlet;

import Abstract.Action;
import com.mycompany.td2.dasi.metier.modele.Employee;
import com.mycompany.td2.dasi.metier.services.EntityService;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aurélien
 */
public class GetEmployeeAction extends Action{
    EntityService entityService = new EntityService();
    @Override
    public void executer(HttpServletRequest request) {
        
        String employeeIdStr = request.getParameter("id");
        try{
            Long employeeId = Long.parseLong(employeeIdStr);
            Employee employee = entityService.searchEmployeeById(employeeId);
            System.out.println("Employee = " + employee);
            request.setAttribute("employee", employee);
        } catch(NumberFormatException e){
            System.out.println("Error, bad ID");
            request.setAttribute("employee", null);
        }        
    }
}
