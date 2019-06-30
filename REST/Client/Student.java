package com.mycompany.restclient;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Both Student.java and Course.java are used to handle HTTP response in the REST Client
 *
 */
@XmlRootElement(name = "Student")
public class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Student) && (id == ((Student) obj).getId()) && (name.equals(((Student) obj).getName()));
    }
}