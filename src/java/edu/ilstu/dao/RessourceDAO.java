/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.RessourceModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface RessourceDAO {

    public int createRessource(RessourceModel ressourceModel);

    public void deleteRessource(RessourceModel ressourceModel);

     

}
