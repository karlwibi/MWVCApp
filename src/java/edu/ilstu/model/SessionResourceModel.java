/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.SessionResourceDAO;
import edu.ilstu.dao.SessionResourceDAOImpl;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class SessionResourceModel {
    
    private int sessionId;
    private int resourceId;
    
    public SessionResourceModel() {
    }
    
    public SessionResourceModel(int sessionId, int resourceId) {
        this.sessionId = sessionId;
        this.resourceId = resourceId;
    }
    
    /**
     * create a session Resource
     */
    public void createSessionRessource() {
        
        SessionResourceDAO srdao = new SessionResourceDAOImpl();
        
        srdao.createSessionRessource(this);
        
    }

    /**
     * delete a study tool
     * resource related to
     * a particular session
     */
    public void deleteASessionRessource() {
       
        SessionResourceDAO srdao = new SessionResourceDAOImpl();
        
        srdao.deleteASessionResource(this);
    }

    /**
     * delete all study tool
     * related to a Session
     */
    public void deleteAllSessionRessource() {
       
        SessionResourceDAO srdao = new SessionResourceDAOImpl();
        
        srdao.deleteASessionResource(this);
    }

    /**
     * return all the study tool for a session
     *
     * @return List
     *         ArrayList of SessionResourceModel
     */
    public ArrayList<SessionResourceModel> getSessionRessoureForASession() {
        
        SessionResourceDAO srdao = new SessionResourceDAOImpl();
        
        ArrayList<SessionResourceModel> list = srdao.getSessionRessoureForASession(this.sessionId);
        
        return list;
        
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
     * @return the resourceId
     */
    public int getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    
    
     public String sessionResourceToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("sessionId", this.sessionId);
        obj.put("resourceId", this.resourceId);
        
        return obj.toJSONString();

     }
}
