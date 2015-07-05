package com.eritechno.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.eritechno.model.Student;
import com.eritechno.service.StudentService;
import com.eritechno.web.model.ModelAttributeNames;
import com.eritechno.web.model.ViewNames;
import com.eritechno.web.util.UploadUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	/**
	 * default home page controller
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String showAll(Model model) {
		model.addAttribute("students", studentService.findAll());
		return ViewNames.STUDENT_HOME;
	}

	/**
	 * controller for edit student page
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/edit/{studentId}", method = RequestMethod.GET)
	public String editStudent(Model model, @PathVariable int studentId) {
		model.addAttribute(ModelAttributeNames.STUDENT,
				studentService.findStudent(studentId));
		return ViewNames.EDIT_STUDENT;

	}

	/**
	 * controller for add student page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addStudent() {
		return ViewNames.ADD_STUDENT;
	}

	/**
	 * controller for find student
	 * 
	 * @param studentId
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public void findStudent(@RequestParam(value = "studentId") int studentId) {
		Assert.isTrue(studentId != 0, "studentId is required");
		studentService.findStudent(studentId);
	}

	/**
	 * 
	 * controller for deleting student
	 * 
	 * @param studentId
	 * @param redirectAttrib
	 * @return
	 */
	@RequestMapping(value = "/delete/{studentId}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable int studentId,
			RedirectAttributes redirectAttrib) {
		Assert.isTrue(studentId != 0, "studentId is required");
		;
		redirectAttrib.addFlashAttribute(ModelAttributeNames.MESSAGE,
				studentService.deleteStudent(studentId));
		return ViewNames.REDIRECT_STUDENT_HOME;
	}

	/**
	 * 
	 * controller for getting student's image
	 * 
	 * @param studentId
	 * @return
	 */

	@RequestMapping(value = "/photo/{studentId}")
	@ResponseBody
	public byte[] getImage(@PathVariable int studentId) {
		return UploadUtils.getStudentPhoto(studentId);
	}

	/**
	 * controller for adding student
	 * 
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param redirectAttrib
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String insetStudent(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "age") int age,
			@RequestParam(value = "gender") char gender,
			@RequestParam(value = "studentPhoto") MultipartFile studentPhoto,
			RedirectAttributes redirectAttrib) {
		Assert.hasLength(firstName, "firstName is required");
		Assert.hasLength(lastName, "lastName is required");
		Assert.isTrue(age != 0, "age is required");
		Assert.notNull(studentPhoto, "studentPhoto is required");

		redirectAttrib.addFlashAttribute(ModelAttributeNames.MESSAGE,
				studentService.saveStudent(new Student(firstName, lastName,
						age, gender), studentPhoto));
		return ViewNames.REDIRECT_STUDENT_HOME;
	}

	/**
	 * controller for editing student
	 * 
	 * @param studentId
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param redirectAttrib
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateStudent(
			@RequestParam(value = "studentId") int studentId,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "age") int age,
			@RequestParam(value = "gender") char gender,
			@RequestParam(value = "studentPhoto") MultipartFile studentPhoto,
			RedirectAttributes redirectAttrib) {
		Assert.isTrue(studentId != 0, "studentId is required");
		Assert.hasLength(firstName, "firstName is required");
		Assert.hasLength(lastName, "lastName is required");
		Assert.isTrue(age != 0, "age is required");
		Assert.notNull(studentPhoto, "studentPhoto is required");
		redirectAttrib.addFlashAttribute(ModelAttributeNames.MESSAGE,
				studentService.editStudent(new Student(studentId, firstName,
						lastName, age, gender), studentPhoto));
		return ViewNames.REDIRECT_STUDENT_HOME;
	}

	/**
	 * 
	 * redirects exception to default controller
	 * 
	 * @param redirectAttrib
	 * @param errorMessage
	 * @return
	 */
	@RequestMapping(value = "/exception")
	public String redirectException(RedirectAttributes redirectAttrib,
			@RequestParam("errorMessage") String errorMessage) {
		redirectAttrib.addFlashAttribute(ModelAttributeNames.ERROR,
				errorMessage);
		return ViewNames.REDIRECT_STUDENT_HOME;
	}

	/**
	 * exception handler
	 * 
	 * @param exception
	 * @param redirectAttrib
	 * @return
	 */

	@ExceptionHandler(Exception.class)
	public RedirectView handleAllExceptins(Exception exception) {
		RedirectView redirectView = new RedirectView(
				ViewNames.REDIRECT_STUDENT_EXCEPTION);
		redirectView.addStaticAttribute("errorMessage", exception.getMessage());
		return redirectView;
	}
}
