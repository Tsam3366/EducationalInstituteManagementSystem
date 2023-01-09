package com.example.edu.Model;



import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="studymaterial")
public class StudyMaterialModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private byte[] content;
	private String name;
	private String fileType;
	private String course;
	
	public StudyMaterialModel() {
		super();
	}
	public StudyMaterialModel(byte[] content, String name, String fileType,String course) {
		super();
		this.content = content;
		this.name = name;
		this.fileType = fileType;
		this.course=course;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "StudyMaterialModel [id=" + id + ", content=" + Arrays.toString(content) + ", name=" + name
				+ ", fileType=" + fileType + ", course=" + course + "]";
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	
}
