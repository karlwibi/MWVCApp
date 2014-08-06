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
        
        return ocdao.createOnlineClass(this);
    }
    
    
    public void updateClass(){
        
        ocdao.updateOnlineClass(this);
        
    }
    
    public void deleteClass(){
        
        ocdao.deleteOnlineClass(this);
    }
    
    
    public ArrayList<OnlineClassModel> getClasses(){
        
        ArrayList<OnlineClassModel> list= ocdao.getOnliceClasses();
        
        return list;
    }
    
    /**
     * Find an online class by id
     * @return an object of type OnlineClassModel
     */
    public OnlineClassModel getAClass(){
        
        OnlineClassModel ocm = ocdao.getfindOnlineClassById(this.onlineClassId);
        
        return ocm;
    }
    /**
     * find class by roomId
     * @return an object of type OnlineClassModel
     */
    public OnlineClassModel getClassByRoomId(){
        
        OnlineClassModel ocm = ocdao.findByRoomId(roomid);
        
        return ocm;
    }
    
}
