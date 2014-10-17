/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ilstu.dao;

import edu.ilstu.model.OnlineClassModel;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public interface OnlineClassDAO {

    /**
     *
     * @param onlineClass
     * @return
     */
    public int createOnlineClass(OnlineClassModel onlineClass);

    /**
     *
     * @param onlineClassId
     * @return
     */
    public OnlineClassModel getfindOnlineClassById(int onlineClassId);

    /**
     *
     * @return a List of OnlineClass object
     */
    public ArrayList<OnlineClassModel> getOnliceClasses();

    public ArrayList<OnlineClassModel> getOnliceClassesByTeacherId(int teacherId);

    /**
     *
     * @param onlineClass
     */
    public void updateOnlineClass(OnlineClassModel onlineClass);

    /**
     *
     * @param onlineClass
     */
    public void deleteOnlineClass(OnlineClassModel onlineClass);

    /**
     *
     * @param roomId
     * @return
     */
    public OnlineClassModel findByRoomId(int roomId);
}
