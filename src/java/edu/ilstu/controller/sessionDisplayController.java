/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.model.ClassSessionModel;
import edu.ilstu.model.ContentModel;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.PreziContentModel;
import edu.ilstu.model.RevealContentModel;
import edu.ilstu.model.ScheduleClassModel;
import edu.ilstu.model.SessionResourceModel;
import edu.ilstu.model.StudyToolModel;
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
    private ClassSessionModel sessionModel;
    private OnlineClassModel onlineClassModel;
    private PreziContentModel preziContentModel;
    private RevealContentModel revealContentModel;
    private StudyToolModel studyToolModel;
    private SessionResourceModel sessionResourceModel;
    private ScheduleClassModel sceduleClassModel;
    private ContentModel slideContent;
    private List<ClassSessionModel> classSessionList;
    private List<StudyToolModel> studyResourceList;

    /**
     * Creates a new instance of sessionDisplayController
     */
    public sessionDisplayController() {
        
        if (resourceType!=null && sessionId!=0){
            sessionModel =new ClassSessionModel();
             onlineClassModel=new OnlineClassModel();
             preziContentModel=new PreziContentModel();
                     
             
             
        }
    }
    
    public void init(){
        
    }
}
