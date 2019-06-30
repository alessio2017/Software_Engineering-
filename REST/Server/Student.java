package com.mycompany.restws;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * The annotation to tell JAXB that instances of this class should be marshaled to XML.
 * Basically it maps a class or an enum type to an XML element.
 */
@XmlRootElement(name = "Student")
public class Student {
    private int id;
    private String name;
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
 
}