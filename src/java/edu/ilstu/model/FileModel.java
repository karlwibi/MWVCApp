/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.model;

import java.io.Serializable;

/**
 *
 * @author kawibi
 */
public class FileModel implements Serializable {
    

private int fileId;
private String fileName;
private int Size;
private int postedBy;
private String location;

public FileModel(){}

public FileModel(String fileName, int postedBy, String location){
    this.fileName=fileName;
    this.postedBy=postedBy;
    this.location=location;
}

public FileModel(String fileName, int postedBy, String location, int size, int fileId){
    this(fileName,postedBy,location);
    this.Size=size;
    this.fileId=fileId;
    
}

    /**
     * @return the fileId
     */
    public int getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the Size
     */
    public int getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(int Size) {
        this.Size = Size;
    }

    /**
     * @return the postedBy
     */
    public int getPostedBy() {
        return postedBy;
    }

    /**
     * @param postedBy the postedBy to set
     */
    public void setPostedBy(int postedBy) {
        this.postedBy = postedBy;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }



}


