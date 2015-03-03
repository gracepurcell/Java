package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Event> events;
    EventTableGateway gateway;

    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new EventTableGateway(conn);
            
            this.events = gateway.getEvents();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addEvent(Event e){
        boolean result= false;
        try{
            int id = this.gateway.insertEvent(e.getDate(), e.getTime(), e.getTitle(), e.getAttending(), e.getPrice(), e.getAddress(), e.getEventManager() );
            if (id != -1){
                e.setId(id);
                this.events.add(e);
                result = true;
            }
        } 
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return result;
    }   
        

    public List<Event> getEvents() {
        return new ArrayList<Event>(this.events);
    }
    
    public boolean removeEvent(Event e) {
        boolean removed = false;
        
        try{
            removed = this.gateway.deleteEvent(e.getId());
            if (removed){
                removed = this.events.remove(e);
            }
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    public Event findEventByTitle(String title) {
        Event e = null;
        int i = 0;
        boolean found = false;
        while (i < this.events.size() && !found) {
            e = this.events.get(i);
            if (e.getTitle().equalsIgnoreCase(title)) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            e = null;
        }
        return e;
    }

    Event findEventByTile(String title) {
        Event e = null;
        int i = 0;
        boolean found = false;
        while (i < this.events.size() && !found){
            e = this.events.get(i);
            if(e.getTitle() == title){
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found){
            e = null;
        }
        return e;
    }

    boolean updateEvent(Event e) {
        boolean updated = false;
        
        try{
            updated = this.gateway.updateEvent(e);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return updated;
    }

    Event findEventById(int id) {
        Event e = null;
        int i = 0;
        boolean found = false;
        while (i < this.events.size() && !found){
            e = this.events.get(i);
            if(e.getId() == id){
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found){
            e = null;
        }
        return e;
    }
}
