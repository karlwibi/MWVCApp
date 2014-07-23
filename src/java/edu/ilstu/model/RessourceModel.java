/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.sql.Date;

/**
 *
 * @author kawibi
 */
public abstract class RessourceModel {
    
    private int ressourceId;
    private int teacherId;
    private Date dateCreated;
    private int onlineClassId;
    private char hasPresentation;
    private char hasStudyTool;
    
    
    
    public RessourceModel(){}
    
    public RessourceModel(int ressourceId){
    
        this.ressourceId=ressourceId;
    
    }
    
    public RessourceModel(int teacherId, Date dateCreated, int onlineClassId, char hasPresentation, char hasStudyTool){
        this.teacherId=teacherId;
        this.dateCreated=dateCreated;
        this.onlineClassId=onlineClassId;
        this.hasPresentation=hasPresentation;
        this.hasStudyTool=hasStudyTool;
        
    }
    
    public RessourceModel(int ressourceId,int teacherId, Date dateCreated, int onlineClassId, char hasPresentation, char hasStudyTool ){
        
        this(teacherId,dateCreated,onlineClassId,hasPresentation, hasStudyTool);
        this.ressourceId=ressourceId;
        
    }

    /**
     * @return the ressourceId
     */
    public int getRessourceId() {
        return ressourceId;
    }

    /**
     * @param ressourceId the ressourceId to set
     */
    public void setRessourceId(int ressourceId) {
        this.ressourceId = ressourceId;
    }

    /**
     * @return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the onlineClassId
     */
    public int getOnlineClassId() {
        return onlineClassId;
    }

    /**
     * @param onlineClassId the onlineClassId to set
     */
    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
    }

    /**
     * @return the hasPresentation
     */
    public char getHasPresentation() {
        return hasPresentation;
    }

    /**
     * @param hasPresentation the hasPresentation to set
     */
    public void setHasPresentation(char hasPresentation) {
        this.hasPresentation = hasPresentation;
    }

    /**
     * @return the hasStudyTool
     */
    public char getHasStudyTool() {
        return hasStudyTool;
    }

    /**
     * @param hasStudyTool the hasStudyTool to set
     */
    public void setHasStudyTool(char hasStudyTool) {
        this.hasStudyTool = hasStudyTool;
    }
    
    
    
    
    
}
