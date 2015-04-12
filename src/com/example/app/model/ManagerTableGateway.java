package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerTableGateway {
    
    private static final String TABLE_NAME = "eventmanager";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EMAIL = "email";


    private Connection mConnection;

    public ManagerTableGateway(Connection connection){
        mConnection = connection;
    }
    
    public int insertManager(String n, String ph, String ma, String ema) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_EMAIL +
                
                ") VALUES (?, ?, ?, ?)";
        
        
        stmt = mConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(4, ph);
        stmt.setString(3, ma);
        stmt.setString(2, ema);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1){
            
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        return id;
    }
    
    public boolean deleteManager(int id) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt (1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }
    
    public List<Manager> getManagers() throws SQLException{
        String query;
        Statement stmt;
        ResultSet rs;
        List<Manager> eventmanagers;
        String name, phone, manaddress, email;
        int id;
        Manager m;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        eventmanagers = new ArrayList<Manager>();
        while (rs.next()){
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            phone = rs.getString(COLUMN_PHONE);
            manaddress = rs.getString(COLUMN_ADDRESS);
            email = rs.getString(COLUMN_EMAIL);
            

            m = new Manager (id, name, phone, manaddress, email);
            eventmanagers.add(m);
        }
        
        return eventmanagers;
    } 
    
    boolean updateManager(Manager m) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME + " = ?, " + 
                COLUMN_PHONE + " = ?, " + 
                COLUMN_ADDRESS + " = ?, " + 
                COLUMN_EMAIL + " = ? " +
                
                
                " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, m.getId());
        stmt.setString(2, m.getName());
        stmt.setString(3, m.getPhone());
        stmt.setString(4, m.getManaddress());
        stmt.setString(5, m.getEmail());
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }
    
}