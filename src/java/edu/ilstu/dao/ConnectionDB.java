/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author kawibi
 */
public class ConnectionDB {
   
    private static Connection dbConnect;
   
    @Resource(name="jdbc/webrtc")
    private static DataSource ds;
    
     private static ConnectionDB instance;
    
    private ConnectionDB(){
    
        try{
            dbConnect=ds.getConnection();
        }catch(SQLException e){
            
            System.err.println(e.getMessage());
        }
    }
    
//    private static class Connectionhelper{
//         private static final ConnectionDB instance= new ConnectionDB();
//    }
    
    public static ConnectionDB getInstance(){
        
        if (instance==null){
            synchronized( ConnectionDB.class){
                
                if(instance==null){
                    instance=new ConnectionDB();
                }
            }
        }
        
        return instance;
    }
    
    public static Connection getConnInst(){
        
        
            try{
                 Context initContext = new InitialContext();
                 Context envContext=(Context) initContext.lookup("java:/comp/env");
                 ds=(DataSource) envContext.lookup("jdbc/webrtc");
                 try{
                     dbConnect=ds.getConnection();
                 }catch(SQLException e){
                    System.err.println(e.getMessage());
                 }
            }catch(NamingException e){
              System.err.println(e.getMessage());
            }
           
            
        
        return dbConnect;
    }
}
