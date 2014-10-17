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
public abstract class RessourceModel implements Serializable {

    private int ressourceId;
    private int teacherId;
    private Date dateCreated;
    private int onlineClassId;
    private char hasPrezi;
    private char hasReveal;
    private char hasStudyTool;

    public RessourceModel() {
        this.hasPrezi='N';
        this.hasReveal='N';
        this.hasStudyTool='N';
    }

    public RessourceModel(int ressourceId) {

        this();
        this.ressourceId = ressourceId;

        
    }

    public RessourceModel(int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool) {
        this.teacherId = teacherId;
        this.dateCreated = dateCreated;
        this.onlineClassId = onlineClassId;
        this.hasPrezi = hasPrezi;
        this.hasReveal=hasReveal;
        this.hasStudyTool = hasStudyTool;

    }

    public RessourceModel(int ressourceId, int teacherId, Date dateCreated, int onlineClassId, char hasPrezi,char hasReveal, char hasStudyTool) {

        this(teacherId, dateCreated, onlineClassId, hasPrezi,hasReveal, hasStudyTool);
        this.ressourceId = ressourceId;

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
    public char getHasPrezi() {
        return hasPrezi;
    }

    /**
     * @param hasPrezi the hasPresentation to set
     */
    public void setHasPrezi(char hasPrezi) {
        this.hasPrezi = hasPrezi;
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

    /**
     * @return the hasReveal
     */
    public char getHasReveal() {
        return hasReveal;
    }

    /**
     * @param hasReveal the hasReveal to set
     */
    public void setHasReveal(char hasReveal) {
        this.hasReveal = hasReveal;
    }

    
    
}
