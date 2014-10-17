/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.OnlineClassDAO;
import edu.ilstu.dao.OnlineClassDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class OnlineClassModel implements Serializable {

    private int onlineClassId;
    private String title;
    private String description;
    private int roomid;
    private OnlineClassDAO ocdao;
    private int teacherId;
    
    public OnlineClassModel() {
        Random rn = new Random();
        ocdao=new OnlineClassDAOImpl();
        this.roomid = (rn.nextInt(9999 - 1000)) + 1000;
    }

    public OnlineClassModel(String title, String description) {
        this();
        this.title = title;
        this.description = description;

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the roomid
     */
    public int getRoomid() {
        return roomid;
    }

    /**
     * Generates a new roomid
     */
    public void generateRoomId() {
        Random rn = new Random();
        setRoomid(rn.nextInt(9999 - 1000) + 1000);
    }

    /**
     * @param roomid the roomid to set
     */
    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    
    public int saveClass(){
        
        return getOcdao().createOnlineClass(this);
    }
    
    
    public void updateClass(){
        
        getOcdao().updateOnlineClass(this);
        
    }
    
    public void deleteClass(){
        
        getOcdao().deleteOnlineClass(this);
    }
    
    
    public ArrayList<OnlineClassModel> getClasses(){
        
        ArrayList<OnlineClassModel> list= getOcdao().getOnliceClasses();
        
        return list;
    }
    
    
    public ArrayList<OnlineClassModel> getClassesByTeacherID(){
        
        ArrayList<OnlineClassModel> list= getOcdao().getOnliceClassesByTeacherId(this.getTeacherId());
        
        return list;
    }
    /**
     * Find an online class by id
     * @return an object of type OnlineClassModel
     */
    public OnlineClassModel getAClass(){
        
        OnlineClassModel ocm = getOcdao().getfindOnlineClassById(this.onlineClassId);
        
        return ocm;
    }
    /**
     * find class by roomId
     * @return an object of type OnlineClassModel
     */
    public OnlineClassModel getClassByRoomId(){
        
        OnlineClassModel ocm = getOcdao().findByRoomId(roomid);
        
        return ocm;
    }
    
    
    
    
    
    
    
    
     public String onlineClassToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("description", this.description);
        obj.put("onlineClassId", this.onlineClassId);
        obj.put("roomid", this.roomid);
        obj.put("title", this.title);
        obj.put("teacherId", this.getTeacherId());

        return obj.toJSONString();
    }

    /**
     * @return the ocdao
     */
    public OnlineClassDAO getOcdao() {
        return ocdao;
    }

    /**
     * @param ocdao the ocdao to set
     */
    public void setOcdao(OnlineClassDAO ocdao) {
        this.ocdao = ocdao;
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



}


