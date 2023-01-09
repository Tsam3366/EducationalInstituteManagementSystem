package com.example.edu.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.edu.Model.StudyMaterialModel;
import com.example.edu.Repository.StudyMaterialRepository;

@Service
public class StudyMaterialService {

	@Autowired
	private StudyMaterialRepository filemodelrepo;
	
	public void saveFile(StudyMaterialModel filemodel) {
		// TODO Auto-generated method stub
		filemodelrepo.save(filemodel);
		
	}

	public List<StudyMaterialModel> getAllFiles() {
		// TODO Auto-generated method stub
		return filemodelrepo.findAll();
	}


	public StudyMaterialModel getFile(Long id) {
		// TODO Auto-generated method stub
		return filemodelrepo.findById(id).get();
	}
	public void dlt(Long id) {
		 filemodelrepo.deleteById(id);
	}
	public StudyMaterialModel getbyid(Long id) {
		return filemodelrepo.findById(id).get();
	}

}
