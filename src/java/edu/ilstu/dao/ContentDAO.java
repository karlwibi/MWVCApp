/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ContentModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ContentDAO {
 
    
    public int createContent(ContentModel content);
   
    public void updateContent(ContentModel content);
    
    public ArrayList<ContentModel> getAllContents();
    
    public ArrayList<ContentModel> getContentsByRevealId(int revealId);
    
    public void deleteContent(ContentModel content);
    
        
    
    
}
