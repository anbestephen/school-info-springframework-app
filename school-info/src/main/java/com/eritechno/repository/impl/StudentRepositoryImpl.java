package com.eritechno.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.eritechno.model.Student;
import com.eritechno.repository.StudentRepository;
import com.eritechno.repository.util.SISPreparedStatementCreatorFactory;

@Transactional(readOnly = false)
@Repository
public class StudentRepositoryImpl implements StudentRepository {
	@Autowired
	private JdbcTemplate jdbcTeamplate;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private static final String SELECT_ALL_STUDENTS = " SELECT ID , FIRST_NAME , LAST_NAME , AGE , GENDER "
			+ " FROM STUDENT ";
	private static final String SELECT_STUDENT_FOR_ID = "SELECT ID , FIRST_NAME , LAST_NAME , AGE , GENDER "
			+ "FROM STUDENT WHERE ID = ?";
	private static final String INSERT_STUDENT = "INSERT INTO STUDENT ( FIRST_NAME , LAST_NAME , AGE , GENDER ) "
			+ "VALUES ( ? , ? , ? , ?)";
	private static final String UPDATE_STUDENT = "UPDATE STUDENT "
			+ "SET FIRST_NAME = ? , LAST_NAME = ? , AGE = ?  , GENDER = ? "
			+ "WHERE ID = ? ";
	private static final String DELETE_STUDENT = "DELETE FROM STUDENT "
			+ "WHERE ID = ?";

	@Override
	public List<Student> findAll() {
		List<Student> studentList = null;
		try {
			studentList = jdbcTeamplate.query(SELECT_ALL_STUDENTS,
					new StudentMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error feaching all Students");
		}
		return studentList;
	}

	@Override
	public Student findStudent(int studentId) {
		Assert.isTrue(studentId != 0, "studentId is required");
		Student student = null;
		try {
			student = jdbcTeamplate.queryForObject(SELECT_STUDENT_FOR_ID,
					new StudentMapper(), studentId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error feaching Student " + studentId, e);
		}
		return student;
	}

	@Override
	public long saveStudent(Student student) {
		Assert.notNull(student, "student is required");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long generetedKey = 0;
		try {
			jdbcTeamplate
					.update(SISPreparedStatementCreatorFactory
							.createPreparedStatementCreator(
									INSERT_STUDENT,
									new Object[] { student.getFirstName(),
											student.getLastName(),
											student.getAge(),
											String.valueOf(student.getGender()) },
									true), keyHolder);
			generetedKey = keyHolder.getKey().longValue();
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error saving Student " + student.getId(), e);
		}
		return generetedKey;
	}

	@Override
	public int deleteStudent(int studentId) {
		Assert.isTrue(studentId != 0, "student is required");
		int affectedRows = 0;
		try {
			affectedRows = jdbcTeamplate.update(DELETE_STUDENT, studentId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error deleting Student " + studentId);
		}
		return affectedRows;
	}

	@Override
	public int editStudent(Student student) {
		Assert.notNull(student, "student is required");
		int affectedRows = 0;
		try {
			affectedRows = jdbcTeamplate.update(UPDATE_STUDENT,
					student.getFirstName(), student.getLastName(),
					student.getAge(), String.valueOf(student.getGender()),
					student.getId());
		} catch (EmptyResultDataAccessException e) {
			logger.error("Error updating student " + student.getId());
		}
		return affectedRows;
	}

	class StudentMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int age) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("ID"));
			student.setFirstName(rs.getString("FIRST_NAME"));
			student.setLastName(rs.getString("LAST_NAME"));
			student.setAge(rs.getInt("AGE"));
			student.setGender(rs.getString("GENDER").charAt(0));
			return student;
		}

	}

}
