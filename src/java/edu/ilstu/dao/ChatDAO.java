/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ChatModel;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ChatDAO {
    
    public int createChat(ChatModel chat);
   
    public void updateChat (ChatModel chat);
    
    public void deleteChat (ChatModel chat);
    
    public ChatModel getChatById (int chatId);
     
    public ChatModel getChatByDate (Date date);
    
    public ArrayList<ChatModel> getRoomChatsInstance(int onlineClassId);
    
    
    
    
    
    
}
