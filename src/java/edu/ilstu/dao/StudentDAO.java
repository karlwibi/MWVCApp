/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.StudentModel;
import edu.ilstu.model.UserModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface StudentDAO {
    
    public int createStudent(StudentModel aStudent);
   
    public void upadteStudent(StudentModel aStudent);
    
    public ArrayList<StudentModel> getStudents();
    
    public StudentModel findStudentById(int userid);
    
    public void deleteStudent(StudentModel aStudent);
}
