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
public class StudyToolModel extends RessourceModel{

    private String articleLink;
    private String videolink;
    
     public StudyToolModel(){}
    
    public StudyToolModel(int ressourceId) {
        super(ressourceId);
    }
    
    public StudyToolModel(int teacherId, Date dateCreated, int onlineClassId, char hasPresentation, char hasStudyTool, String articleLink,String videoLink ){
        super(teacherId,dateCreated,onlineClassId,hasPresentation,hasStudyTool);
        this.articleLink=articleLink;
        this.videolink=videoLink;
    }
    
    
    public StudyToolModel(int ressourceId,int teacherId, Date dateCreated, int onlineClassId, char hasPresentation, char hasStudyTool, String articleLink,String videoLink ){
    
        super(ressourceId,teacherId,dateCreated, onlineClassId, hasPresentation, hasStudyTool);
        this.articleLink=articleLink;
        this.videolink=videoLink;
    }

    /**
     * @return the articleLink
     */
    public String getArticleLink() {
        return articleLink;
    }

    /**
     * @param articleLink the articleLink to set
     */
    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    /**
     * @return the videolink
     */
    public String getVideolink() {
        return videolink;
    }

    /**
     * @param videolink the videolink to set
     */
    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }
    
    
}