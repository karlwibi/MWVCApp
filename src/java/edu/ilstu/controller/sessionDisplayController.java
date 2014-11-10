/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.dao.RessourceDAOImpl;
import edu.ilstu.filters.AlphanumComparator;
import edu.ilstu.mail.Email;
import edu.ilstu.model.ClassSessionModel;
import edu.ilstu.model.ContentModel;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.PreziContentModel;
import edu.ilstu.model.RevealContentModel;
import edu.ilstu.model.ScheduleClassModel;
import edu.ilstu.model.SessionResourceModel;
import edu.ilstu.model.StudyToolModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kawibi
 */
@ManagedBean
@RequestScoped
public class sessionDisplayController {

    private String resourceType;
    private int sessionId;
    private boolean isPresentation;
    private boolean isPreziPresentation;
    private boolean isStudyResource;
    private ClassSessionModel sessionModel;
    private OnlineClassModel onlineClassModel;
    private PreziContentModel preziContentModel;
    private RevealContentModel revealContentModel;
    private StudyToolModel studyToolModel;
    private SessionResourceModel sessionResourceModel;
    private ScheduleClassModel sceduleClassModel;
    private ContentModel slideContent;
    private List<ContentModel> slideContentList;
    private List<ClassSessionModel> classSessionList;
    private List<StudyToolModel> studyResourceList;
    private List<String> slideList;
    private List<SessionResourceModel> sessionResourceList;

    /**
     * Creates a new instance of sessionDisplayController
     */
    public sessionDisplayController() {
        
        
    }

    public void init() {
        if (!resourceType.isEmpty() && sessionId != 0) {

            isPresentation=false;
            isPresentation=false;
            isStudyResource=false;
            sessionModel = new ClassSessionModel();
            onlineClassModel = new OnlineClassModel();
            preziContentModel = new PreziContentModel();
            revealContentModel = new RevealContentModel();
            studyToolModel = new StudyToolModel();
            sessionResourceModel = new SessionResourceModel();
            sceduleClassModel = new ScheduleClassModel();
            slideContent = new ContentModel();
            classSessionList = new ArrayList();
            studyResourceList = new ArrayList();
            slideList = new ArrayList();
            slideContentList = new ArrayList();
            getResources();

        }
    }

    private void getResources() {

        sessionModel.setSessionId(sessionId);
        //get the session info
        sessionModel = sessionModel.getAClassSession();
        //get the schedule information 
        sceduleClassModel.setScheduleClassId(sessionModel.getScheduleClassId());
        sceduleClassModel = sceduleClassModel.getASchedule();
        //retreive the online class information
        onlineClassModel.setOnlineClassId(sceduleClassModel.getOnlineClassId());
        onlineClassModel = onlineClassModel.getAClass();

        //testing email
        
//        Email email=new Email();
//        email.setOnlineClassId(sceduleClassModel.getOnlineClassId());
//        email.onlineClassCreateMessage();

        
        //getting the information for the presentation
        if (resourceType.equals("Presentation")) {

            

            if (!RessourceDAOImpl.checkResourceType(sessionModel.getPresentationId()).isEmpty()) {

                if (RessourceDAOImpl.checkResourceType(sessionModel.getPresentationId()).equals("Prezi")) {

                    isPreziPresentation=true;
                    preziContentModel.setRessourceId(sessionModel.getPresentationId());
                    preziContentModel = preziContentModel.findPreziRessourceById();

                } else {
                    isPresentation = true;

                    revealContentModel.setRessourceId(sessionModel.getPresentationId());
                    revealContentModel = revealContentModel.findRevealRessourceById();
                    getTheSlides(revealContentModel.getRevealId());

                }
            }

        } else {

        //this section is getting informmation for the resourceLinks
            isStudyResource = true;
            //gett the sessionResource info
            sessionResourceModel.setSessionId(sessionId);
            sessionResourceList = sessionResourceModel.getSessionRessoureForASession();

            for (SessionResourceModel srm : sessionResourceList) {
                //retrieve the study resource and set it 
                studyToolModel.setRessourceId(srm.getResourceId());
                StudyToolModel stm = studyToolModel.findStudyToolById();
                studyResourceList.add(stm);
            }

        }
    }

    public void getTheSlides(int revealId) {

        slideContent.setRevealId(revealId);
        slideContentList = slideContent.findContentByRevealID();

        for (ContentModel slide : slideContentList) {

            String path = slide.getContentText();
            String fileName = getSlideName(path);
            System.out.println("Slide Filename: " + fileName);
            slideList.add(fileName);
        }
        Collections.sort(slideList,  new AlphanumComparator());
    }

    public String getSlideName(String path) {

        File file = new File(path);
        String slideName = file.getName();

        return slideName;

    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return the sessionId
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the sessionModel
     */
    public ClassSessionModel getSessionModel() {
        return sessionModel;
    }

    /**
     * @param sessionModel the sessionModel to set
     */
    public void setSessionModel(ClassSessionModel sessionModel) {
        this.sessionModel = sessionModel;
    }

    /**
     * @return the onlineClassModel
     */
    public OnlineClassModel getOnlineClassModel() {
        return onlineClassModel;
    }

    /**
     * @param onlineClassModel the onlineClassModel to set
     */
    public void setOnlineClassModel(OnlineClassModel onlineClassModel) {
        this.onlineClassModel = onlineClassModel;
    }

    /**
     * @return the preziContentModel
     */
    public PreziContentModel getPreziContentModel() {
        return preziContentModel;
    }

    /**
     * @param preziContentModel the preziContentModel to set
     */
    public void setPreziContentModel(PreziContentModel preziContentModel) {
        this.preziContentModel = preziContentModel;
    }

    /**
     * @return the revealContentModel
     */
    public RevealContentModel getRevealContentModel() {
        return revealContentModel;
    }

    /**
     * @param revealContentModel the revealContentModel to set
     */
    public void setRevealContentModel(RevealContentModel revealContentModel) {
        this.revealContentModel = revealContentModel;
    }

    /**
     * @return the studyToolModel
     */
    public StudyToolModel getStudyToolModel() {
        return studyToolModel;
    }

    /**
     * @param studyToolModel the studyToolModel to set
     */
    public void setStudyToolModel(StudyToolModel studyToolModel) {
        this.studyToolModel = studyToolModel;
    }

    /**
     * @return the sessionResourceModel
     */
    public SessionResourceModel getSessionResourceModel() {
        return sessionResourceModel;
    }

    /**
     * @param sessionResourceModel the sessionResourceModel to set
     */
    public void setSessionResourceModel(SessionResourceModel sessionResourceModel) {
        this.sessionResourceModel = sessionResourceModel;
    }

    /**
     * @return the sceduleClassModel
     */
    public ScheduleClassModel getSceduleClassModel() {
        return sceduleClassModel;
    }

    /**
     * @param sceduleClassModel the sceduleClassModel to set
     */
    public void setSceduleClassModel(ScheduleClassModel sceduleClassModel) {
        this.sceduleClassModel = sceduleClassModel;
    }

    /**
     * @return the slideContent
     */
    public ContentModel getSlideContent() {
        return slideContent;
    }

    /**
     * @param slideContent the slideContent to set
     */
    public void setSlideContent(ContentModel slideContent) {
        this.slideContent = slideContent;
    }

    /**
     * @return the classSessionList
     */
    public List<ClassSessionModel> getClassSessionList() {
        return classSessionList;
    }

    /**
     * @param classSessionList the classSessionList to set
     */
    public void setClassSessionList(List<ClassSessionModel> classSessionList) {
        this.classSessionList = classSessionList;
    }

    /**
     * @return the studyResourceList
     */
    public List<StudyToolModel> getStudyResourceList() {
        return studyResourceList;
    }

    /**
     * @param studyResourceList the studyResourceList to set
     */
    public void setStudyResourceList(List<StudyToolModel> studyResourceList) {
        this.studyResourceList = studyResourceList;
    }

    /**
     * @return the slideList
     */
    public List<String> getSlideList() {
        return slideList;
    }

    /**
     * @param slideList the slideList to set
     */
    public void setSlideList(List<String> slideList) {
        this.slideList = slideList;
    }

    /**
     * @return the slideContentList
     */
    public List<ContentModel> getSlideContentList() {
        return slideContentList;
    }

    /**
     * @param slideContentList the slideContentList to set
     */
    public void setSlideContentList(List<ContentModel> slideContentList) {
        this.slideContentList = slideContentList;
    }

    /**
     * @return the isPresentation
     */
    public boolean isIsPresentation() {
        return isPresentation;
    }

    /**
     * @param isPresentation the isPresentation to set
     */
    public void setIsPresentation(boolean isPresentation) {
        this.isPresentation = isPresentation;
    }

    /**
     * @return the isStudyResource
     */
    public boolean isIsStudyResource() {
        return isStudyResource;
    }

    /**
     * @param isStudyResource the isStudyResource to set
     */
    public void setIsStudyResource(boolean isStudyResource) {
        this.isStudyResource = isStudyResource;
    }

    /**
     * @return the sessionResourceList
     */
    public List<SessionResourceModel> getSessionResourceList() {
        return sessionResourceList;
    }

    /**
     * @param sessionResourceList the sessionResourceList to set
     */
    public void setSessionResourceList(List<SessionResourceModel> sessionResourceList) {
        this.sessionResourceList = sessionResourceList;
    }

    /**
     * @return the isPreziPresentation
     */
    public boolean isIsPreziPresentation() {
        return isPreziPresentation;
    }

    /**
     * @param isPreziPresentation the isPreziPresentation to set
     */
    public void setIsPreziPresentation(boolean isPreziPresentation) {
        this.isPreziPresentation = isPreziPresentation;
    }

}
