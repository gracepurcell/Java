package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DemoApp {
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Model model;
        int opt = 9;
        
        
        do {
            try {
                model = Model.getInstance();
                
                System.out.println("1. Create new Event");
                System.out.println("2. Delete existing Event");
                System.out.println("3. Edit existing Event");
                System.out.println("4. View all Events");
                System.out.println();
                System.out.println("5. Create new Manager");
                System.out.println("6. Delete existing Manager");
                System.out.println("7. Edit existing Manager");
                System.out.println("8. View all Managers");
                System.out.println();
                System.out.println("9. Exit");
                System.out.println();


                opt = getInt(keyboard, "Enter option: ", 9);

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
            catch (DataAccessException e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        while (opt != 9);
        System.out.println("Goodbye");
    }
    
    private static void createEvent(Scanner keyb, Model mdl) throws DataAccessException{
        Event e = readEvent(keyb, mdl);
        if (mdl.addEvent(e)){
            System.out.println("Event added to database");
        }
        else {
            System.out.println("Event added to database");
        }
        System.out.println();
    }
    
    private static void createManager(Scanner keyb, Model mdl) throws DataAccessException{
        Manager m = readManager(keyb);
        if (mdl.addManager(m)){
            System.out.println("Manager added to database");
        }
        else {
            System.out.println("Manager added to database");
        }
        System.out.println();
    }
    
    private static void deleteEvent(Scanner kb, Model m) throws DataAccessException {
        int id = getInt(kb, "Enter the event id to delete:", -1 );
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
    
    private static void deleteManager(Scanner kb, Model m) throws DataAccessException {
        int id = getInt(kb, "Enter the manager id to delete:", -1);
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
    
    private static void editEvents(Scanner kb, Model m) throws DataAccessException {
        int id = getInt(kb, "Enter the event id to edit:", -1);
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
    
    private static void editManager(Scanner kb, Model m) throws DataAccessException {
        int id = getInt(kb, "Enter the manager id to delete:", -1);
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
            System.out.printf("%5s %15s %10s %20s %10s %20s %8s %15s\n", "Id", "Date", "Time", "Title", "Attending", "Address", "Price", "Manager");
            for (Event ev : events){
                Manager ma = mdl.findManagerById(ev.getEventManagerId());
                System.out.printf("%5d %15s %10s %20s %10s %20s %8s %15s\n",
                    ev.getId(),
                    ev.getDate(),
                    ev.getTime(),
                    ev.getTitle(),
                    ev.getAttending(),
                    ev.getAddress(),
                    ev.getPrice(),
                (ma != null) ? ma.getName() : "");
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
            System.out.printf("%5s %20s %20s %20s %15s\n", "ID", "Name", "Address", "Email", "Phone");
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
    
    private static Event readEvent(Scanner keyb, Model model) {
        String date, time, title, attending, address;
        double price;
        String line;
        int eventManagerId;

        date = getString(keyb, "Enter date: ");
        time = getString(keyb, "Enter time: ");
        title = getString(keyb, "Enter title: ");
        attending = getString(keyb, "Enter attending: ");
        price = getDouble(keyb, "Enter price: ", 0);
        address = getString(keyb, "Enter address: ");
        viewManager(model);
        eventManagerId = getInt(keyb, "Enter event manager's id: ", -1);

        Event e = 
                new Event(date, time, title, attending, address, price, eventManagerId);
        
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

    private static void editEventDetails(Scanner kb, Model m, Event e) {
        String date, time, title, attending, address;
        double price;
        int eventManager;
        String line1;
        
        date = getString(kb, "Enter date[" + e.getDate() + "]:");
        time = getString(kb, "Enter time[" + e.getTime() + "]:");
        title = getString(kb, "Enter title[" + e.getTitle() + "]:");
        attending = getString(kb, "Enter attending[" + e.getAttending() + "]:");
        address = getString(kb, "Enter address[" + e.getAddress() + "]:");
        price = getDouble(kb, "Enter price[" + e.getPrice() + "]:", e.getPrice());
        eventManager = getInt(kb, "Enter event manager id [" + e.getEventManagerId() + "]:", e.getEventManagerId());
        
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
        
        if (price !=e.getPrice()){
            e.setPrice(price);
        }
                
        if (eventManager != e.getEventManagerId()){
            e.setEventManagerId(eventManager);
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

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static int getInt(Scanner keyb, String prompt, int defaultValue ){
        int opt =defaultValue;
        boolean finished = false;

        do{
            try {
                System.out.print(prompt);
                String line = keyb.nextLine();
                if (line.length() > 0){
                    opt = Integer.parseInt(line);
                }
                finished = true;
            }
            catch (NumberFormatException e){
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while(!finished);

        return opt;
    }

     private static double getDouble(Scanner keyb, String prompt, double defaultValue ){
        double opt =defaultValue;
        boolean finished = false;

        do{
            try {
                System.out.print(prompt);
                String line = keyb.nextLine();
                if (line.length() > 0){
                    opt = Double.parseDouble(line);
                }
                finished = true;
            }
            catch (NumberFormatException e){
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while(!finished);

        return opt;
    }

}
