/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.model;

import edu.ilstu.dao.StudyToolDAO;
import edu.ilstu.dao.StudyToolDAOimpl;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author kawibi
 */
public class StudyToolModel extends RessourceModel implements Serializable {

    private String articleLink;
    private String videolink;

    public StudyToolModel() {
    }

    public StudyToolModel(int ressourceId) {
        super(ressourceId);
    }

    public StudyToolModel(int teacherId, Date dateCreated, int onlineClassId, char hasPrezi, char hasReveal, char hasStudyTool, String articleLink, String videoLink) {
        super(teacherId, dateCreated, onlineClassId, hasPrezi, hasReveal, hasStudyTool);
        this.articleLink = articleLink;
        this.videolink = videoLink;
    }

    public StudyToolModel(int ressourceId, int teacherId, Date dateCreated, int onlineClassId, char hasPrezi, char hasReveal, char hasStudyTool, String articleLink, String videoLink) {

        super(ressourceId, teacherId, dateCreated, onlineClassId, hasPrezi, hasReveal, hasStudyTool);
        this.articleLink = articleLink;
        this.videolink = videoLink;
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

    public int createStudyTool() {

        StudyToolDAO stdao = new StudyToolDAOimpl();
        this.setHasStudyTool('Y');
        this.setRessourceId(stdao.createStudyRessource(this));

        return this.getRessourceId();

    }

    public void deleteStudyTool() {

        StudyToolDAO stdao = new StudyToolDAOimpl();

        stdao.deleteStudyRessource(this);

    }

    
    public StudyToolModel findStudyToolById(){
        
        StudyToolDAO stdao = new StudyToolDAOimpl();

        return stdao.getRessourceById(this.getRessourceId());
    }
    
    public ArrayList<StudyToolModel> findStudyToolByOnlinceClassId(){
        StudyToolDAO stdao = new StudyToolDAOimpl();

        return stdao.getStudytoolRessourceByOnlinceClasseId(this.getOnlineClassId());
        
    }
   
    public String studyToolToJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("dateCreated", super.getDateCreated());
        obj.put("onlineClassId", super.getOnlineClassId());
        obj.put("userid", super.getRessourceId());
        obj.put("userid", super.getTeacherId());
        obj.put("fname", this.articleLink);
        obj.put("fname", this.videolink);
        
        return obj.toJSONString();
    }
    
}
