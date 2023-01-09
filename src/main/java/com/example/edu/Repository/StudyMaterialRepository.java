package com.example.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.edu.Model.StudyMaterialModel;

@Repository
public interface StudyMaterialRepository extends JpaRepository<StudyMaterialModel, Long>{


}
