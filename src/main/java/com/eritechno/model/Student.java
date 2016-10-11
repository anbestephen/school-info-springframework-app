package com.eritechno.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {
	private long id;
	@NotNull(message = "firstName is required")
	@Size(min = 5, max = 12)
	private String firstName;
	@NotNull(message = "lastName is required")
	@Size(min = 5, max = 12)
	private String lastName;
	@Min(6)
	private int age;
	@NotNull
	private char gender;

	public Student() {
	}

	public Student(String firstName, String lastName, int age, char gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public Student(long id, String firstName, String lastName, int age,
			char gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

}
