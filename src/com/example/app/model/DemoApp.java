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
            System.out.println("5. Exit");
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
            }
        }
        while (opt != 5);
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


}
