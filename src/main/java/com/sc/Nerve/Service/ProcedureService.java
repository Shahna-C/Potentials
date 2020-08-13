package com.sc.Nerve.Service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.Nerve.Entity.Nerve;
import com.sc.Nerve.Entity.Procedure;
import com.sc.Nerve.Entity.User;
import com.sc.Nerve.Repository.NerveRepository;
import com.sc.Nerve.Repository.ProcedureRepository;
import com.sc.Nerve.Repository.UserRepository;
import com.sc.Nerve.Request.ProcedureNerveRequest;
import com.sc.Nerve.Util.ProcedureStatus;



@Service
public class ProcedureService {
	
	private static final Logger logger = LogManager.getLogger(ProcedureService.class);
	
	@Autowired
	private NerveRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProcedureRepository procedureRepo;
	
	public Iterable<Procedure> getProcedures(){
		return procedureRepo.findAll();
	}
	
	public Procedure createProcedure(Procedure procedure) {
		return procedureRepo.save(procedure);
	}
	
	
	public Procedure getProceduresById(Long id) throws Exception {
		try {
			return procedureRepo.findOne(id);
		}catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve procedure: " + id, e);
			throw e;
		}
	}
	
	public Procedure submitNewProcedure (Set<Long> nerveIds, Long userId) throws Exception {
		try {
			User user = userRepo.findOne(userId);
			Procedure procedure = initializeNewProcedure(nerveIds, user);
			return procedureRepo.save(procedure);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve user: " + userId, e);
			throw e;
		}
	}
	public Procedure cancelProcedure(Long procedureId) throws Exception {
	try {
		Procedure procedure = procedureRepo.findOne(procedureId);
		procedure.setStatus(ProcedureStatus.CANCELLED);
		return procedureRepo.save(procedure);
	} catch (Exception e) {
		logger.error("Exception occurred whil trying to cancel procedure: " + procedureId, e);
		throw new Exception("Unable to delete procedure.");
	}
}
	
	public Procedure completeProcedure(Long procedureId) throws Exception {
		try {
			Procedure procedure = procedureRepo.findOne(procedureId);
			procedure.setStatus(ProcedureStatus.COMPLETED);
			return procedureRepo.save(procedure);
		} catch (Exception e ) {
			logger.error("Exception occured while trying to complete procedure: " + procedureId, e);;
			throw new Exception ( "Unable to update procedure.");
		}
	}

	
	public Procedure initializeNewProcedure(Set<Long> nerveIds, User user) {
		Procedure procedure = new Procedure();
		procedure.setNerves(convertToNerveSet(repo.findAll(nerveIds)));
		procedure.setUser(user);
		addProcedureToNerves(procedure);
		return procedure;
	}


	public Procedure updateProcedure(Procedure procedure, Long id) throws Exception {
		try {
		Procedure oldProcedure = procedureRepo.findOne(id);
			oldProcedure.setName(procedure.getName());
			return procedureRepo.save(oldProcedure);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update procedure: " + id, e);
			throw new Exception("Unable to update procedure");
		}
	}
	

	
	private void addProcedureToNerves(Procedure procedure ){
		Set<Nerve> nerves = procedure.getNerves();
		for (Nerve nerve : nerves) {
	         nerve.getProcedures().add(procedure);
		}
	}
	
	private Set<Nerve> convertToNerveSet(Iterable<Nerve> iterable) {
		Set<Nerve> set = new HashSet<Nerve>();
		for (Nerve nerve : iterable) {
			set.add(nerve);
		}
		return set;
	}
	

	
	
	public Procedure addNerveToProcedure(Long procedureId, ProcedureNerveRequest request) {
		Procedure procedure = procedureRepo.findOne(procedureId);
		Iterable<Nerve> nerves = repo.findAll(request.getNerveIds());
		
		if (procedure.getNerves() == null) {
			procedure.setNerves(new HashSet<Nerve>());
		}
		
		for (Nerve n : nerves) {
			procedure.getNerves().add(n);
			n.setProcedures(new HashSet<Procedure>());	
				
		}
		
		procedureRepo.save(procedure);
		repo.save(nerves);
		
		return procedureRepo.save(procedure);
		}

	}


