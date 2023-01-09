package com.example.edu.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.edu.Model.AttendanceModel;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel , Long>{

	
	List<AttendanceModel> findByTimingAndAdate(String timing,Date date);
	
	@Query(value="select * from attendance where email=? and status='Absent'",nativeQuery=true)
	List<AttendanceModel> absent(String email);
}
