package com.example.edu.Repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.edu.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByEmailAndDob(String email, Date date);

	@Query(value = "Select * from student where status='Inactive'", nativeQuery = true)
	List<Student> findByStatus();
	@Query(value = "Select * from student where status='Active'", nativeQuery = true)
	List<Student> findByActive();
	@Query(value = "select status from student where email=?", nativeQuery = true)
	String findStatus(String email);

	@Query(value = "select * from student where email=?", nativeQuery = true)
	Student findStudentByEmail(String email);

	@Query(value="Select * from student where courses like %?% and timing like %?%", nativeQuery=true)
	List<Student> filterByCourse( String courses,String timing);
	
	@Query(value="Select * from student where courses like %?%", nativeQuery=true)
	List<Student> filterForCourse(String courses);
	
	@Query(value="Select * from student where timing like %?%", nativeQuery=true)
	List<Student> forAttendance(String timing);
	
	@Query(value="select * from student where fees=? and timing like %?%", nativeQuery=true)
	List<Student> findByFeesAndTiming(String fees, String timing);
	
	@Query(value="Select * from student where courses like '%Parichaya%'", nativeQuery=true)
	List<Student> filterParichaya();
	@Query(value="Select * from student where courses like '%Prathamik%'", nativeQuery=true)
	List<Student> filterPrathamik();
	@Query(value="Select * from student where courses like '%Madhyama%'", nativeQuery=true)
	List<Student> filterMadhyama();
	@Query(value="Select * from student where courses like '%Rashtrabhasha%'", nativeQuery=true)
	List<Student> filterRashtrabhasha();
	@Query(value="Select * from student where courses like '%Praveshika%'", nativeQuery=true)
	List<Student> filterPraveshika();
	@Query(value="Select * from student where courses like '%Visharad Poorvardh%'", nativeQuery=true)
	List<Student> filterVp();
	@Query(value="Select * from student where courses like '%Visharad Uttaradh%'", nativeQuery=true)
	List<Student> filterVu();
	@Query(value="Select * from student where courses like '%Praveen Poorvardh%'", nativeQuery=true)
	List<Student> filterPp();
	@Query(value="Select * from student where courses like '%Praveen Uttaradh%'", nativeQuery=true)
	List<Student> filterPu();
	@Query(value="Select * from student where courses like '%English Lower%'", nativeQuery=true)
	List<Student> filterElower();
	@Query(value="Select * from student where courses like '%English Higher%'", nativeQuery=true)
	List<Student> filterEhigher();
	@Query(value="Select * from student where courses like '%Tamil Lower%'", nativeQuery=true)
	List<Student> filterTlower();
	@Query(value="Select * from student where courses like '%Tamil Higher%'", nativeQuery=true)
	List<Student> filterThigher();
	@Query(value="Select * from student where courses like '%C.O.A%'", nativeQuery=true)
	List<Student> filtercoa();

}
