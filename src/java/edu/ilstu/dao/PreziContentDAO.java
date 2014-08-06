/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.PreziContentModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface PreziContentDAO {
    
    public int createPreziRessource(PreziContentModel ressource);
    
    public void updatePreziRessource(PreziContentModel ressource);
    
    public void deletePreziRessource(PreziContentModel ressource);
    
    public PreziContentModel getPreziRessourcesByRessourceId(int ressourceId);
    
    public ArrayList<PreziContentModel> getPreziRessourcebyOnlineClassId(int onlineClassId);
    
  }
