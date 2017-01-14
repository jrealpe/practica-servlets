/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;


public class Conference {
    
    Integer id;
    String name;
    String description;
    Date date;
    
    public Conference(){}

    public Conference(Integer id, String name, String description, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static boolean isValid(String name, String date, String description){
        
        if (name == null || date == null) return false;
        if((name.length() < 4 || name.isEmpty()) && date.isEmpty()) return false;        
        
        return true;
        
    }
}
