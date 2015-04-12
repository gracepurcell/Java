package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() throws DataAccessException {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    private List<Manager> eventmanager;
    ManagerTableGateway managergateway;
    private List<Event> events;
    EventTableGateway gateway;
    

    private Model() throws DataAccessException {

        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new EventTableGateway(conn);
            this.managergateway = new ManagerTableGateway(conn);
            
            this.eventmanager = this.managergateway.getManagers();
            this.events = this.gateway.getEvents();
        } 
        catch (ClassNotFoundException ex) {
            throw new DataAccessException("Exeption initialising Model object: " + ex.getMessage());
        } 
        catch (SQLException ex) {
            throw new DataAccessException("Exeption initialising Model object: " + ex.getMessage());
        }
       
    }
    
    public boolean addEvent(Event e) throws DataAccessException{
        boolean result= false;
        try{
            int id = this.gateway.insertEvent(e.getDate(), e.getTime(), e.getTitle(), e.getAttending(), e.getAddress(), e.getPrice(), e.getEventManagerId() );
            if (id != -1){
                e.setId(id);
                this.events.add(e);
                result = true;
            }
        } 
        catch (SQLException ex){
            throw new DataAccessException("Exeption adding event: " + ex.getMessage());
        }  
        return result;
    }  
    
    public boolean addManager (Manager ma) throws DataAccessException{
        boolean result = false;
        try{
            int id = this.managergateway.insertManager(ma.getName(), ma.getEmail(), ma.getManaddress(), ma.getPhone() );
            if (id != -1){
                ma.setId(id);
                this.eventmanager.add(ma);
                result = true;
            }
        }
        catch (SQLException ex){
            throw new DataAccessException("Exeption adding manager: " + ex.getMessage());
        }
        return result;
    }
        

    public List<Event> getEvents() {
        return new ArrayList<Event>(this.events);
    }
    
    public List<Manager> getManagers(){
        return new ArrayList<Manager>(this.eventmanager);
    }
    
    public boolean removeEvent(Event e) throws DataAccessException {
        boolean removed = false;
        
        try{
            removed = this.gateway.deleteEvent(e.getId());
            if (removed){
                removed = this.events.remove(e);
            }
        }
        catch (SQLException ex){
            throw new DataAccessException("Exeption removing Event: " + ex.getMessage());
        }
        return removed;
    }
    
    public boolean removeManager(Manager m) throws DataAccessException {
        boolean removed = false;
        
        try{
            removed = this.managergateway.deleteManager(m.getId());
            if (removed){
                removed = this.eventmanager.remove(m);
            }
        }
        catch (SQLException ex){
            throw new DataAccessException("Exeption removing event: " + ex.getMessage());
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
    
    public Manager findManagerByName(String name) {
        Manager m = null;
        int i = 0;
        boolean found = false;
        while (i < this.eventmanager.size() && !found) {
            m = this.eventmanager.get(i);
            if (m.getName().equalsIgnoreCase(name)) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            m = null;
        }
        return m;
    }

    boolean updateEvent(Event e) throws DataAccessException {
        boolean updated = false;
        
        try{
            updated = this.gateway.updateEvent(e);
        }
        catch (SQLException ex){
            throw new DataAccessException("Exeption updating event: " + ex.getMessage());
        }
        
        return updated;
    }
    
    boolean updateManager(Manager m) throws DataAccessException {
        boolean updated = false;
        
        try{
            updated = this.managergateway.updateManager(m);
        }
        catch (SQLException ex){
            throw new DataAccessException("Exeption updating manager: " + ex.getMessage());
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
    
    Manager findManagerById(int id) {
        Manager m = null;
        int i = 0;
        boolean found = false;
        while (i < this.eventmanager.size() && !found){
            m = this.eventmanager.get(i);
            if(m.getId() == id){
                found = true;
            }
            else {
                i++;
            }
        }
        if (!found){
            m = null;
        }
        return m;
    }
}
