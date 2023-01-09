package com.example.edu;

import java.util.List;

import com.example.edu.Model.AttendanceModel;

public class AttendanceModelDto {
	
	private List<AttendanceModel> attendances;

	public List<AttendanceModel> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<AttendanceModel> attendances) {
		this.attendances = attendances;
	}

	public AttendanceModelDto(List<AttendanceModel> attendances) {
		super();
		this.attendances = attendances;
	}

	public AttendanceModelDto() {
		super();
	}

	@Override
	public String toString() {
		return "AttendanceModelDto [attendances=" + attendances + "]";
	}
	
	
}
