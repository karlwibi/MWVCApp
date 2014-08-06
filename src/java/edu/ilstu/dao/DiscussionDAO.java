/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.DiscussionModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface DiscussionDAO {
    
    
    public int createDiscussion(DiscussionModel discussion);
    
    public void updateDiscussion (DiscussionModel discussion);
    
    public DiscussionModel findDiscussionsbyId(int discussionid);
    
    public ArrayList<DiscussionModel> findDiscussionbyOnlineClass(int onlinecalssid);
    
    public ArrayList<DiscussionModel> findDiscussionByOwner(int teacherId);
    
    public ArrayList<DiscussionModel> getDiscussions();
    
    public void deleteDiscussion(DiscussionModel discussion);
    
    public DiscussionModel findDiscussionsByTitle(String title);
    
    public ArrayList<DiscussionModel> findDiscussionsByDescriptionKeyWord(String keyWord);
    
    
    
    
    
    
}
