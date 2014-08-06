/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ReactionModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ReactionDAO {
    
   public int newReaction(ReactionModel reaction); 
   
   public void updateReaction (ReactionModel reaction);
   
   public ReactionModel getReactionById(int reactionId);
         
   public ArrayList<ReactionModel> getReactionByUserId(int userId);
   
   public ArrayList<ReactionModel> getReactionByDiscussionId(int discussionId);
   
   public void deleteReaction(ReactionModel reaction);
}
