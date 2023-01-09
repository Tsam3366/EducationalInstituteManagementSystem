package com.example.edu.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.Model.FeesStructure;
import com.example.edu.Model.Student;
import com.example.edu.Repository.FeesStructureRepository;
import com.example.edu.Repository.StudentRepository;


@Service
public class FeesService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private FeesStructureRepository feesStructureRepository;
	
	public FeesService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> filter(String fees,String timing){
		return studentRepository.findByFeesAndTiming(fees,timing);
	}
	
	public void updatefees(Long id)
	{
		Student temp=studentRepository.findById(id).get();
		temp.setFees("Paid");
		studentRepository.save(temp);
	}
	
	public List<FeesStructure> getStruct()
	{
		return feesStructureRepository.findAll();
	}

	public void saveFees(Long id,String fees) {
		FeesStructure temp=feesStructureRepository.findById(id).get();
		temp.setFees(fees);
		feesStructureRepository.save(temp);
	}
}
