package com.mycompany.restws;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Course")
public class Course {
    private int id;
    private String name;
    private List<Student> students = new ArrayList<>();
 
    private Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public List<Student> getStudents(){
        return this.students;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    
    @GET
    @Path("{studentId}")
    public Student getStudent(@PathParam("studentId")int studentId) {
        return findById(studentId);
    }
    
    
    // called by client createStudentInCourse(...) and createStudentXml(...)
    @POST // handles POST requests, indicated by the @POST annotation, 
            //by adding the received Student object to the students list:
    @Path("") // can be avoided since it's an empty string
    public Response createStudent(Student student) {
        for (Student element : students) {
            if (element.getId() == student.getId()) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        students.add(student);
        return Response.ok(student).build(); // returns 200 OK status response or 409 Conflict
    }
    
    // invoked by client deleteStudentInCourse(...)
    @DELETE
    @Path("{studentId}")
    public Response deleteStudent(@PathParam("studentId") int studentId) {
        Student student = findById(studentId);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        students.remove(student);
        return Response.ok().build();
    }

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
            return (obj instanceof Course) && (id == ((Course) obj).getId()) && (name.equals(((Course) obj).getName()));

    }
    
    
}
