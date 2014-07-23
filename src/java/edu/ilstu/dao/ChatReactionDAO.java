/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ChatReactionModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ChatReactionDAO {
    
    public int postNewReaction(ChatReactionModel chatReaction);
    
    public void updateChatreaction(ChatReactionModel chatReaction);
    
    public void DeleteChatReaction(ChatReactionModel chatReaction);
    
    public ArrayList<ChatReactionModel> getChatReactionById(int chatId);
    
    public ChatReactionModel getChatReactionForUser(int chatId, int userId);
    
    
    
    
    
}
