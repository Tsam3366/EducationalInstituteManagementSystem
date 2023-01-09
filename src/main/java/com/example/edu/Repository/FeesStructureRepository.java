package com.example.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.Model.FeesStructure;

@Repository
public interface FeesStructureRepository extends JpaRepository<FeesStructure,Long>{

}
