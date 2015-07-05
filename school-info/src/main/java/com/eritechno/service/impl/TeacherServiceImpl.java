package com.eritechno.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.eritechno.model.Teacher;
import com.eritechno.repository.TeacherRepository;
import com.eritechno.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public int saveTeacher(Teacher teacher) {
		Assert.notNull(teacher, "teacher is reqired");
		return teacherRepository.saveTeacher(teacher);
	}

	@Override
	public Teacher findTeacher(int id) {
		Assert.isTrue(0 != id, "id is required");
		return teacherRepository.findTeacher(id);
	}

	@Override
	public int deleteTeacher(int teacherId) {
		Assert.isTrue(teacherId != 0, "teacher is required");
		return teacherRepository.deleteTeacher(teacherId);
	}

	@Override
	public int editTeacher(Teacher teacher) {
		Assert.notNull(teacher, "teacher is required");
		return teacherRepository.editTeacher(teacher);
	}

}
