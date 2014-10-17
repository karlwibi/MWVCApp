/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.SessionResourceModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface SessionResourceDAO {
    
    public void createSessionRessource(SessionResourceModel sessionResourceModel);
    
    public void deleteASessionResource(SessionResourceModel sessionRessource);
    
     public void deleteAllSessionResource(SessionResourceModel sessionRessource);
    
    public ArrayList<SessionResourceModel> getSessionRessoureForASession(int sessionId);
    
}
