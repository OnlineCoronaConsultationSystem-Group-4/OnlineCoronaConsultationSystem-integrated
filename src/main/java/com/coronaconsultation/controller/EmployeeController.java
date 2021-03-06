package com.coronaconsultation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coronaconsultation.entities.Doctor;
import com.coronaconsultation.entities.Employee;
import com.coronaconsultation.entities.Gender;
import com.coronaconsultation.exception.EmployeeNotFoundException;
import com.coronaconsultation.services.EmployeeMasterImpl;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {
	@Autowired
	private EmployeeMasterImpl employeeMasterImpl;
	@PostMapping("/")
public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		
		try {
			employeeMasterImpl.createEmployee(employee);
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Employee Created", HttpStatus.OK);
	}
	
	
	@PutMapping("/updateEmployee/updateAllFields/{id}/")
	public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
		try {
			if(employeeMasterImpl.updateEmployee(id, employee)) {
				return new ResponseEntity<>("Employee Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateEmployee/updateName/{id}/")
	public ResponseEntity<String> updateEmployeeName(@PathVariable int id, @RequestBody String name){
		
		System.out.println("Name Was:: "+name);
		try {
			if(employeeMasterImpl.updateName(id, name)) {
				return new ResponseEntity<>("Name Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateEmployee/updateEmail/{id}/")
	public ResponseEntity<String> updateEmployeeEmail(@PathVariable int id, @RequestBody String email){
		try {
			if(employeeMasterImpl.updateEmail(id, email)) {
				return new ResponseEntity<>("Email Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateEmployee/updateMobile/{id}/")
	public ResponseEntity<String> updateDoctorMobile(@PathVariable int id, @RequestBody String mobile){
		try {
			if(employeeMasterImpl.updateMobile(id, mobile)) {
				return new ResponseEntity<>("Mobile Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateEmployee/updateGender/{id}/")
	public ResponseEntity<String> updateDoctorGender(@PathVariable int id, @RequestBody Gender gender){
		try {
			if(employeeMasterImpl.updateGender(id, gender)) {
				return new ResponseEntity<>("Gender Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/updateEmployee/updateDesignation/{id}/")
	public ResponseEntity<String> updateDoctorSpecialization(@PathVariable int id, @RequestBody String designation){
		try {
			if(employeeMasterImpl.updateDesignation(id, designation)) {
				return new ResponseEntity<>("Designation Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	@PutMapping("/updateAddress/updateAddress/{id}/")
	public ResponseEntity<String> updateEmployeeAddress(@PathVariable int id, @RequestBody String address){
		try {
			if(employeeMasterImpl.updateAddress(id, address)) {
				return new ResponseEntity<>("Email Updated!!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	
	
	@DeleteMapping("/delete/{id}/")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id){
		try {
			if(employeeMasterImpl.deleteEmployee(id)) {
				return new ResponseEntity<String>("Employee deleted !!", HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("No such id Exists!!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}/")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id){
		Employee employee;
		try {
			employee = employeeMasterImpl.getEmployee(id);
			if(employee!=null) {
				return new ResponseEntity<>(employee, HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/allEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees;
		try {
			
			employees = employeeMasterImpl.getAllEmployees();
			if(employees!=null) {
				return new ResponseEntity<>(employees, HttpStatus.OK);
			}
		} catch (EmployeeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	

}
