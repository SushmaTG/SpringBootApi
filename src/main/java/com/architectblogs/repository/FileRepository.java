package com.architectblogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.architectblogs.model.File;

@Repository
@Component
public interface FileRepository extends JpaRepository<File, Integer> {
	
//	File findByName(String filename);
	
}
