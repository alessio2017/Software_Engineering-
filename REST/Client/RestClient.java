package com.mycompany.restclient;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*
 * path of xml POST/PUT 
 * /home/biar/NetBeansProjects/Lab02_REST/RestClient/src/main/resources/course.xml
 */

public class RestClient {
    private static Client client;
    private static CloseableHttpClient clientxml;
    private static RestClient c;
   
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        //we create a client object, which is used to communicate with the server
        client = ClientBuilder.newClient(); // used for GET and POST without xml
        clientxml = HttpClients.createDefault();
        c = new RestClient(); //used for xml methods

        Course c1 = new Course();
        c1.setId(5);
        c1.setName("Malware Analysis");
        ArrayList<Student> newStudent = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(2); s1.setName("Mimmo");
        newStudent.add(s1);
        c1.setStudents(newStudent);
        Response r = updateCourse(c1, 1); //PUT
        System.out.println(r.getStatus());
        //_______________________
        Course c2 = new Course();
        c2.setId(3);
        c2.setName("Network Infrastructure");
        ArrayList<Student> newStudent2 = new ArrayList<>();
        Student s2 = new Student();
        s2.setId(2); s2.setName("Giovanni");
        newStudent2.add(s2);
        c2.setStudents(newStudent2);
        Response r2 = updateCourse(c2, 1); //PUT
        System.out.println(r2.getStatus());
        //______________________
     
        Student s3 = new Student();
        s3.setId(10); s3.setName("Riccardo");
        Response r3 = createStudentInCourse(s3, 2); //POST
        System.out.println(r3.getStatus());
        //______________________
        
        Response r4 = deleteStudentInCourse(2, 10); //DELETE
        System.out.println(r4.getStatus());
        //_______________________
        
        HttpResponse rep = createStudentXml(1); //POST XML
        System.out.println(rep.getStatusLine().getStatusCode());
        //_______________________
        
        HttpResponse rep2 = updateCourseXml(); //PUT XML
        System.out.println(rep2.getStatusLine().getStatusCode());
        //_______________________
      
        clientxml.close();
        client.close();
    }

    public static Response createStudentInCourse(Student newStudent, int courseId){
        WebTarget webTarget = client.target("http://localhost:8080/course/courses/" + String.valueOf(courseId) + "/students");
        Invocation.Builder builder = webTarget.request("text/xml");
        return builder.post(Entity.entity(newStudent, MediaType.TEXT_XML)); //for POST request
    }
    
    public static void getCourse(int id){
        WebTarget webTarget  = client.target("http://localhost:8080/course/");
        WebTarget depWebTarget = webTarget.path("courses/" + String.valueOf(id)); //same as before, just for modularization
        Invocation.Builder builder = depWebTarget.request("text/xml");
        Course dep = builder.accept(MediaType.TEXT_XML).get(Course.class); //for GET request
        System.out.println(dep.getId());
    }
    
    public static Response updateCourse(Course newCourse, int oldId){
       WebTarget webTarget = client.target("http://localhost:8080/course/courses/" + String.valueOf(oldId));
       Invocation.Builder builder = webTarget.request("text/xml");
       return builder.put(Entity.entity(newCourse, MediaType.TEXT_XML));//for PUT request
    }
    
    public static Response deleteStudentInCourse(int courseId, int studentId){
        WebTarget webTarget  = client.target("http://localhost:8080/course/");
        WebTarget depWebTarget = webTarget.path("courses/" + String.valueOf(courseId) + "/students/" + String.valueOf(studentId));
        Invocation.Builder builder = depWebTarget.request();
        return builder.delete(); //for DELETE request
    }
    
    //it creates a Student starting from an XML
    public static HttpResponse createStudentXml(int courseId) throws IOException{
        HttpPost httpPost = new HttpPost("http://localhost:8080/course/courses/" + String.valueOf(courseId) + "/students");
        InputStream resourceStream = c.getClass().getClassLoader().getResourceAsStream("student.xml");
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        httpPost.setHeader("Content-Type", "text/xml");
        return clientxml.execute(httpPost);
    }
    
    //it updates a Student starting from an XML
    public static HttpResponse updateCourseXml() throws IOException{
        HttpPut httpPut = new HttpPut("http://localhost:8080/course/courses/" + "2");
        InputStream resourceStream = c.getClass().getClassLoader().getResourceAsStream("course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");
        return clientxml.execute(httpPut);
    }
}