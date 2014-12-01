/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.controller;

import com.jcabi.aspects.Async;
import edu.ilstu.admin.UserLogin;
import edu.ilstu.admin.UserLoginRole;
import edu.ilstu.mail.Email;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.RoomParticipantModel;
import edu.ilstu.model.ScheduleClassModel;
import edu.ilstu.model.StudentModel;
import edu.ilstu.model.UserModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kawibi
 */
@ManagedBean
@SessionScoped
public class RoomParticipantController implements Serializable {

    private int roomid;
    private RoomParticipantModel rpm;
    private OnlineClassModel ocm;
    private StudentModel studentModel;
    private List<StudentModel> studentList;
    private List<String> selectedStudents;
    private List<StudentModel> studentList2;
    private List<String> selectedStudents2;
    private List<StudentModel> studentListManual;
    private UploadedFile file;

    /**
     * Creates a new instance of RoomParticipant
     */
    public RoomParticipantController() {

    }

    public void init() {

        RoomParticipantController roomParticipant = (RoomParticipantController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("roomParticipant");

        if (roomParticipant != null) {
            rpm = null;
            ocm = null;
            studentModel = null;
            studentList = null;
            selectedStudents = null;
            studentList2 = null;
            selectedStudents2 = null;
            setStudentListManual(null);

        }

        initStudentManualList();
        studentList2 = new ArrayList();

        setOcm(new OnlineClassModel());
        getOcm().setRoomid(roomid);
        setOcm(getOcm().getClassByRoomId());//get all the information for online Class

        rpm = new RoomParticipantModel();
        rpm.setOnlineClassId(ocm.getOnlineClassId());

        studentModel = new StudentModel();
        setStudentList(studentModel.getAllStudents());

        if (!rpm.getAllParticipantForRoom().isEmpty()) {
            for (Iterator<RoomParticipantModel> r = rpm.getAllParticipantForRoom().iterator(); r.hasNext();) {
                int id = r.next().getStudentId();
                for (Iterator<StudentModel> s = studentList.iterator(); s.hasNext();) {
                    StudentModel sm = s.next();

                    if (sm.getUserid() == id) {
                        studentList2.add(sm);
                        s.remove();
                    }
                }

            }
        }

    }

    /**
     * @return the roomid
     */
    public int getRoomid() {
        return roomid;
    }

    /**
     * @param roomid the roomid to set
     */
    public void setRoomid(int roomid) {
        this.roomid = roomid;

    }

    /**
     * @return the rpm
     */
    public RoomParticipantModel getRpm() {
        return rpm;
    }

    /**
     * @param rpm the rpm to set
     */
    public void setRpm(RoomParticipantModel rpm) {
        this.rpm = rpm;
    }

    /**
     * @return the ocm
     */
    public OnlineClassModel getOcm() {
        return ocm;
    }

    /**
     * @param ocm the ocm to set
     */
    public void setOcm(OnlineClassModel ocm) {
        this.ocm = ocm;
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
     * @return the studentModels
     */
    public List<StudentModel> getStudentModels() {
        return getStudentList();
    }

    /**
     * @param studentModels the studentModels to set
     */
    public void setStudentModels(List<StudentModel> studentModels) {
        this.setStudentList(studentModels);
    }

    /**
     * @return the studentList
     */
    public List<StudentModel> getStudentList() {
        return studentList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(List<StudentModel> studentList) {
        this.studentList = studentList;
    }

    /**
     * @return the selectedStudents
     */
    public List<String> getSelectedStudents() {
        return selectedStudents;
    }

    /**
     * @param selectedStudents the selectedStudents to set
     */
    public void setSelectedStudents(List<String> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    /**
     * @return the studentList2
     */
    public List<StudentModel> getStudentList2() {
        return studentList2;
    }

    /**
     * @param studentList2 the studentList2 to set
     */
    public void setStudentList2(List<StudentModel> studentList2) {
        this.studentList2 = studentList2;
    }

    /**
     * @return the selectedStudents2
     */
    public List<String> getSelectedStudents2() {
        return selectedStudents2;
    }

    /**
     * @param selectedStudents2 the selectedStudents2 to set
     */
    public void setSelectedStudents2(List<String> selectedStudents2) {
        this.selectedStudents2 = selectedStudents2;
    }

    /**
     * add student to the classList
     */
    public void addStudentToClass() {

        for (Iterator<String> i = selectedStudents.iterator(); i.hasNext();) {

            StudentModel sm = new StudentModel();
            int id = Integer.parseInt(i.next());

            sm = sm.getStudentById(id);
            studentList2.add(sm);

            for (Iterator<StudentModel> j = studentList.iterator(); j.hasNext();) {
                if (j.next().getUserid() == id) {
                    j.remove();
                }
            }

        }

    }

    /**
     * remove student from the class list
     */
    public void removeStudentFromClass() {

        for (Iterator<String> i = selectedStudents2.iterator(); i.hasNext();) {

            StudentModel sm = new StudentModel();
            int id = Integer.parseInt(i.next());

            sm = sm.getStudentById(id);
            studentList.add(sm);

            for (Iterator<StudentModel> j = studentList2.iterator(); j.hasNext();) {
                if (j.next().getUserid() == id) {
                    j.remove();
                }
            }

        }

    }

    /**
     * add all student in the list to the class
     */
    public void addAll() {

        for (Iterator<StudentModel> i = studentList.iterator(); i.hasNext();) {

            studentList2.add(i.next());
            i.remove();

        }

    }

    public void removeAll() {
        for (Iterator<StudentModel> i = studentList2.iterator(); i.hasNext();) {

            studentList.add(i.next());
            i.remove();

        }
    }

    /**
     * save information for student entered manually
     *
     * @return
     */
    public String submitM() {

        if (!studentListManual.isEmpty()) {

            for (StudentModel s : studentListManual) {
                String username = generateUsername(s.getFname(), s.getLname());
                String Password = passwordGenerator();
                s.setIs_a('s');
                s.setUserid(s.saveUser());

                //creating user account and setting role
                UserLogin ul = new UserLogin(username, Password, s.getUserid());
                ul.createLogin();

                UserLoginRole ulr = new UserLoginRole(username, "student");
                ulr.setUserRole();

                //adding student to the room
                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(ocm.getOnlineClassId());
                rpm.setStudentId(s.getUserid());
                rpm.addParticipant();

                //Generating email
                // Email email = new Email();
                if (s.getEmail() != null) {

                    //email.studentEnrollMail(s.getFname(),username,Password, s.getEmail(), ocm.getTitle());
                    sendMessage(s.getFname(), username, Password, s.getEmail(), ocm.getTitle());

                }

            }

        }

        studentListManual.clear();

        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        int id = ocm.getTeacherId();

        return "/protected/onlineClasses.xhtml?faces-redirect=true&userId=" + id;
    }

    private void initStudentManualList() {
        studentListManual = new ArrayList<>();
        StudentModel aModel = new StudentModel();
        getStudentListManual().add(aModel);
    }

    public void extend() {
        StudentModel aModel = new StudentModel();
        getStudentListManual().add(aModel);
    }

    public void unExtend() {

        int lastIndex = studentListManual.size() - 1;
        if (lastIndex != 0) {
            studentListManual.remove(lastIndex);
        }
    }

    /**
     * @return the studentListManual
     */
    public List<StudentModel> getStudentListManual() {
        return studentListManual;
    }

    /**
     * @param studentListManual the studentListManual to set
     */
    public void setStudentListManual(List<StudentModel> studentListManual) {
        this.studentListManual = studentListManual;
    }

    /**
     * Saving information using the pre-existing student list
     *
     * @return
     */
    public String save() {

        if (!studentList2.isEmpty()) {

            for (StudentModel sm : studentList2) {
                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(ocm.getOnlineClassId());
                rpm.setStudentId(sm.getUserid());
                rpm.addParticipant();

                //get the username and password
                UserLogin ul = new UserLogin();
                ul.setUserId(sm.getUserid());
                ul = ul.getLoginById();

                if (sm.getEmail() != null) {
                    sendMessage(sm.getFname(), ul.getUsername(), ul.getPassword(), sm.getEmail(), ocm.getTitle());
                }
            }

        }

        studentList.clear();
        studentList2.clear();

        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        int id = ocm.getTeacherId();

        return "/protected/onlineClasses.xhtml?faces-redirect=true&userId=" + id;

    }

    /**
     * Create user using an excel sheet
     *
     * @return
     */
    public String submitE() {

        try {
            String result = "";
            InputStream input = file.getInputstream();

            HSSFWorkbook workbook = new HSSFWorkbook(input);
            HSSFSheet worksheet = workbook.getSheetAt(0);

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = worksheet.rowIterator();

            while (rowIterator.hasNext()) {
                int id = 0;
                StudentModel sm = new StudentModel();
                UserLogin ul = new UserLogin();

                Row row = rowIterator.next();

                Cell cellA1 = row.getCell(0);
                String a1Val = cellA1.getStringCellValue();//getting the firstname
                sm.setFname(a1Val);

                Cell cellB1 = row.getCell(1);
                String b1Val = cellB1.getStringCellValue();//getting the lastname
                sm.setLname(b1Val);

                Cell cellC1 = row.getCell(2);
                int c1Val = (int) cellC1.getNumericCellValue();//gettting the uid

                Cell cellF1 = row.getCell(5);
                String F1Val = cellF1.getStringCellValue();//getting the major
                sm.setMajor(F1Val);
                sm.setIs_a('s');

                Cell cellE1 = row.getCell(7);
                String E1Val = cellE1.getStringCellValue();//getting the email address
                sm.setEmail(E1Val);

                id = sm.saveUser();

                Cell cellD1 = row.getCell(3);
                String d1Val = cellD1.getStringCellValue();//getting the username

                ul.setUsername(d1Val);
                ul.setPassword(passwordGenerator());
                ul.setUserId(id);
                ul.createLogin();//creating the user login

                UserLoginRole ulr = new UserLoginRole(d1Val, "student");
                ulr.setUserRole();//creating the role

                rpm = new RoomParticipantModel();
                rpm.setOnlineClassId(ocm.getOnlineClassId());
                rpm.setStudentId(id);
                rpm.addParticipant();

                result = result + a1Val + " | " + b1Val + " | " + c1Val + " | " + d1Val + " | " + F1Val + "\n";

                System.out.println(result);

                //Generating email
                //Email email = new Email();
                if (sm.getEmail() != null) {

                    // email.studentEnrollMail(sm.getFname(),d1Val,ul.getPassword(), sm.getEmail(), ocm.getTitle());
                    sendMessage(sm.getFname(), d1Val, ul.getPassword(), sm.getEmail(), ocm.getTitle());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RoomParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int id = ocm.getTeacherId();

        return "/protected/onlineClasses.xhtml?faces-redirect=true&userId=" + id;
    }

    /**
     * generates password for users
     *
     * @return
     */
    public String passwordGenerator() {

        String password = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String alphabetUperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeric = "0123456789";
        String specialCharacter = "!@#$_";
        String passwordPool = alphabet + alphabetUperCase + numeric + specialCharacter;
        int length = passwordPool.length();

        Random generator = new Random();

        for (int i = 0; i < 9; i++) {
            password = password + passwordPool.charAt(generator.nextInt(length));
        }

        return password;

    }

    /**
     * Generate username when student are insert Manually
     *
     * @param firstName
     * @param lastName
     * @return
     */
    public String generateUsername(String firstName, String lastName) {

        String usernameGenerate = "";
        int length = lastName.length();
        String password2part = "";

        if (length < 6) {
            password2part = lastName;
        } else {
            password2part = lastName.substring(0, 6);
        }

        usernameGenerate = firstName.charAt(0) + password2part;

        return usernameGenerate;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @Async
    private void sendMessage(String fname, String username, String password, String emailAddress, String classTitle) {
        Email email = new Email();
        email.studentEnrollMail(fname, username, password, emailAddress, classTitle);

    }

}
