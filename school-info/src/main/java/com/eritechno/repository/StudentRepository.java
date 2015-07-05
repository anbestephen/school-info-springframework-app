package com.eritechno.repository;

import java.util.List;

import com.eritechno.model.Student;

public interface StudentRepository {

	public Student findStudent(int id);

	public long saveStudent(Student student);

	public int deleteStudent(int studentId);

	public int editStudent(Student student);

	public List<Student> findAll();

}
