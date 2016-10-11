package com.eritechno.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eritechno.model.Student;

public interface StudentService {

	public List<Student> findAll();

	public String saveStudent(Student student, MultipartFile studentImage);

	public String editStudent(Student student, MultipartFile studentImage);

	public String deleteStudent(int studentId);

	public Student findStudent(int studentId);

}
