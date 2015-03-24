package com.example.app.model;

public class Event {
    
    private int id;
    private String date;
    private String time;
    private String title;
    private String attending;
    private String address;
    private double price;
    private int eventManagerId;

    public Event(int id, String d, String tm, String tt, String at, String ad, double p, int eventManagerId) {
        this.id = id;
        this.date = d;
        this.time = tm;
        this.title = tt;
        this.attending = at;
        this.address = ad;
        this.price = p;
        this.eventManagerId = eventManagerId;
    }
    

    public Event(String d, String tm, String tt, String at, String ad, double p, int eventManagerId){
        this(-1, d, tm, tt, at, ad, p, eventManagerId);
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
    
    public double getPrice(){
        return price; 
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public int getEventManagerId(){
        return eventManagerId; 
    }
    
    public void setEventManagerId(int eventManagerId){
        this.eventManagerId = eventManagerId;
    }
    
    
}
