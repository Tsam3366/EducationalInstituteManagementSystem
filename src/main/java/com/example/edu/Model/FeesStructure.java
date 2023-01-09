package com.example.edu.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Fees_Management")
public class FeesStructure 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String coursename;
	private String Fees;
	public FeesStructure() {
		super();
	}
	public FeesStructure(Long id, String coursename, String fees) {
		super();
		Id = id;
		this.coursename = coursename;
		Fees = fees;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getFees() {
		return Fees;
	}
	public void setFees(String fees) {
		Fees = fees;
	}
	@Override
	public String toString() {
		return "FeesStructure [Id=" + Id + ", coursename=" + coursename + ", Fees=" + Fees + "]";
	}
	
}
