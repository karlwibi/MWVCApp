/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.io.Serializable;

/**
 *
 * @author kawibi
 */
public class ReactionModel implements Serializable{
    
    private int reactionId;
    private int discussionId;
    private int postby;
    private String reactionText;
    
    public ReactionModel(){}
    
    public ReactionModel(int reactionId){
        this.reactionId=reactionId;
    }
    
    public ReactionModel(int discussionId, int postby, String reactionText){
        this.discussionId=discussionId;
        this.postby=postby;
        this.reactionText=reactionText;
    }
    
    public ReactionModel(int reactionId,int discussionId, int postby, String reactionText){
        this(discussionId, postby, reactionText);
        this.reactionId=reactionId;
        
    }

    /**
     * @return the reactionId
     */
    public int getReactionId() {
        return reactionId;
    }

    /**
     * @param reactionId the reactionId to set
     */
    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    /**
     * @return the discussionId
     */
    public int getDiscussionId() {
        return discussionId;
    }

    /**
     * @param discussionId the discussionId to set
     */
    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
    }

    /**
     * @return the postby
     */
    public int getPostby() {
        return postby;
    }

    /**
     * @param postby the postby to set
     */
    public void setPostby(int postby) {
        this.postby = postby;
    }

    /**
     * @return the reactionText
     */
    public String getReactionText() {
        return reactionText;
    }

    /**
     * @param reactionText the reactionText to set
     */
    public void setReactionText(String reactionText) {
        this.reactionText = reactionText;
    }
    
    
    
    
}
