package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DemoApp {
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        
        Model model = Model.getInstance();
        

        
        int opt;
        do {
            System.out.println("1. Create new Event");
            System.out.println("2. Delete existing Event");
            System.out.println("3. Edit existing Event");
            System.out.println("4. View all Events");
            System.out.println("5. Create new Manager");
            System.out.println("6. Delete existing Manager");
            System.out.println("7. Edit existing Manager");
            System.out.println("8. View all Managers");
            System.out.println("9. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

            System.out.println("You chose option " + opt);
            switch (opt) {
                case 1: {
                    System.out.println("Creating event");
                    createEvent(keyboard, model);
                    break;
                }
                case 2: {
                    System.out.println("Deleting event");
                    deleteEvent(keyboard, model);
                    break;
                }
                case 3: {
                    System.out.println("Editing events");
                    editEvents(keyboard, model);
                    break;
                }
                
                case 4:{
                    System.out.println("Viewing events");
                    viewEvents(model);
                    break;
                }
                
                case 5: {
                    System.out.println("Creating Manager");
                    createManager(keyboard, model);
                    break;
                }
                
                case 6: {
                    System.out.println("Deleting Manager");
                    deleteManager(keyboard, model);
                    break;
                }
                
                case 7: {
                    System.out.println("Editing Manager");
                    editManager(keyboard, model);
                    break;
                }
                
                case 8:{
                    System.out.println("Viewing Manager");
                    viewManager(model);
                    break;
                }
            }
        }
        while (opt != 9);
        System.out.println("Goodbye");
    }
    
    private static void createEvent(Scanner keyb, Model mdl){
        Event e = readEvent(keyb);
        if (mdl.addEvent(e)){
            System.out.println("Event added to database");
        }
        else {
            System.out.println("Event added to database");
        }
        System.out.println();
    }
    
    private static void createManager(Scanner keyb, Model mdl){
        Manager m = readManager(keyb);
        if (mdl.addManager(m)){
            System.out.println("Manager added to database");
        }
        else {
            System.out.println("Manager added to database");
        }
        System.out.println();
    }
    
    private static void deleteEvent(Scanner kb, Model m) {
        System.out.print("Enter the event id to delete:");
        int id = Integer.parseInt(kb.nextLine());
        Event e;

        e = m.findEventById(id);
        if (e != null) {
            if (m.removeEvent(e)) {
                System.out.println("Event deleted");
            }
            else {
                System.out.println("Event not deleted");
            }
        }
        else {
            System.out.println("Event not found");
        }
    }
    
    private static void deleteManager(Scanner kb, Model m) {
        System.out.print("Enter the manager id to delete:");
        int id = Integer.parseInt(kb.nextLine());
        Manager ma;

        ma = m.findManagerById(id);
        if (m != null) {
            if (m.removeManager(ma)) {
                System.out.println("Manager deleted");
            }
            else {
                System.out.println("Manager not deleted");
            }
        }
        else {
            System.out.println("Manager not found");
        }
    }
    
    private static void editEvents(Scanner kb, Model m) {
        System.out.print("Enetr the event id you wish to edit:");
        int id = Integer.parseInt(kb.nextLine());
        Event e;

        e = m.findEventById(id);
       if (e!= null){
           editEventDetails(kb,m,e);
           if(m.updateEvent(e)){
               System.out.println("Event updated");
           }
           else {
               System.out.print("Event not updated");
           }
       }
       else{
           System.out.println("Event not found");
       }
    } 
    
    private static void editManager(Scanner kb, Model m) {
        System.out.print("Enter the manager id you wish to edit:");
        int id = Integer.parseInt(kb.nextLine());
        Manager ma;

        ma = m.findManagerById(id);
       if (ma!= null){
           editManagerDetails(kb,m,ma);
           if(m.updateManager(ma)){
               System.out.println("Manager updated");
           }
           else {
               System.out.print("Manager not updated");
           }
       }
       else{
           System.out.println("Manager not found");
       }
    }
    
    private static void viewEvents(Model mdl) {
        List<Event> events = mdl.getEvents();
        System.out.println();
        if (events.isEmpty()){
            System.out.println("There are no events in this database.");
        }
        else{
            System.out.printf("%5s %20s %20s %20s %15s %12s %20s %8s\n", "Id", "Date", "Time", "Title", "Attending", "Address", "Event Manager", "Price");
            for (Event ev : events){
                System.out.printf("%5d %20s %20s %20s %15s %12s %20s %f8\n",
                    ev.getId(),
                    ev.getDate(),
                    ev.getTime(),
                    ev.getTitle(),
                    ev.getAttending(),
                    ev.getAddress(),
                    ev.getEventManager(),
                    ev.getPrice());
            }
        }
        System.out.println();
    }
    
    private static void viewManager(Model model) {
        List<Manager> eventmanager = model.getManagers();
        System.out.println();
        if (eventmanager.isEmpty()){
            System.out.println("There are no managers in this database.");
        }
        else{
            System.out.printf("%5s %20s %20s %20s %15s\n", "Id", "Name", "Address", "Email", "Phone");
            for (Manager ma : eventmanager){
                System.out.printf("%5d %20s %20s %20s %15s\n",
                    ma.getId(),
                    ma.getName(),
                    ma.getManaddress(),
                    ma.getEmail(),
                    ma.getPhone());
            }
        }
        System.out.println();
    }
    
    private static Event readEvent(Scanner keyb) {
        String date, time, title, attending, address, eventManager;
        double price;
        String line;

        date = getString(keyb, "Enter date: ");
        time = getString(keyb, "Enter time: ");
        title = getString(keyb, "Enter title: ");
        attending = getString(keyb, "Enter attending: ");
        line = getString(keyb, "Enter price: ");
        price = Double.parseDouble(line);
        address = getString(keyb, "Enter address: ");
        eventManager = getString(keyb, "Enter event manager's name: ");

        Event e = 
                new Event(date, time, title, attending, 
                        address, eventManager ,price);
        
        return e;
    }   
    
    private static Manager readManager(Scanner keyb) {
        String name, manaddress, email, phone;

        name = getString(keyb, "Enter name: ");
        manaddress = getString(keyb, "Enter address: ");
        email = getString(keyb, "Enter email: ");
        phone = getString(keyb, "Enter phone: ");

        Manager m = 
                new Manager(name, manaddress, email, phone);
        
        return m;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static void editEventDetails(Scanner kb, Model m, Event e) {
        String date, time, title, attending, address, eventManager;
        double price;
        String line1;
        
        date = getString(kb, "Enter date[" + e.getDate() + "]:");
        time = getString(kb, "Enter time[" + e.getTime() + "]:");
        title = getString(kb, "Enter title[" + e.getTitle() + "]:");
        attending = getString(kb, "Enter attending[" + e.getAttending() + "]:");
        address = getString(kb, "Enter address[" + e.getAddress() + "]:");
        eventManager = getString(kb, "Enter event manager[" + e.getEventManager() + "]:");
        line1 = getString(kb, "Enter price[" + e.getPrice() + "]:");
        
        if (date.length() != 0){
            e.setDate(date);
        }
        
        if (time.length() != 0){
            e.setTime(time);
        }
        
        if (title.length() != 0){
            e.setTitle(title);
        }
        
        if (attending.length() != 0){
            e.setAttending(attending);
        }
        
        if (address.length() != 0){
            e.setAddress(address);
        }
        
        if (eventManager.length() != 0){
            e.setEventManager(eventManager);
        }
        
        if (line1.length() != 0){
            price = Integer.parseInt(line1);
            e.setPrice(price);
        }
                
        
    }
    
    private static void editManagerDetails(Scanner kb, Model m, Manager ma) {
        String name, manaddress, email, phone;
        
        name = getString(kb, "Enter name[" + ma.getName() + "]:");
        manaddress = getString(kb, "Enter address[" + ma.getManaddress() + "]:");
        email = getString(kb, "Enter email[" + ma.getEmail() + "]:");
        phone = getString(kb, "Enter phone[" + ma.getPhone() + "]:");
        
        if (name.length() != 0){
           ma.setName(name);
        }
        
        if (manaddress.length() != 0){
            ma.setManaddress(manaddress);
        }
        
        if (email.length() != 0){
           ma.setEmail(email);
        }
        
        if (phone.length() != 0){
           ma.setPhone(phone);
        }
       
    }

    
   

    


}
