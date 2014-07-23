/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

/**
 *
 * @author kawibi
 */
public class ContentModel {
    
    private int contentId;
    private int revealId;
    private int page;
    private String contentText;
    
    
    public ContentModel(){}
    
    public ContentModel(int contentId){
        this.contentId=contentId;
    }
    
    public ContentModel (int revealId, String contentText, int page){
        this.revealId=revealId;
        this.contentText=contentText;
        this.page=page;
    }
    
    public ContentModel(int contentId,int revealId, String conteText, int page){
        
       this(revealId,conteText,page);
       this.contentId=contentId;
        
    }

    /**
     * @return the contentId
     */
    public int getContentId() {
        return contentId;
    }

    /**
     * @param contentId the contentId to set
     */
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    /**
     * @return the revealId
     */
    public int getRevealId() {
        return revealId;
    }

    /**
     * @param revealId the revealId to set
     */
    public void setRevealId(int revealId) {
        this.revealId = revealId;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the contentText
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * @param contentText the contentText to set
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    
    
    
}
