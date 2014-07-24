/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.util.Random;

/**
 *
 * @author kawibi
 */
public class OnlineClassModel {
 
    private int onlineClassId;
    private String title;
    private String description;
    private int roomid;
    
    public OnlineClassModel(){}
    
    public  OnlineClassModel(String title, String description){
        Random rn= new Random();
        
        this.title=title;
        this.description=description;
        roomid=rn.nextInt(9999-1000)+1000;    
        
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
    * Generates a new 
    * roomid
    */ 
   public void generateRoomId(){
       Random rn= new Random();
        setRoomid(rn.nextInt(9999-1000)+1000);
   }

    /**
     * @param roomid the roomid to set
     */
    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }
   
   
}
