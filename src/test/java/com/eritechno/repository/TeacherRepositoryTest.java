package com.eritechno.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eritechno.model.Teacher;
import com.eritechno.repository.TeacherRepository;
import com.eritechno.test.BaseIntegrationTestCase;

public class TeacherRepositoryTest extends BaseIntegrationTestCase {
	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	public void testInsertAndFindTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName("John");
		teacher.setLastName("Doe");
		teacher.setAge(35);
		assertEquals(1, teacherRepository.saveTeacher(teacher));
		teacher = teacherRepository.findTeacher(4);
		assertEquals("John", teacher.getFirstName());
		assertEquals("Doe", teacher.getLastName());
		assertEquals(35, teacher.getAge());
	}

	@Test
	public void editTeacher() {

		Teacher teacher = teacherRepository.findTeacher(1);
		assertNotNull(teacher);
		teacher.setAge(50);
		teacher.setFirstName("Other First Name");
		teacher.setLastName("Other Last Name");
		assertEquals(1, teacherRepository.editTeacher(teacher));
		teacher = teacherRepository.findTeacher(1);
		assertEquals("Other First Name", teacher.getFirstName());
		assertEquals("Other Last Name", teacher.getLastName());
		assertEquals(50, teacher.getAge());
	}

	@Test
	public void deleteTeacher() {
      assertEquals(1, teacherRepository.deleteTeacher(2));
      assertNull(teacherRepository.findTeacher(2));
	}
}
