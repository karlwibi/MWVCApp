/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.ClassSessionModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface ClassSessionDAO {
 
    
    public int createSession(ClassSessionModel session);
    
    public void updateSession(ClassSessionModel session);
    
    public void deleteSession(ClassSessionModel session);
    
    public ClassSessionModel getASession(int sessionId);
    
    public ArrayList<ClassSessionModel> getSessionsByScheduleId(int scheduleId);
    
    public ArrayList<ClassSessionModel> getAllSessions();
    
    
}
