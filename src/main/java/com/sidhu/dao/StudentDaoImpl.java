package com.sidhu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sidhu.entity.Student;
import com.sidhu.persistence.HibernateUtil;

public class StudentDaoImpl implements StudentDao {
	
	Session session;
	Transaction transaction;

	private void initSession(){
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	private void closeSession(){
		session.close();
	}
	
	private void initTransaction(){
		initSession();
		transaction = session.beginTransaction();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudents() {
		List<Student> students;
		initSession();
		students = session.createQuery("from Student").list();
		closeSession();
		return students;
	}

	@Override
	public Student getStudentById(int studentId) {
		initSession();
		Student student = (Student) session.get(Student.class, studentId);
		closeSession();
		return  student;
	}

	@Override
	public int saveOrUpdate(Student student) {
		initTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
		return student.getId();
	}

	@Override
	public void delete(int studentId) {
		initTransaction();
		Student student = (Student) session.get(Student.class, studentId);
		session.delete(student);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> searchStudents(String q) {
		List<Student> students;
		initSession();
		students = session.createQuery("from Student s where lower(s.firstName) like :firstName").setParameter("firstName", "%" + q.toLowerCase() + "%").list();
		closeSession();
		return students;
	}

}
