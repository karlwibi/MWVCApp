/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import edu.ilstu.dao.ClassSessionDAO;
import edu.ilstu.dao.ClassSessionDAOImpl;
import java.sql.Date;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class ClassSessionModel {
    
    private int sessionId;
    private Date sessionDate;
     private java.util.Date dateSession;
    private int scheduleClassId;
    private int presentationId;
  
    
    
    public ClassSessionModel(){}
    
    public ClassSessionModel(Date sessionDate, int scheduleClassId, int presentationId){
        
        this.sessionDate=sessionDate;
        this.scheduleClassId=scheduleClassId;
        this.presentationId=presentationId;
        
        
    }

    /**
     * @return the sessionId
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the sessionDate
     */
    public Date getSessionDate() {
        return sessionDate;
    }

    
    
    
    /**
     * @param sessionDate the sessionDate to set
     */
    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    /**
     * @return the scheduleClassId
     */
    public int getScheduleClassId() {
        return scheduleClassId;
    }

    /**
     * @param scheduleClassId the scheduleClassId to set
     */
    public void setScheduleClassId(int scheduleClassId) {
        this.scheduleClassId = scheduleClassId;
    }

    /**
     * @return the presentationId
     */
    public int getPresentationId() {
        return presentationId;
    }

    /**
     * @param presentationId the presentationId to set
     */
    public void setPresentationId(int presentationId) {
        this.presentationId = presentationId;
    }

   
    
    
    
    public int createClassSession(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        return csdao.createSession(this);
    }
    
    public void updateClassSession(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        csdao.updateSession(this);
    }
    
    public void deleteClassSession(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        csdao.deleteSession(this);
    }
    /**
     * return class Session by SessionID 
     * 
     * @return classSessionModel 
     */
    public ClassSessionModel getAClassSession(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        return csdao.getASession(this.sessionId);
    }
    
    /**
     * this method retrieve all online class session based on the
     * scheduleID
     * 
     * @return List A list of ClassSessionModel
     */
    public ArrayList<ClassSessionModel> getClassSessionsByScheduleID(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        return csdao.getSessionsByScheduleId(this.scheduleClassId);
    }
       
    /**
     * return all session from the database
     * @return 
     */
    public ArrayList<ClassSessionModel> getAllClassSessions(){
        
        ClassSessionDAO csdao = new ClassSessionDAOImpl();
        
        return csdao.getAllSessions();
    }
    
     public String classSessionToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("presentationId", this.presentationId);
        obj.put("scheduleId", this.scheduleClassId);
        obj.put("sessionDate", this.sessionDate);
        obj.put("sessionId", this.sessionId);

        return obj.toJSONString();

     }

    /**
     * @return the dateSession
     */
    public java.util.Date getDateSession() {
        return (java.util.Date)sessionDate;
    }

    /**
     * @param dateSession the dateSession to set
     */
    public void setDateSession(java.util.Date dateSession) {
        this.dateSession = dateSession;
        if( this.dateSession!=null)
        setSessionDate(new java.sql.Date(this.dateSession.getTime()));
    }
     
     
     
}


