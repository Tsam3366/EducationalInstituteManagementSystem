package com.example.edu.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.Model.AttendanceModel;
import com.example.edu.Repository.AttendanceRepository;

@Service
public class AttendanceService 
{

	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public void save(List<AttendanceModel> at){
		attendanceRepository.saveAll(at);
	}
	public List<AttendanceModel> findall(){
		return attendanceRepository.findAll();
	}
	public List<AttendanceModel> view(String time,Date date){
		return attendanceRepository.findByTimingAndAdate(time, date);
	}
	public List<AttendanceModel> absent(String email)
	{
		return attendanceRepository.absent(email);
	}
}
