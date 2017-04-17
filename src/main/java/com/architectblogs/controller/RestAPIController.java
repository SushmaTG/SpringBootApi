package com.architectblogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.architectblogs.model.File;
import com.architectblogs.service.StorageService;
import com.architectblogs.util.CustomErrorType;

@RestController
@RequestMapping("/")
//@ComponentScan(basePackages={"com.architectblogs.service"})
public class RestAPIController {
	
	@Autowired
	StorageService storageService;

//    
//    public RestAPIController(final StorageService storageService) {
//        this.storageService = storageService;
//    }
//	
	//List<File> files = new ArrayList<File>();
	
//	// redirect to the upload form template
//	/* (non-Javadoc)
//	 * @see com.architectblogs.controller.StorageServiceInterface#uploadedFiles(org.springframework.ui.Model)
//	 */
//	@GetMapping("/")
//	public String uploadedFiles(Model model){
//		return "uploadForm";
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.architectblogs.controller.StorageServiceInterface#handleFileUpload(org.springframework.web.multipart.MultipartFile, org.springframework.ui.Model)
//	 */
//	@PostMapping("/")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model ){
//		// save the file to the database here in try and catch block
//		
//		model.addAttribute("message", "Successfully uploaded" + file.getOriginalFilename() + "!");
//		files.add(file.getOriginalFilename());
//		return "uploadForm";
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.architectblogs.controller.StorageServiceInterface#getFiles(org.springframework.ui.Model)
//	 */
//	@GetMapping("/getFiles")
//	public String getFiles(Model model){
//		model.addAttribute("files", files.stream().map(fileName -> MvcUriComponentsBuilder.
//				fromMethodName(RestAPIController.class, "getFile", fileName).build().toString())
//				.collect(Collectors.toList()));
//		model.addAttribute("totalFiles", "TotalFiles: " + files.size());
//		return "listFiles";
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.architectblogs.controller.StorageServiceInterface#getFile(java.lang.String)
//	 */
//	public ResponseEntity<Resource> getFile(@PathVariable String filename){
//		Resource file = fileRepository.(filename);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
//				file.getFilename() + "\"").body(file);
//	}
	
	@GetMapping("/uploadForm")
	public String uploadPage(Model model){
		return "uploadForm";
	}
	
	// get all the files
	@GetMapping("/")
	public List<File> findAllFiles(){
		return storageService.findAllFiles();
	}
	
	// create a File
	@PostMapping("/")
	public String saveFileUploaded(@RequestBody File file, Model model ){
		if(storageService.isFileExist(file)){
			model.addAttribute("message", "The file already exists");
		}else{
			storageService.saveFile(file);
			model.addAttribute("messsage", "File uploaded successfully");
		}
		return "fileUploadStatus";
	}
	
	// update a File
	@RequestMapping("/updateFile")
	public String updateFile(@RequestParam ("id") int id, Model model, @RequestBody File file){
		File file1 = storageService.findById(id);
		if(file1 == null){
			model.addAttribute("message", "Unable to update the file, the file with id not found");
		}
		file1.setFilename(file.getFilename());
		file1.setFiledata(file.getFiledata());
		file1.setDescription(file.getDescription());
		storageService.updateFile(file1);
		return "/";
	}
	
	// find a File
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{id}")
	public ResponseEntity<?> findFileUploaded(@RequestParam("id") int id, Model model){
		File file = storageService.findById(id);
		if(file == null){
			model.addAttribute("message", "The file with id not found");
			return new ResponseEntity(new CustomErrorType("File with id:" +id+ "not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<File> (file, HttpStatus.OK);
	}
	
	// delete a file
	@DeleteMapping("/{id}")
	public String deleteFile(@RequestParam("id") int id, Model model){
		if(storageService.findById(id) == null){
			model.addAttribute("message", "The file you are looking is not found");
		}else{
			storageService.deleteById(id);
		}
		return "/";
	}
	
	// delete all files
	public ResponseEntity<File> deleteAllFiles(){
		storageService.deleteAllFiles();
		return new ResponseEntity<File>(HttpStatus.NO_CONTENT);
	}
	
}


	











