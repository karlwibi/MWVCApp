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
public interface PreziContentDAO {
    
    public int createPreziRessource(RessourceModel ressource);
    
    public void updatePreziRessource(RessourceModel ressource);
    
    public void deletePreziRessource(RessourceModel ressource);
    
    public RessourceModel getPreziRessourcesByRessourceId(int ressourceId);
    
    public ArrayList<RessourceModel> getPreziRessourcebyOnlineClassId(int onlineClassId);
    
  }
