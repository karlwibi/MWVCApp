/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import edu.ilstu.dao.ChatDAO;
import edu.ilstu.dao.ChatDAOImpl;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class ChatModel implements Serializable {
    
    private int chatid;
    private int onlineClassId;
    private Date chatdate;
    
    public ChatModel() {
    }
    
    public ChatModel(int chatid) {
        this.chatid = chatid;
    }
    
    public ChatModel(int onlineClassId, Date chatDate) {
        this.onlineClassId = onlineClassId;
        this.chatdate = chatDate;
    }
    
    public ChatModel(int chatid, int onlineClassId, Date chatDate) {
        
        this(onlineClassId, chatDate);
        this.chatid = chatid;
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
    
    public int createChat() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        return cdao.createChat(this);
    }
    
    public void updateChat() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        cdao.updateChat(this);
    }
    
    public void deleteChat() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        cdao.deleteChat(this);
    }
    
    public ChatModel getChatByID() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        return cdao.getChatById(this.getChatid());
    }
    
    public ChatModel getChatByDate() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        return cdao.getChatByDate(this.getChatdate());
    }
    
    public ArrayList<ChatModel> getRoomChatsInstance() {
        
        ChatDAO cdao = new ChatDAOImpl();
        
        return cdao.getRoomChatsInstance(this.getOnlineClassId());
    }
    
}
