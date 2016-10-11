package com.eritechno.service;

import com.eritechno.model.Teacher;

public interface TeacherService {

	public int saveTeacher(Teacher teacher);

	public Teacher findTeacher(int id);

	public int deleteTeacher(int  teacherId);

	public int editTeacher(Teacher teacher);

}
