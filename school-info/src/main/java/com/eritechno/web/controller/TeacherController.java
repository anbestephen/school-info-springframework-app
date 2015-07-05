package com.eritechno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eritechno.model.Teacher;
import com.eritechno.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public void findTeacher(@RequestParam(value = "studentId") int studentId) {
		Assert.isTrue(studentId != 0, "studentId is required");
		teacherService.findTeacher(studentId);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertTeacher(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "age") int age) {
		Assert.hasLength(firstName, "firstName is required");
		Assert.hasLength(lastName, "lastName is required");
		Assert.isTrue(age != 0, "age is required");
		teacherService.saveTeacher(new Teacher(firstName, lastName, age));

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateTeacher(@RequestParam(value = "studentId") int studentId,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "age") int age) {
		Assert.isTrue(studentId != 0, "studentId is required");
		Assert.hasLength(firstName, "firstName is required");
		Assert.hasLength(lastName, "lastName is required");
		Assert.isTrue(age != 0, "age is required");
		teacherService.editTeacher(new Teacher(studentId, firstName, lastName,
				age));

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void deleteTeacher(@RequestParam(value = "teacherId") int teacherId) {
		Assert.isTrue(teacherId != 0, "studentId is required");
		teacherService.deleteTeacher(teacherId);

	}

}
