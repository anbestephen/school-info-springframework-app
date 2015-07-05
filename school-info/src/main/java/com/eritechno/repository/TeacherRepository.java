package com.eritechno.repository;

import com.eritechno.model.Teacher;

public interface TeacherRepository {

	public Teacher findTeacher(int id);

	public int saveTeacher(Teacher teacher);

	public int editTeacher(Teacher teacher);

	public int deleteTeacher(int teacherId);
}
