/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.RessourceModel;
import edu.ilstu.model.StudyToolModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface StudyToolDAO {
    
    public int createStudyRessource(StudyToolModel ressource);
    
    public void updateStudyRessource(StudyToolModel ressource);
    
    public void deleteStudyRessource(StudyToolModel ressource);
    
    public StudyToolModel getRessourceById(int ressourceId);
    
    public ArrayList<StudyToolModel> getStudytoolRessourceByOnlinceClasseId(int onlinceClassId);
    
    
    
}
