package com.eritechno.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import com.eritechno.model.Student;
import com.eritechno.repository.StudentRepository;
import com.eritechno.test.BaseIntegrationTestCase;

public class StudentRepositoryTest extends BaseIntegrationTestCase {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testFindAllStudents(){
		assertTrue(studentRepository.findAll().size() > 1);
	}
	@Test
	public void testInsertAndFindStudent() {
		Student student = new Student();
		student.setFirstName("Bere");
		student.setLastName("Alem");
		student.setAge(25);
		student.setGender('M');
		assertTrue(studentRepository.saveStudent(student)> 2);

		Student feachedStudent = studentRepository.findStudent(4);
		assertNotNull(feachedStudent);
		assertEquals(student.getFirstName(), feachedStudent.getFirstName());
		assertEquals(student.getLastName(), feachedStudent.getLastName());
		assertEquals(student.getAge(), feachedStudent.getAge());

	}

	@Test
	public void testEditStudent() {
		Student student = studentRepository.findStudent(1);
        student.setId(1);
		student.setFirstName("Other First Name");
		student.setLastName("Other Last Name");
		student.setAge(0);
		student.setGender('M');
		assertEquals(1, studentRepository.editStudent(student));
		student = studentRepository.findStudent(1);
		assertEquals("Other First Name", student.getFirstName());
		assertEquals("Other Last Name", student.getLastName());
		assertEquals(0, student.getAge());
	}
	
	@Test
	public void testDeletStudent(){
		assertEquals(1, studentRepository.deleteStudent(2));
		assertNull(studentRepository.findStudent(2));
	}

}
