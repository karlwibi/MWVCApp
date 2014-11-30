/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class Property {

//    private static String savePath="/ProjectRTC/home/ubuntu/var/onlineClass_";
//    private static String applicationLink="http://localhost:8181/MWVCApp/";
//    private static String webRtcURL="http://localhost:9001/";
//    private static String smtpServer="smtp.gmail.com";
//    private static String smtpPort="587";
//    private static String smtpUsername="ridma.reutar@gmail.com";
//    private static String smtpPassword="Ar1q2w3e";
    private static String savePath;
    private static String applicationLink;
    private static String webRtcURL;
    private static String smtpServer;
    private static String smtpPort;
    private static String smtpUsername;
    private static String smtpPassword;

    /**
     * @return the savePath
     */
    public static String getSavePath() {
        return savePath;
    }

    /**
     * @param aSavePath the savePath to set
     */
    public static void setSavePath(String aSavePath) {
        savePath = aSavePath;
    }

    /**
     * @return the webRtcURL
     */
    public static String getWebRtcURL() {
        return webRtcURL;
    }

    /**
     * @param aWebRtcURL the webRtcURL to set
     */
    public static void setWebRtcURL(String aWebRtcURL) {
        webRtcURL = aWebRtcURL;
    }

    /**
     * @return the smtpServer
     */
    public static String getSmtpServer() {
        return smtpServer;
    }

    /**
     * @param aSmtpServer the smtpServer to set
     */
    public static void setSmtpServer(String aSmtpServer) {
        smtpServer = aSmtpServer;
    }

    /**
     * @return the smtpPort
     */
    public static String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param aSmtpPort the smtpPort to set
     */
    public static void setSmtpPort(String aSmtpPort) {
        smtpPort = aSmtpPort;
    }

    /**
     * @return the smtpUsername
     */
    public static String getSmtpUsername() {
        return smtpUsername;
    }

    /**
     * @param aSmtpUsername the smtpUsername to set
     */
    public static void setSmtpUsername(String aSmtpUsername) {
        smtpUsername = aSmtpUsername;
    }

    /**
     * @return the smtpPassword
     */
    public static String getSmtpPassword() {
        return smtpPassword;
    }

    /**
     * @param aSmtpPassword the smtpPassword to set
     */
    public static void setSmtpPassword(String aSmtpPassword) {
        smtpPassword = aSmtpPassword;
    }

    /**
     * @return the applicationLink
     */
    public static String getApplicationLink() {
        return applicationLink;
    }

    /**
     * @param aApplicationLink the applicationLink to set
     */
    public static void setApplicationLink(String aApplicationLink) {
        applicationLink = aApplicationLink;
    }

    public static void uploadWebRTCAppInfo() {

        File propertyDir = new File("/ProjectRTC/home/ubuntu/var/", "property");
//         File propertyDir = new File("/home/ubuntu/ProjectRTC/home/ubuntu/var/", "property");

        File file = new File(propertyDir, "webrtc.properties");

        Properties webRTCProperties = new Properties();

        try { 
            webRTCProperties.load(new FileInputStream(file));
        } catch (IOException ex) {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

        savePath = webRTCProperties.getProperty("savePath");
        applicationLink = webRTCProperties.getProperty("applicationLink");
        webRtcURL = webRTCProperties.getProperty("webRtcURL");
        smtpServer = webRTCProperties.getProperty("smtpServer");
        smtpPort = webRTCProperties.getProperty("smtpPort");
        smtpUsername = webRTCProperties.getProperty("smtpUsername");
        smtpPassword = webRTCProperties.getProperty("smtpPassword");

    }

    public static void saveWebRTCAppInfo() {

        File propertyDir = new File("/ProjectRTC/home/ubuntu/var/", "property");
//        File propertyDir = new File("/home/ubuntu/ProjectRTC/home/ubuntu/var/", "property");

        if (!(propertyDir.exists())) {
            boolean Success = propertyDir.mkdirs();
        }

        File file = new File(propertyDir, "webrtc.properties");

        Properties webRTCProperties = new Properties();

        System.out.println("saving info into properties "+webRtcURL);
        
        webRTCProperties.put("savePath", savePath);
        webRTCProperties.put("applicationLink", applicationLink);
        webRTCProperties.put("webRtcURL", webRtcURL);
        webRTCProperties.put("smtpServer", smtpServer);
        webRTCProperties.put("smtpPort", smtpPort);
        webRTCProperties.put("smtpUsername", smtpUsername);
        webRTCProperties.put("smtpPassword", smtpPassword);

        try {
            webRTCProperties.store(new FileOutputStream(file), "WebRTC properties");
        } catch (IOException ex) {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
