package com.example.edu.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.Model.Student;
import com.example.edu.Repository.StudentRepository;

@Service
public class StudentService 
{
	@Autowired
	private StudentRepository studentRepository;
	
	public void saveStudent(Student stud) {
		studentRepository.save(stud);
	}
	public Student login(String email,Date date)
	{
		Student stud=studentRepository.findByEmailAndDob(email,date);
		return stud;
	}
	public Student findbymail(String email)
	{
		return studentRepository.findStudentByEmail(email);
	}
	public List<Student> inactive()
	{
		List<Student> stud=studentRepository.findByStatus();
		return stud;
	}
	public List<Student> all()
	{
		List<Student> stud=studentRepository.findAll();
		return stud;
	}
	public List<Student> filter(String course,String time)
	{
		return studentRepository.filterByCourse(course,time);
	}
	public Student getStudById(Long id)
	{
		return studentRepository.findById(id).get();
	}
	public void changeStatus(Long id)
	{
		Student idd=this.getStudById(id);
		idd.setStatus("Active");
		studentRepository.save(idd);
	}
	public String findstatus(String email)
	{
		return studentRepository.findStatus(email);
	}
	public List<Student> findinactivestud() {
		
		return studentRepository.findByStatus();
	}
	public List<Student> findactivestud(){
		return studentRepository.findByActive();
	}
	public List<Student> forAttendance(String timing){
		return studentRepository.forAttendance(timing);
	}
	public List<Student> courseFilter(String courses)
	{
		return studentRepository.filterForCourse(courses);
	}
	public void del(Long id)
	{
		studentRepository.deleteById(id);
	}
	
	public int parichaya()
	{
		return studentRepository.filterParichaya().size();
	}
	public int prathamik()
	{
		return studentRepository.filterPrathamik().size();
	}
	public int madhyama()
	{
		return studentRepository.filterMadhyama().size();
	}
	public int rashtrabhasha()
	{
		return studentRepository.filterRashtrabhasha().size();
	}
	public int praveshika()
	{
		return studentRepository.filterPraveshika().size();
	}
	public int vp()
	{
		return studentRepository.filterVp().size();
	}
	public int Vu()
	{
		return studentRepository.filterVu().size();
	}
	
	public int Pp()
	{
		return studentRepository.filterPp().size();
	}
	public int Pu()
	{
		return studentRepository.filterPu().size();
	}
	public int eLower()
	{
		return studentRepository.filterElower().size();
	}
	public int eHigher()
	{
		return studentRepository.filterEhigher().size();
	}
	public int tlower()
	{
		return studentRepository.filterTlower().size();
	}
	public int tHigher()
	{
		return studentRepository.filterEhigher().size();
	}
	public int coa()
	{
		return studentRepository.filtercoa().size();
	}
}
