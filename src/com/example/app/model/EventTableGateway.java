package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class EventTableGateway {
    
    private static final String TABLE_NAME = "event";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ATTENDING = "attending";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EVENTMANAGER = "eventManager";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_EVENTMANAGER_ID = "ID";
    
    private Connection mConnection;

    public EventTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public int insertEvent(String d, String tm, String tt, String at, String ad, double p, int em ) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
    
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_DATE + ", " +
                COLUMN_TIME + ", " +
                COLUMN_TITLE + ", " +
                COLUMN_ATTENDING + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_EVENTMANAGER_ID + " " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        stmt = mConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, d);
        stmt.setString(2, tm);
        stmt.setString(3, tt);
        stmt.setString(4, at);
        stmt.setString(5, ad);
        stmt.setDouble(6, p);
        stmt.setInt(7, em);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1){
            
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
         
        return id;
        
    }
    
    public boolean deleteEvent(int id) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }

    public List<Event> getEvents() throws SQLException {
        String query;       // the SQL query to execute
        Statement stmt;     // the java.sql.Statement object used to execute the
                            // SQL query
        ResultSet rs;       // the java.sql.ResultSet representing the result of
                            // SQL query 
        List<Event> events;   // the java.util.List containing the Programmer objects
                            // created for each row in the result of the query
        String title, date, time, attending, address;
        double price;
        int id, eventManagerId;
        Event e;       // a Event object created from a row in the result of
                            // the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Programmer object, which is inserted into an initially
        // empty ArrayList
        events = new ArrayList<Event>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            date = rs.getString(COLUMN_DATE);
            time = rs.getString(COLUMN_TIME);
            title = rs.getString(COLUMN_TITLE);
            attending = rs.getString(COLUMN_ATTENDING);
            address = rs.getString(COLUMN_ADDRESS);
            price = rs.getDouble(COLUMN_PRICE);
            eventManagerId = rs.getInt(COLUMN_EVENTMANAGER_ID);

            e = new Event(id, date, time, title, attending, address, price, eventManagerId);
            events.add(e);
        }

        return events;    
    }

    boolean updateEvent(Event e) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_DATE         + " = ?, " + 
                COLUMN_TIME         + " = ?, " + 
                COLUMN_TITLE        + " = ?, " + 
                COLUMN_ATTENDING    + " = ?, " + 
                COLUMN_ADDRESS      + " = ?, " + 
                COLUMN_PRICE        + " = ?, " + 
                COLUMN_EVENTMANAGER + " = ? " + 
                " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, e.getDate());
        stmt.setString(2, e.getTime());
        stmt.setString(3, e.getTitle());
        stmt.setString(4, e.getAttending());
        stmt.setString(5, e.getAddress());
        stmt.setDouble(6, e.getPrice());
        stmt.setInt(7, e.getEventManagerId());
        stmt.setInt(8, e.getId());
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
        
        
        
                
           
    }

    


    
}
