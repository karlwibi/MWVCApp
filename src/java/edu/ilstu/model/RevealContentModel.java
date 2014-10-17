/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import edu.ilstu.dao.RevealContentDAO;
import edu.ilstu.dao.RevealContentDAOImpl;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class RevealContentModel extends RessourceModel implements Serializable {
 
    private int revealId;
    
    
    public RevealContentModel(){}
    
    public RevealContentModel(int ressourceId) {
        super(ressourceId);
    }
    
    public RevealContentModel(int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool){
        super(teacherId,dateCreated,onlineClassId,hasPrezi,hasReveal,hasStudyTool);
       
    }
    
    
    public RevealContentModel(int ressourceId,int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool, String articleLink,int revealId){
            super(ressourceId,teacherId,dateCreated, onlineClassId,hasPrezi,hasReveal, hasStudyTool);
        this.revealId=revealId;
        
    }

    /**
     * @return the revealId
     */
    public int getRevealId() {
        return revealId;
    }

    /**
     * @param revealId the revealId to set
     */
    public void setRevealId(int revealId) {
        this.revealId = revealId;
    }
    
    
public int createRevealRessource(){
    
    RevealContentDAO rcdao=new RevealContentDAOImpl();
    
    this.setHasReveal('Y');
    this.setRessourceId(rcdao.createRevealRessource(this));
    
    return this.getRessourceId();
}
   

public void updateRevealRessource(){
    
    RevealContentDAO rcdao=new RevealContentDAOImpl();
    
    rcdao.updateRevealRessource(this);
}


public void deleteRevealRessource(){
    
    RevealContentDAO rcdao=new RevealContentDAOImpl();
    
    rcdao.deleteRevealRessource(this);
    
}

public RevealContentModel findRevealRessourceById(){
    
    RevealContentDAO rcdao=new RevealContentDAOImpl();
    
    return rcdao.getRessourceById(this.getRessourceId());
}


public ArrayList<RevealContentModel> findReealRessourceByOnlineClassId(){
    
    RevealContentDAO rcdao=new RevealContentDAOImpl();
    
    return rcdao.getRevealRessourceByOnlinceClasseId(this.getOnlineClassId());
}


}
