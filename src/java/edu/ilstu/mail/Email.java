/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.mail;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import edu.ilstu.controller.RoomParticipant;
import edu.ilstu.model.OnlineClassModel;
import edu.ilstu.model.RoomParticipantModel;
import edu.ilstu.model.StudentModel;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;


/**
 *
 * @author it3530126
 */
public class Email {

    private String to;
    private String from = "kwbisse@ilstu.edu";
    private String message;
    private String messageType;
    private Properties properties;
    private String HOST = "smtp.gmail.com"; // "smtp.ilstu.edu"
    private int onlineClassId;
    private int sessionId;
    private Session session;

    public Email() {
        
         final String username="ridma.reutar@gmail.com";
         final String password="Ar1q2w3e";
         
        properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", "587");
        
        
       // properties.setProperty("mail.user", "yourID"); // if needed
       // properties.setProperty("mail.password", "yourPassword"); // if needed
        
        // Get the default Session object.
        //session = Session.getDefaultInstance(properties);
         session= Session.getInstance(properties, new Authenticator(){
            @Override
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            } 
         });


    }

    public Email(String to, String from, String messageType, String message) {

        this();
        this.to = to;
        this.from = from;
        this.message = message;
        this.messageType = messageType;
    }

    public String onlineClassCreateMessage() {
        OnlineClassModel ocm=null;
        Address[] recipientAddresses = null;

        String status = null;
       
        //setting the student email from the list 
        
            //get all the information for the class 
            RoomParticipantModel participant = new RoomParticipantModel();
            participant.setOnlineClassId(onlineClassId);
            //getting the room participant info
            List<RoomParticipantModel> participantList = new ArrayList();
            participantList = participant.getAllParticipantForRoom();
            //intializing the Internet AddressArray 
            recipientAddresses = new InternetAddress[participantList.size()];
//adding the student email address to the recipientaddress queue
            int i=0;
            for (RoomParticipantModel aPartcipant : participantList) {
                int studentId = aPartcipant.getStudentId();
                StudentModel aStudent = new StudentModel();
                //getting the student information
                aStudent = aStudent.getStudentById(studentId);
                //adding to the recipientAddresses Array variable
                try {
                    recipientAddresses[i]=new InternetAddress(aStudent.getEmail());
                } catch (AddressException ex) {
                    Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
            
            sendMail(getSession(), recipientAddresses);

        
            
         

     
        return status;
    }

    public String sessionCreateMessage() {
        String status = null;
        // Get the default Session object.
        setSession(Session.getDefaultInstance(getProperties()));

        return status;
    }

    
    public void sendMail(Session session, Address[] recipientAddresses){
        
        OnlineClassModel ocm=null;
        

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(getFrom()));

            // Set To: header field of the header.
            message.addRecipients(Message.RecipientType.TO,
                    recipientAddresses);

            
            
             // Set Subject: header field
            ocm=new OnlineClassModel();
            ocm.setOnlineClassId(onlineClassId);
            ocm=ocm.getAClass();
            message.setSubject("Access granted to the Online class: "+ocm.getTitle());

            String path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/emailTemplate/email.html");
            
            //File source=new File(path+"/resources/emailTemplate/email.html");
            
            InputStream in = new FileInputStream(path);
            in = new BufferedInputStream(in);

            
            StringWriter writer= new StringWriter();
            IOUtils.copy(in, writer);
            // Send the actual HTML message, as big as you like
            
            String emailMessage = writer.toString();
            String escapedText= StringEscapeUtils.escapeHtml4(emailMessage);
            escapedText=String.format(escapedText, ocm.getTitle());
            emailMessage=StringEscapeUtils.unescapeHtml4(escapedText);
            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailMessage, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            //DEALING WITH THE IMAGE
            MimeBodyPart imagePart = new MimeBodyPart();
//             String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/"+land.getPicture().getPicturePath());
//             FileDataSource fileDataSource = new FileDataSource(path) {
//                @Override
//                public String getContentType() {
//                    return "application/octet-stream";
//                }
//            };
//              DataSource fds = new FileDataSource(path);
//            imagePart.setDataHandler(new DataHandler(fds));
            imagePart.addHeader("Content-ID", "<image>");
            imagePart.addHeader("Content-ID", "<logo>");

            //putting everything together
//            multipart.addBodyPart(imagePart);

            // Send message
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @return the HOST
     */
    public String getHOST() {
        return HOST;
    }

    /**
     * @param HOST the HOST to set
     */
    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    /**
     * @return the onlineClassId
     */
    public int getOnlineClassId() {
        return onlineClassId;
    }

    /**
     * @param onlineClassId the onlineClassId to set
     */
    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
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
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

}
