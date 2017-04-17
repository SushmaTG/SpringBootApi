package com.architectblogs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.architectblogs.model.File;
import com.architectblogs.repository.FileRepository;

@Service
@Transactional
//@Component
public class StorageServiceImpl implements StorageService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public File findById(int id) {
		// TODO Auto-generated method stub
		return fileRepository.findOne(id);
	}

//	@Override
//	public File findByName(String name) {
//		// TODO Auto-generated method stub
//		return fileRepository.findOne(name);
//	}

	@Override
	public void saveFile(File file) {
		// TODO Auto-generated method stub
		fileRepository.save(file);
	}

	@Override
	public void updateFile(File file) {
		// TODO Auto-generated method stub
		saveFile(file);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		fileRepository.delete(id);
	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub
		fileRepository.deleteAll();
	}

	@Override
	public List<File> findAllFiles() {
		// TODO Auto-generated method stub
		return (List<File>) fileRepository.findAll();
	}

	@Override
	public boolean isFileExist(File file) {
		// TODO Auto-generated method stub
		return findByName(file.getFilename()) != null;
	}

	@Override
	public File findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
