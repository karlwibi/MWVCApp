/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.FileModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface FileDAO {
    
    
    public int createFile(FileModel file);
    
    public void updateFile(FileModel file);
    
    public void deleteFile(FileModel File);
    
    public ArrayList<FileModel> getFileByuseid(int userId);
    
    public FileModel getFilebyFileId(int fileId);
    
    public FileModel getFilebyName(String name);
    
    
    
    
    
    
    
    
    
}
