package com.sc.Nerve.Service;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.Nerve.Entity.Procedure;
import com.sc.Nerve.Entity.User;
import com.sc.Nerve.Repository.ProcedureRepository;
import com.sc.Nerve.Repository.UserRepository;
import com.sc.Nerve.Request.UserProcedureRequest;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private ProcedureRepository procedureRepo;
	
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve customer: " + id, e);
			throw e;
		}
	}
	
	public Iterable<User> getUsers(){
		return repo.findAll();	
	}

	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User addProcedureToUser(Long userId, UserProcedureRequest request) {
		User user = repo.findOne(userId);
		Iterable<Procedure> procedures = procedureRepo.findAll(request.getProcedureIds());
		
		if (user.getProcedures() == null) {
			user.setProcedures(new HashSet<Procedure>());
		}
		
		for (Procedure p : procedures) {
			user.getProcedures().add(p);
			p.setUser(user);
		}
		
		repo.save(user);
		procedureRepo.save(procedures);
		
		return repo.save(user);
	}
	
	
	public User updateUser(User user, Long id) throws Exception {
		try {
			User oldUser = repo.findOne(id);
			oldUser.setName(user.getName());
			oldUser.setUsername(user.getUsername());
			oldUser.setEmail(user.getEmail());
			return repo.save(oldUser);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update user: " + id, e);
			throw new Exception("Unable to update user");
		}
	}
	
	public void deleteUser(Long id)throws Exception{
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete user: " + id, e);
			throw new Exception("Unable to delete user.");
		}
	}
	
}
