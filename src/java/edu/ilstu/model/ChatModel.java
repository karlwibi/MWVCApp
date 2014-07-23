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
public class ChatModel {
    
    private int chatid;
    private int onlineClassId;
    private int userId;
    private Date chatdate;
   
    public ChatModel(){}
    
    public ChatModel(int chatid){
        this.chatid=chatid;
    }
    
    public ChatModel(int onlineClassId, int userId, Date chatDate){
        this.onlineClassId=onlineClassId;
        this.userId=userId;
        this.chatdate=chatDate;
    }
    
    public ChatModel(int chatid, int onlineClassId, int userId, Date chatDate){
        
        this(onlineClassId,userId, chatDate);
        this.chatid=chatid;
    }

    /**
     * @return the chatid
     */
    public int getChatid() {
        return chatid;
    }

    /**
     * @param chatid the chatid to set
     */
    public void setChatid(int chatid) {
        this.chatid = chatid;
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
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the chatdate
     */
    public Date getChatdate() {
        return chatdate;
    }

    /**
     * @param chatdate the chatdate to set
     */
    public void setChatdate(Date chatdate) {
        this.chatdate = chatdate;
    }
    
    
    
}
