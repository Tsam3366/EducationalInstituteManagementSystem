package com.example.edu.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name="student")
public class Student
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String sname;
	private String mname;
	private String fname;
	private Date dob;
	private String address;
	private String email;
	private Long mobile;
	private String gender;
	private String courses;
	private String timing;
	private String status;
	private Date admissionDate=new Date(System.currentTimeMillis());
	private String fees="Unpaid";
	public Student() {
		super();
	}
	public Student(String sname, String mname, String fname, Date dob, String address, String email, Long mobile,
			String gender, String courses, String timing, String status, Date admissionDate,String fees) {
		super();
		this.sname = sname;
		this.mname = mname;
		this.fname = fname;
		this.dob = dob;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.courses = courses;
		this.timing = timing;
		this.status = status;
		this.admissionDate = admissionDate;
		this.fees=fees;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", sname=" + sname + ", mname=" + mname + ", fname=" + fname + ", dob=" + dob
				+ ", address=" + address + ", email=" + email + ", mobile=" + mobile + ", gender=" + gender
				+ ", courses=" + courses + ", timing=" + timing + ", status=" + status + ", admissionDate="
				+ admissionDate + "]";
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	
	
}
