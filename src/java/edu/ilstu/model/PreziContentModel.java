/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import edu.ilstu.dao.PreziContentDAO;
import edu.ilstu.dao.PreziContentDAOImpl;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import org.json.simple.JSONObject;

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
    
    public int createPreziRessource(){
        
        PreziContentDAO pcdao= new PreziContentDAOImpl();
        
        this.setHasPrezi('Y');
        
        this.setRessourceId(pcdao.createPreziRessource(this));
        
        return this.getRessourceId();
    }
    
    public void updatePreziRessource(){
        
        PreziContentDAO pcdao= new PreziContentDAOImpl();
        
        pcdao.updatePreziRessource(this);
        
    }
    
    public void deletePreziRessource(){
        
        PreziContentDAO pcdao= new PreziContentDAOImpl();
        
        pcdao.deletePreziRessource(this);
    }
    
    public ArrayList<PreziContentModel> findPreziRessourceByOnlineClassId(){
        
        PreziContentDAO pcdao= new PreziContentDAOImpl();
        
        return pcdao.getPreziRessourcebyOnlineClassId(this.getOnlineClassId());
        
    }
    
    public PreziContentModel findPreziRessourceById(){
        
        
        PreziContentDAO pcdao= new PreziContentDAOImpl();
        
        return pcdao.getPreziRessourcesByRessourceId(this.getRessourceId());
        
    }
    
     public String PreziToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("dateCreated", super.getDateCreated());
        obj.put("onlineClassId", super.getOnlineClassId());
        obj.put("userid", super.getRessourceId());
        obj.put("userid", super.getTeacherId());
        obj.put("fname", this.getPreziId());
        
        return obj.toJSONString();
    }
    
}
