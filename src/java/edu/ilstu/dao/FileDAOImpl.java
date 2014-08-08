/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ilstu.dao;

import edu.ilstu.model.FileModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author kawibi
 */
public class FileDAOImpl implements FileDAO {

    @Override
    public int createFile(FileModel file) {
       
        int i = 1;
        int identity = 0;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("INSERT INTO File (filename,size,postedby,location)"
                    + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            p.setString(i++, file.getFileName());
            p.setInt(i++, file.getSize());
            p.setInt(i++, file.getPostedBy());
            p.setString(i++, file.getLocation());

            p.executeUpdate();

            rs = p.getGeneratedKeys();
            rs.next();
            identity = rs.getInt(1);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return identity;
    }

    @Override
    public void updateFile(FileModel file) {
        
        int i = 1;
        
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("UPDATE File "
                    + "SET filename=?,"
                    + "size=?,"
                    + "postedby=?,"
                    + "location=?"
                    + "WHERE fileid=?");

            p.setString(i++, file.getFileName());
            p.setInt(i++, file.getSize());
            p.setInt(i++, file.getPostedBy());
            p.setString(i++, file.getLocation());
            p.setInt(i++, file.getFileId());

            p.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        
    }

    @Override
    public void deleteFile(FileModel File) {
              int i = 1;
        
        Connection con = ConnectionDB.getConnInst();
        
        try {

            PreparedStatement p = con.prepareStatement("DELETE FROM File "
                    + "WHERE fileid=?");

            
            p.setInt(i++, File.getFileId());

            p.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    @Override
    public ArrayList<FileModel> getFileByuseid(int userId) {
        int i=1;
        ArrayList<FileModel> list = new ArrayList<>();
        FileModel fileModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM file WHERE postedby=?");

            p.setInt(i++, userId );
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                fileModel = new FileModel();
                fileModel.setFileId(rs.getInt(j++));
                fileModel.setFileName(rs.getString(j++));
                fileModel.setSize(rs.getInt(j++));
                fileModel.setPostedBy(rs.getInt(j++));
                fileModel.setLocation(rs.getString(j++));

                list.add(fileModel);

            }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return list;
    }

    @Override
    public FileModel getFilebyFileId(int fileId) {
         int i=1;
        
        FileModel fileModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM file WHERE fileid=?");

            p.setInt(i++, fileId );
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                fileModel = new FileModel();
                fileModel.setFileId(rs.getInt(j++));
                fileModel.setFileName(rs.getString(j++));
                fileModel.setSize(rs.getInt(j++));
                fileModel.setPostedBy(rs.getInt(j++));
                fileModel.setLocation(rs.getString(j++));

                

            }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return fileModel;
    }

    @Override
    public FileModel getFilebyName(String name) {
       int i=1;
        
        FileModel fileModel = null;
        ResultSet rs = null;
        Connection con = ConnectionDB.getConnInst();

        try {

            PreparedStatement p = con.prepareStatement("SELECT * FROM file WHERE filename=?");

            p.setString(i++, name);
            
            rs = p.executeQuery();

            while (rs.next()) {
                int j = 1;
                fileModel = new FileModel();
                fileModel.setFileId(rs.getInt(j++));
                fileModel.setFileName(rs.getString(j++));
                fileModel.setSize(rs.getInt(j++));
                fileModel.setPostedBy(rs.getInt(j++));
                fileModel.setLocation(rs.getString(j++));

                

            }

            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return fileModel;
    }
    
}
