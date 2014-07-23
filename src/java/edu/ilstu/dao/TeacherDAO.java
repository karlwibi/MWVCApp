/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.TeacherModel;
import edu.ilstu.model.UserModel;
import java.util.ArrayList;
/**
 *
 * @author kawibi
 */
public interface TeacherDAO {
    
    public int createTeacher(TeacherModel aTeacher);
    
    public void updateTeacher(TeacherModel aTeacher);
    
    public UserModel findTeacherById(int userId);
    
    public ArrayList<UserModel> getTeachers();
    
    public void deleteTeacher(TeacherModel aTeacher);
    
}
