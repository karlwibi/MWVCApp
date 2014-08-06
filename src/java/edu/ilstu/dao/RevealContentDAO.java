/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;


import edu.ilstu.model.RevealContentModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RevealContentDAO {
    
    public int createRevealRessource(RevealContentModel ressource);
    
    public void updateRevealRessource(RevealContentModel ressource);
    
    public void deleteRevealRessource(RevealContentModel ressource);
    
    public RevealContentModel getRessourceById(int ressourceId);
    
    public ArrayList<RevealContentModel> getRevealRessourceByOnlinceClasseId(int onlinceClassId);
    
}
