package com.example.edu.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notice")
public class NoticeModel 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date uploadDate=new Date(System.currentTimeMillis());
	private String title;
	private String details;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "NoticeModel [id=" + id + ", title=" + title + ", details=" + details + "]";
	}
	
	public NoticeModel(Long id, Date uploadDate, String title, String details) {
		super();
		this.id = id;
		this.uploadDate = uploadDate;
		this.title = title;
		this.details = details;
	}
	public NoticeModel() {
		super();
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	
}
