/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.helper;

/**
 *
 * @author Admin
 */
public class Property {
    
    private static String savePath="/ProjectRTC/home/ubuntu/var/onlineClass_";
    private static String webRtcURL="";

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
    
    
    
    
    
}
