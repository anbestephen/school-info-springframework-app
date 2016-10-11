package com.eritechno.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.eritechno.model.Student;
import com.eritechno.repository.StudentRepository;
import com.eritechno.service.StudentService;
import com.eritechno.web.util.UploadUtils;
import com.eritechno.web.util.WebUtils;

@Service
@PropertySource("classpath:/config/common.properties")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Value("${student.insert.message}")
	public String studentInsertMessage;
	@Value("${student.update.message}")
	public String StudentUpdateMessage;
	@Value("${student.delete.message}")
	public String studentDeleteMessage;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public String saveStudent(Student student, MultipartFile studentImage) {
		Assert.notNull(student, "student is required");
		long generatedKey = studentRepository.saveStudent(student);
		UploadUtils.uploadImage(studentImage, UploadUtils.STUDENT_DIRECTORY,
				generatedKey);
		return WebUtils.getFormatedMessage(studentInsertMessage, generatedKey);
	}

	@Override
	public String editStudent(Student student, MultipartFile studentImage) {
		Assert.notNull(student, "student is required");
		studentRepository.editStudent(student);
		UploadUtils.uploadImage(studentImage, UploadUtils.STUDENT_DIRECTORY,
				student.getId());
		return WebUtils.getFormatedMessage(StudentUpdateMessage,
				student.getId());
	}

	@Override
	public String deleteStudent(int studentId) {
		Assert.isTrue(studentId != 0, "student is required");
		studentRepository.deleteStudent(studentId);
		return WebUtils.getFormatedMessage(studentDeleteMessage, studentId);
	}

	@Override
	public Student findStudent(int id) {
		Assert.isTrue(id != 0, "id is required");
		return studentRepository.findStudent(id);
	}

}
