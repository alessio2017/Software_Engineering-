package com.mycompany.restws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


// this is the name of the WS
@Path("course") //course is the root resource, so all requests with "course" URL are mapped here
@Produces("text/xml") // The value of @Produces annotation is used to tell the server to 
        //convert objects returned from methods within this class to XML documents before
        //sending them to clients.
public class CourseRepository {
    // creating a simple database
    private Map<Integer, Course> courses = new HashMap<>();
    {
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setId(1);
        student1.setName("Student A");
        student2.setId(2);
        student2.setName("Student B");

        List<Student> course1Students = new ArrayList<>();
        course1Students.add(student1);
        course1Students.add(student2);

        Course course1 = new Course();
        Course course2 = new Course();
        course1.setId(1);
        course1.setName("REST with Spring");
        course1.setStudents(course1Students);
        course2.setId(2);
        course2.setName("Learn Spring Security");

        courses.put(1, course1);
        courses.put(2, course2);
    }
 
    // request handling methods
    private Course findById(int id) {
        for (Map.Entry<Integer, Course> course : courses.entrySet()) {
            if (course.getKey() == id) {
                return course.getValue();
            }
        }
        return null;
    }
    
    // invoked by client getCourse(...)
    @GET // The methog is invoked when handling GET requests
    @Path("courses/{courseId}")
    public Course getCourse(@PathParam("courseId") int courseId) {
        return findById(courseId);
    }
    
    // invoked by client updateCourse(...) and updateCourseXml(...)
    @PUT // updates
    @Path("courses/{courseId}")
    public Response updateCourse(@PathParam("courseId") int courseId, Course course) {
        Course existingCourse = findById(courseId);        
        if (existingCourse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingCourse.equals(course)) {
            return Response.notModified().build();    
        }
        courses.put(courseId, course);
        return Response.ok().build();
    }
    
    // This method of this root resource class does not directly handle any HTTP request. 
    // Instead, it delegates requests to the Course class where requests are handled by matching methods
    // Since this method is not marked with an HTTP annotation, once it returns it has to
    // call some method inside the class of the type of the returned object and seeing if there is
    // some match inside
    @Path("courses/{courseId}/students")
    public Course pathToStudent(@PathParam("courseId") int courseId) {
        return findById(courseId);
    }
}