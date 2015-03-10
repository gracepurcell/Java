package com.example.app.model;

public class Manager {
    
    private int id;
    private String name;
    private String email;
    private String manaddress;
    private String phone;
    
    public Manager(int id, String n, String ema, String ma, String ph){
        this.id = id;
        this.name = name;
        this.email = email;
        this.manaddress = manaddress;
        this.phone = phone;
    }
    
    public Manager(String n, String ema, String ma, String ph){
        this(-1, n, ema, ma, ph);
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getManaddress(){
        return manaddress;
    }
    
    public void setManaddress(String manaddress){
        this.manaddress = manaddress;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }



   
}
