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
public class PreziContentModel extends RessourceModel implements Serializable {
 
    
    private String preziId;
    
      public PreziContentModel(){}
    
    public PreziContentModel(int ressourceId){
        super(ressourceId);
    }
    
    
    public PreziContentModel(int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool, String presiId){
        super(teacherId,dateCreated,onlineClassId, hasPrezi,hasReveal,hasStudyTool);
        this.preziId=presiId;
    }
    
    public PreziContentModel(int ressourceId,int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool, String preziId){
        
        super(ressourceId,teacherId,dateCreated, onlineClassId,  hasPrezi, hasReveal, hasStudyTool);
        this.preziId=preziId;
    }

    /**
     * @return the preziId
     */
    public String getPreziId() {
        return preziId;
    }

    /**
     * @param preziId the preziId to set
     */
    public void setPreziId(String preziId) {
        this.preziId = preziId;
    }
    
    
    
}
