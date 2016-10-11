package com.eritechno.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.eritechno.model.Teacher;
import com.eritechno.repository.TeacherRepository;

@Transactional(readOnly = false)
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private static final String SELECT_TEACHER = "SELECT ID , FIRST_NAME , LAST_NAME, AGE , GENDER "
			+ " FROM TEACHER  WHERE ID = ? ";
	private static final String INSERT_TEACHER = "INSERT INTO TEACHER ( FIRST_NAME , LAST_NAME, AGE , GENDER ) "
			+ "VALUES ( ? , ? , ? , ? )";
	private static final String UPDATE_TEAHER = "UPDATE TEACHER "
			+ "SET FIRST_NAME = ? , LAST_NAME = ? , AGE = ? , GENDER = ? "
			+ "WHERE ID = ? ";
	private static final String DELETE_TEACHER = "DELETE FROM TEACHER "
			+ "WHERE ID = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Teacher findTeacher(int teacherId) {
		Assert.isTrue(teacherId != 0, "teacherId is required");
		Teacher teacher = null;
		try {

			teacher = jdbcTemplate.queryForObject(SELECT_TEACHER,
					new TeacherMapper(), teacherId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error retriving teacher " + teacherId, e);
		}
		return teacher;
	}

	@Override
	public int saveTeacher(Teacher teacher) {
		Assert.notNull(teacher, "teacher is required");
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(INSERT_TEACHER,
					teacher.getFirstName(), teacher.getLastName(),
					teacher.getAge(), String.valueOf(teacher.getGender()));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error inserting Teacher", e);
		}
		return affectedRows;
	}

	@Override
	public int editTeacher(Teacher teacher) {
		Assert.notNull(teacher, "teacher is required");
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(UPDATE_TEAHER,
					teacher.getFirstName(), teacher.getLastName(),
					teacher.getAge(), String.valueOf(teacher.getGender()),
					teacher.getId());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error editing Teacher", e);
		}
		return affectedRows;
	}

	@Override
	public int deleteTeacher(int teacherId) {
		Assert.isTrue(teacherId != 0, "teacher is required");
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(DELETE_TEACHER, teacherId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error deleting Teacher", e);
		}
		return affectedRows;
	}

	private class TeacherMapper implements RowMapper<Teacher> {

		@Override
		public Teacher mapRow(ResultSet rs, int row) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getInt("ID"));
			teacher.setFirstName(rs.getString("FIRST_NAME"));
			teacher.setLastName(rs.getString("LAST_NAME"));
			teacher.setAge(rs.getInt("AGE"));
			teacher.setGender(rs.getString("GENDER").charAt(0));
			return teacher;
		}

	}
}
