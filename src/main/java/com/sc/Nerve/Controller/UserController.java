package com.sc.Nerve.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sc.Nerve.Entity.User;
import com.sc.Nerve.Request.UserProcedureRequest;
import com.sc.Nerve.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	private UserService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUser( Long id) {
		try {
			return new ResponseEntity<Object> (service.getUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Object> getUsers(){
    	return new ResponseEntity<Object>(service.getUsers(), HttpStatus.OK);
    }
    
	@RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user){
    	return new ResponseEntity<Object>(service.createUser(user), HttpStatus.CREATED);

    }

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id){
     try {	
    	return new ResponseEntity<Object>(service.updateUser(user, id), HttpStatus.OK);
    } catch (Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
     }
   }
    
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
	 try {
		 service.deleteUser(id);
        return new ResponseEntity<Object>("Sucessfuly deleted user with id: " + id, HttpStatus.OK);
    }catch (Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
  }
	
	//add post for users/{userId}/procedures @RequestBody UserProcedureRequest
	@RequestMapping(value= "/{userId}/procedures", method = RequestMethod.POST)
	public ResponseEntity<Object> addProcedureToUser(@PathVariable Long userId, @RequestBody UserProcedureRequest request) {
		return new ResponseEntity<Object>(service.addProcedureToUser(userId, request), HttpStatus.CREATED);
  }
}
