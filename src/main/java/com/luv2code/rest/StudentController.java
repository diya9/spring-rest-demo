package com.luv2code.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentController {

	List<Student> students;
	Scanner sc = new Scanner(System.in);
	
	@PostConstruct
	public void loadData(){
		students = new ArrayList<>();
		students.add(new Student("Harry", "Potter"));
		students.add(new Student("Hermione", "Granger"));
		students.add(new Student("Draco", "Malfoy"));
		students.add(new Student("Ron", "Weasley"));
		students.add(new Student("Ginny", "Weasley"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
	@GetMapping("/student/{studentId}")
		public Student getStudent(@PathVariable int studentId){
		
			if(students.size() <= studentId || studentId < 0){
				throw new StudentNotFoundException("Student Not found.."+studentId);
			}
			return students.get(studentId);
		}
	
	@PutMapping("/student/{studentId}")
	public Student updateStudent(@PathVariable int studentId){
		if(students.size() <= studentId || studentId < 0){
			throw new StudentNotFoundException("Student Not found.."+studentId);
		}
		Student stud = students.get(studentId);
		System.out.println("Enter Student first Name: ");
		stud.setFname(sc.next());
		System.out.println("Enter Student last Name: ");
		stud.setLname(sc.next());
		students.set(studentId, stud);
		return students.get(studentId);
		
	}
	
	@DeleteMapping("/student/{studentId}")
	public String deleteStudent(@PathVariable int studentId){
		if(students.size() <= studentId || studentId < 0){
			throw new StudentNotFoundException("Student Not found.."+studentId);
		}
		
		students.remove(studentId);
		return "Student Deleted Successfully!!!!";
	}
	
	@PostMapping(path = "/student", consumes = "application/json", produces = "application/json")
	public List<Student> addStudent(@RequestBody Student student){
		students.add(student);
		System.out.println("Record Added Successfully!!!");
		return students;
	}
}
