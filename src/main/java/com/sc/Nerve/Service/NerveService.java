package com.sc.Nerve.Service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.Nerve.Entity.Nerve;
import com.sc.Nerve.Repository.NerveRepository;

@Service
public class NerveService {
	
	private static final Logger logger = LogManager.getLogger(NerveService.class);
	
	@Autowired
	private NerveRepository repo;
	
	public Iterable<Nerve> getNerves(){
		return repo.findAll();
	}
	
	public Nerve createNerve(Nerve nerve) {
		return repo.save(nerve);
	}
	
	public Nerve updateNerve(Nerve nerve, Long id) throws Exception {
		try {
			Nerve oldNerve = repo.findOne(id);
			oldNerve.setName(nerve.getName());
			oldNerve.setMuscle(nerve.getMuscle());
			oldNerve.setInnervations(nerve.getInnervations());
			return repo.save(oldNerve);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to update nerve: " + id, e);
			throw new Exception("Unable to update Nerve");
		}
	}
	
	public void removeNerve(Long id)throws Exception{
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception ocurred while trying to delete nerve: " + id, e);
			throw new Exception("unable to delete product");
		}
	}
	
	public Nerve updateNervePicture(Long nerveId, String url) throws Exception {
		Nerve nerve = repo.findOne(nerveId);
		if (nerve == null) {
			throw new Exception("Nerve does not exist.");
		}
		
		nerve.setNervePictureUrl(url);
		return repo.save(nerve);
	}
	

}