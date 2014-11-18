/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import edu.ilstu.helper.Property;
import edu.ilstu.model.ClassSessionModel;
import edu.ilstu.model.ContentModel;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.PreziContentModel;
import edu.ilstu.model.RevealContentModel;
import edu.ilstu.model.RoomParticipantModel;
import edu.ilstu.model.ScheduleClassModel;
import edu.ilstu.model.SessionResourceModel;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.StudyToolModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author kawibi
 */
@ManagedBean
@SessionScoped
public class onlineClassDetailsController implements Serializable {

    private int onlineClassID;
    private int scheduleID;
    private String StudyToolType;
    private String presentationFlag;
    private boolean preziValid;
    private boolean slideValid;
    private boolean uploadDone;
    private int eventCounter = 0;
    private OnlineClassModel onlineClassModel;
    private ScheduleClassModel scheduleClassModel;
    private ClassSessionModel sessionModel;
    private StudentModel studentModel;
    private RoomParticipantModel roomParticipant;
    private PreziContentModel preziContentModel;
    private StudyToolModel studyToolModel;
    private SessionResourceModel sessionResourceModel;
    private RevealContentModel revealContentModel;
    private ContentModel contentModel;
    private ArrayList<ContentModel> contentList;
    private ArrayList<SessionResourceModel> sessionResourceList;
    private ArrayList<StudyToolModel> listStudyResource;
    private ArrayList<RoomParticipantModel> listParticipant;
    private ArrayList<ClassSessionModel> sessionList;
    private ArrayList<StudentModel> listEnrollStudent;
    private List<String> uploadedFiles;
    private Map<String, InputStream> tempUploadFiles;

    /**
     * Creates a new instance of onlineClassDetailsController
     */
    public onlineClassDetailsController() {

    }

    public void _init() {

        presentationFlag = "slide";
        preziValid = false;
        slideValid = true;
        setUploadDone(false);

        //initialize the fileupload list
        uploadedFiles = new ArrayList<>();
        tempUploadFiles = new HashMap<>();

        //get the online class information
        onlineClassModel = new OnlineClassModel();
        onlineClassModel.setOnlineClassId(onlineClassID);
        onlineClassModel = onlineClassModel.getAClass();

        //get class scheduled
        scheduleClassModel = new ScheduleClassModel();
        scheduleClassModel.setOnlineClassId(onlineClassID);
        scheduleClassModel = scheduleClassModel.getScheduleByOnlineClassID();
        scheduleID = scheduleClassModel.getScheduleClassId();

        //initialize class session;study model; and
        sessionModel = new ClassSessionModel();
        sessionModel.setScheduleClassId(scheduleID);
        //get list session for the current schedule
        sessionList = sessionModel.getClassSessionsByScheduleID();

        //initialize prezi content
        preziContentModel = new PreziContentModel();
        preziContentModel.setOnlineClassId(onlineClassID);

        //initialize Reveal Content
        setRevealContentModel(new RevealContentModel());
        getRevealContentModel().setOnlineClassId(onlineClassID);

        //inistialise the study resource 
        studyToolModel = new StudyToolModel();
        studyToolModel.setOnlineClassId(onlineClassID);
        listStudyResource = new ArrayList<>();
        listStudyResource.add(studyToolModel);

        //get the room participant
        roomParticipant = new RoomParticipantModel();
        roomParticipant.setOnlineClassId(onlineClassID);
        listParticipant = roomParticipant.getAllParticipantForRoom();

        listEnrollStudent = new ArrayList<>();

        //get student information and set the student list
        for (Iterator<RoomParticipantModel> i = listParticipant.iterator(); i.hasNext();) {
            studentModel = new StudentModel();
            studentModel = studentModel.getStudentById((i.next()).getStudentId());
            listEnrollStudent.add(studentModel);
        }

    }

    
    public void createSession() {
        int sessionId = 0;
        int revealId = 0;

        if (preziValid) {

            sessionModel.setPresentationId(preziContentModel.createPreziRessource());
            sessionId = sessionModel.createClassSession();

        }

        if (slideValid) {

            sessionModel.setPresentationId(revealContentModel.createRevealRessource());
            sessionId = sessionModel.createClassSession();
            revealContentModel = revealContentModel.findRevealRessourceById();
            revealId = revealContentModel.getRevealId();

            ///handleling the file upload
            //creating folders
            File onlineClassDir = new File(Property.getSavePath() + onlineClassID);
            File sessionDir = new File(onlineClassDir, "session_" + sessionId);
            File slideDir = new File(sessionDir, "slides");

            if (!(slideDir.exists())) {
                boolean userSuccess = slideDir.mkdirs();
            }
            //looping through the map to get the upload file
            for (Map.Entry<String, InputStream> entry : getTempUploadFiles().entrySet()) {
                int length = 0;
                int page = 0;
                if (entry != null) {
                    String prefix = FilenameUtils.getBaseName(entry.getKey());

                    length = prefix.length();
                    //getting the page number from the slide name
                    if (length == 6) {
                        page = Integer.parseInt(String.valueOf(prefix.charAt(length - 1)));
                    } else {
                        page = Integer.parseInt(prefix.substring(5, length));
                    }

                    contentModel = new ContentModel();
                    contentModel.setRevealId(revealId);
                    contentModel.setPage(page);

                    //String suffix = FilenameUtils.getExtension(uploadedFile.getFileName());
                    //create destination File
                    File file = new File(slideDir, entry.getKey());

                    InputStream input = null;
                    OutputStream output = null;
                    try {
                        input = entry.getValue();
                        output = new FileOutputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(onlineClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        IOUtils.copy(input, output);
                    } catch (IOException ex) {
                        Logger.getLogger(onlineClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        IOUtils.closeQuietly(output);
                        IOUtils.closeQuietly(input);
                    }
                    //inserting into the database
                    System.out.println("File without full Path: " + file.getAbsolutePath());
                    System.out.println("File full Path: " + file.getAbsolutePath());
                    contentModel.setContentText(file.getAbsolutePath());
                    contentModel.createContent();

                }
            }
        }

        for (StudyToolModel stm : listStudyResource) {

            if (!"".equals(stm.getArticleLink())) {
                sessionResourceModel = new SessionResourceModel();
                //setting the session resource attributes
                sessionResourceModel.setSessionId(sessionId);
                sessionResourceModel.setResourceId(stm.createStudyTool());
                //create the session ressource
                sessionResourceModel.createSessionRessource();
            }

        }
    }

    /**
     * add line to list
     */
    public void extend() {
        setStudyToolModel(new StudyToolModel());
        listStudyResource.add(studyToolModel);

    }

    /**
     * Remove the extra 
     * line from list
     */
    public void unExtend() {

        int lastIndex = listStudyResource.size() - 1;
        if (lastIndex != 0) {
            listStudyResource.remove(lastIndex);
        }
    }

    /**
     * check if Date is 
     * in the schedule range
     * date for the class
     */
    public void checkIfDateInRange() {

        if (!((sessionModel.getDateSession().after(scheduleClassModel.getStart_Date()) && sessionModel.getDateSession().before(scheduleClassModel.getEnd_Date())) || sessionModel.getDateSession().equals(scheduleClassModel.getEnd_Date()) || sessionModel.getDateSession().equals(scheduleClassModel.getStart_Date()))) {

            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date not in Range", "The date needs to set accordanly to your class schedule"));

        }
    }

    
    public void presentationListener() {

        if (presentationFlag.equals("slide")) {
            slideValid = true;
            preziValid = false;
        } else {
            preziValid = true;
            slideValid = false;
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        //uploadedFiles.add(event.getFile());
        if (eventCounter == 0) {
            // insert code to execute before first file is started
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UpLoad Info:", "your Upload is starting!"));
        }

        try {
            getTempUploadFiles().put(event.getFile().getFileName(), event.getFile().getInputstream());
            uploadedFiles.add(event.getFile().getFileName());
        } catch (IOException ex) {
            Logger.getLogger(onlineClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            eventCounter++;
        } // block finally

    }

    public void updateClassInfo() {

        this.onlineClassModel.updateClass();
        if (this.scheduleClassModel.getScheduleClassId() != 0) {
            this.scheduleClassModel.updateSchedule();
        } else {
            ScheduleClassModel scm = new ScheduleClassModel(this.onlineClassModel.getOnlineClassId());
            scm.setStart_Date(scheduleClassModel.getStartDate());
            scm.setEnd_Date(scheduleClassModel.getEndDate());
            scm.setStart_Time(scheduleClassModel.getStartTime());
            scm.setEnd_Time(scheduleClassModel.getEndTime());
            scm.saveSchedule();
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "your class information was updated!"));

    }

    /**
     * remove the student from the 
     * class list
     * @param studenId 
     */
    public void removeStudent(int studenId, String studentName){
     
        RoomParticipantModel rp = new RoomParticipantModel(this.onlineClassID, studenId);
        rp.deleteParticipant();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "Student "+ studentName +" was removed"));
        
    }
    
    
    public void removeSession(int sessionId){
        
        ClassSessionModel csm= new ClassSessionModel();
        csm.setSessionId(sessionId);
        csm.deleteClassSession();
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "session was removed"));
        
    }
    
    public void deleteClass(){
        
        int userId=onlineClassModel.getTeacherId();
        onlineClassModel.deleteClass();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/onlineClasses.xhtml?faces-redirect=true&userId="+userId);
        } catch (IOException ex) {
            Logger.getLogger(onlineClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
     * @return the scheduleClassModel
     */
    public ScheduleClassModel getScheduleClassModel() {
        return scheduleClassModel;
    }

    /**
     * @param scheduleClassModel the scheduleClassModel to set
     */
    public void setScheduleClassModel(ScheduleClassModel scheduleClassModel) {
        this.scheduleClassModel = scheduleClassModel;
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
     * @return the studentModel
     */
    public StudentModel getStudentModel() {
        return studentModel;
    }

    /**
     * @param studentModel the studentModel to set
     */
    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    /**
     * @return the sessionList
     */
    public ArrayList<ClassSessionModel> getSessionList() {
        return sessionList;
    }

    /**
     * @param sessionList the sessionList to set
     */
    public void setSessionList(ArrayList<ClassSessionModel> sessionList) {
        this.sessionList = sessionList;
    }

    /**
     * @return the listEnrollStudent
     */
    public ArrayList<StudentModel> getListEnrollStudent() {
        return listEnrollStudent;
    }

    /**
     * @param listEnrollStudent the listEnrollStudent to set
     */
    public void setListEnrollStudent(ArrayList<StudentModel> listEnrollStudent) {
        this.listEnrollStudent = listEnrollStudent;
    }

    /**
     * @return the roomParticipant
     */
    public RoomParticipantModel getRoomParticipant() {
        return roomParticipant;
    }

    /**
     * @param roomParticipant the roomParticipant to set
     */
    public void setRoomParticipant(RoomParticipantModel roomParticipant) {
        this.roomParticipant = roomParticipant;
    }

    /**
     * @return the listParticipant
     */
    public ArrayList<RoomParticipantModel> getListParticipant() {
        return listParticipant;
    }

    /**
     * @param listParticipant the listParticipant to set
     */
    public void setListParticipant(ArrayList<RoomParticipantModel> listParticipant) {
        this.listParticipant = listParticipant;
    }

    /**
     * @return the onlineClassID
     */
    public int getOnlineClassID() {
        return onlineClassID;
    }

    /**
     * @param onlineClassID the onlineClassID to set
     */
    public void setOnlineClassID(int onlineClassID) {
        this.onlineClassID = onlineClassID;
    }

    /**
     * @return the scheduleID
     */
    public int getScheduleID() {
        return scheduleID;
    }

    /**
     * @param scheduleID the scheduleID to set
     */
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
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
     * @return the listStudyResource
     */
    public ArrayList<StudyToolModel> getListStudyResource() {
        return listStudyResource;
    }

    /**
     * @param listStudyResource the listStudyResource to set
     */
    public void setListStudyResource(ArrayList<StudyToolModel> listStudyResource) {
        this.listStudyResource = listStudyResource;
    }

    /**
     * @return the StudyToolType
     */
    public String getStudyToolType() {
        return StudyToolType;
    }

    /**
     * @param StudyToolType the StudyToolType to set
     */
    public void setStudyToolType(String StudyToolType) {
        this.StudyToolType = StudyToolType;
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
     * @return the sessionResourceList
     */
    public ArrayList<SessionResourceModel> getSessionResourceList() {
        return sessionResourceList;
    }

    /**
     * @param sessionResourceList the sessionResourceList to set
     */
    public void setSessionResourceList(ArrayList<SessionResourceModel> sessionResourceList) {
        this.sessionResourceList = sessionResourceList;
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
     * @return the contentModel
     */
    public ContentModel getContentModel() {
        return contentModel;
    }

    /**
     * @param contentModel the contentModel to set
     */
    public void setContentModel(ContentModel contentModel) {
        this.contentModel = contentModel;
    }

    /**
     * @return the contentList
     */
    public ArrayList<ContentModel> getContentList() {
        return contentList;
    }

    /**
     * @param contentList the contentList to set
     */
    public void setContentList(ArrayList<ContentModel> contentList) {
        this.contentList = contentList;
    }

    /**
     * @return the presentationFlag
     */
    public String getPresentationFlag() {
        return presentationFlag;
    }

    /**
     * @param presentationFlag the presentationFlag to set
     */
    public void setPresentationFlag(String presentationFlag) {
        this.presentationFlag = presentationFlag;
    }

    /**
     * @return the preziValid
     */
    public boolean isPreziValid() {
        return preziValid;
    }

    /**
     * @param preziValid the preziValid to set
     */
    public void setPreziValid(boolean preziValid) {
        this.preziValid = preziValid;
    }

    /**
     * @return the slideValid
     */
    public boolean isSlideValid() {
        return slideValid;
    }

    /**
     * @param slideValid the slideValid to set
     */
    public void setSlideValid(boolean slideValid) {
        this.slideValid = slideValid;
    }

    /**
     * @return the uploadedFiles
     */
    public List<String> getUploadedFiles() {
        return uploadedFiles;
    }

    /**
     * @param uploadedFiles the uploadedFiles to set
     */
    public void setUploadedFiles(List<String> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    /**
     * @return the eventCounter
     */
    public int getEventCounter() {
        return eventCounter;
    }

    /**
     * @param eventCounter the eventCounter to set
     */
    public void setEventCounter(int eventCounter) {
        this.eventCounter = eventCounter;
    }

    /**
     * @return the tempUploadFiles
     */
    public Map<String, InputStream> getTempUploadFiles() {
        return tempUploadFiles;
    }

    /**
     * @param tempUploadFiles the tempUploadFiles to set
     */
    public void setTempUploadFiles(Map<String, InputStream> tempUploadFiles) {
        this.tempUploadFiles = tempUploadFiles;
    }

    /**
     * @return the uploadDone
     */
    public boolean isUploadDone() {
        return uploadDone;
    }

    /**
     * @param uploadDone the uploadDone to set
     */
    public void setUploadDone(boolean uploadDone) {
        this.uploadDone = uploadDone;
    }

}
