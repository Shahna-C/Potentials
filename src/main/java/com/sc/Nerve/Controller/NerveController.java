package com.sc.Nerve.Controller;



import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sc.Nerve.Entity.Nerve;
import com.sc.Nerve.Service.NerveService;

@RestController
@RequestMapping("/nerves")
public class NerveController {
	
	private static String UPLOADED_FOLDER = "./pictures/";
	
	@Autowired
	private NerveService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getNerves(){
		return new ResponseEntity<Object>(service.getNerves(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createNerve(@RequestBody Nerve nerve) {
		return new ResponseEntity<Object>(service.createNerve(nerve), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateNerve( @RequestBody Nerve nerve, @PathVariable Long id){
		try {
			return new ResponseEntity<Object>(service.updateNerve(nerve, id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update nerve. ", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteNerve(Long id){
		try {
			service.removeNerve(id);
			return new ResponseEntity<Object>("Sucessfully deleted nerve with id: " + id, HttpStatus.OK);
		} catch (Exception e ) {
			return new ResponseEntity<Object>("Unable to delete nerve. ", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}/nervePicture", method = RequestMethod.POST)
	public ResponseEntity<Object> singleFileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<Object>("Please upload a file.", HttpStatus.BAD_REQUEST);
		}
	    try {
	    	String url = UPLOADED_FOLDER + file.getOriginalFilename();
	    	byte[] bytes = file.getBytes();
	    	Path path = Paths.get(url);
	    	Files.write(path, bytes);
	    	return new ResponseEntity<Object>(service.updateNervePicture(id, url), HttpStatus.CREATED);
	    } catch (Exception e) {
	    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	

}
