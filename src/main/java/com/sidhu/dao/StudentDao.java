package com.sidhu.dao;

import java.util.List;

import com.sidhu.entity.Student;

public interface StudentDao {
	public List<Student> getStudents();
	public Student getStudentById(int studentId);
	public int saveOrUpdate(Student student);
	public void delete(int studentId);
	public List<Student> searchStudents(String q);
}
