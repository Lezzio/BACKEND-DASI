/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend.entityservlet;

import com.mycompany.backend.Action;
import com.mycompany.td2.dasi.metier.modele.Medium;
import com.mycompany.td2.dasi.metier.services.EntityService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aurélien
 */
public class GetListMediumsAction extends Action{
    EntityService entityService = new EntityService();
    @Override
    public void executer(HttpServletRequest request) { 
        List<Medium> listMediums = entityService.listMediums();
        request.setAttribute("listMediums", listMediums);
        
    }
}
