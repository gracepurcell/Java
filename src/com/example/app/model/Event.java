package com.example.app.model;

public class Event {
    
    private int id;
    private String date;
    private String time;
    private String title;
    private String attending;
    private String address;
    private String eventManager;
    private double price;

    public Event(int id, String d, String tm, String tt, String at, String ad, String em, double p) {
        this.id = id;
        this.date = d;
        this.time = tm;
        this.title = tt;
        this.attending = at;
        this.address = ad;
        this.eventManager = em;  
        this.price = p;
    }
    

    public Event(String d, String tm, String tt, String at, String ad, String em, double p){
        this(-1, d, tm, tt, at, ad, em, p);
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id; 
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getAttending(){
        return attending;
    }
    
    public void setAttending(String attending){
        this.attending = attending;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getEventManager(){
        return eventManager;
    }
    
    public void setEventManager(String eventManager){
        this.eventManager = eventManager;
    }
    
    public double getPrice(){
        return price; 
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    
}
