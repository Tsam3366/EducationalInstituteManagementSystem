package com.example.edu.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendance")
public class AttendanceModel
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long aid;
	private Date adate=new Date(System.currentTimeMillis());
	private String sname;
	private String status="Present";
	public String timing;
	public String email;
	public AttendanceModel() {
		super();
	}
	public AttendanceModel(Date adate, String sname,String status,String timing,String email) {
		super();
		this.adate = adate;
		this.sname = sname;
		this.status = status;
		this.timing=timing;
		this.email=email;
	}
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "AttendanceModel [aid=" + aid + ", adate=" + adate + ", sname=" + sname + ", status=" + status
				+ ", timing=" + timing + ", email=" + email + "]";
	}

	
	
}
