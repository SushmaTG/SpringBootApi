package com.architectblogs.service;

import java.util.List;

import com.architectblogs.model.File;

//@Service
public interface StorageService {

	File findById(int id);
	
	File findByName(String name);
	
	void saveFile(File file);
	
	void updateFile(File file);
	
	void deleteById(int id);
	
	void deleteAllFiles();
	
	List<File> findAllFiles();
	
	boolean isFileExist(File file);
	
}
