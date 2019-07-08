/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.clientrestesame;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */
@XmlRootElement(name = "Professor")
public class Professor {
    private String name;
    private String surname;
    public Professor(){
    }
    
    public Professor(String name, String surname){
        this.name=name;
        this.surname=surname;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getName(){
        return this.name;
        
    }
    public String getSurname(){
        return this.surname;
    }
}
