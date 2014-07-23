/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.RessourceModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RevealContentDAO {
    
    public int createRevealRessource(RessourceModel ressource);
    
    public void updateRevealRessource(RessourceModel ressource);
    
    public void deleteRevealRessource(RessourceModel ressource);
    
    public RessourceModel getRessourceById(int ressourceId);
    
    public ArrayList<RessourceModel> getRessourceByOnlinceClasseId(int onlinceClassId);
    
}
