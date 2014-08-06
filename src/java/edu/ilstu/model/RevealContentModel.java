/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.io.Serializable;
import java.sql.Date;

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
    
    

    
}
