package com.sidhu.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sidhu.dao.StudentDao;
import com.sidhu.dao.StudentDaoImpl;
import com.sidhu.entity.Student;

@Path("/")
public class StudentController {
	
	private StudentDao studentDao = new StudentDaoImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/StudentApp")
	public List<Student> getStudents(@DefaultValue("") @QueryParam("q") String q){
		List<Student> students = null;
		if(q.isEmpty())
			students = studentDao.getStudents();
		else
			students = studentDao.searchStudents(q);
		return students;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/StudentApp/{id}")
	public Student findStudent(@PathParam("id") int studentId){
		Student student = studentDao.getStudentById(studentId);
		return student;
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/StudentApp")
	public String addStudent(Student student){
		studentDao.saveOrUpdate(student);
		return "Student ID: " + student.getId();
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Path("/StudentApp/{id}")
	public String updateStudent(Student student){
		studentDao.saveOrUpdate(student);
		return "Student ID: " + student.getId();
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/StudentApp/{id}")
	public String deleteStudent(@PathParam("id") int studentId){
		studentDao.delete(studentId);
		return "Student ID: " + studentId;
	}
}
