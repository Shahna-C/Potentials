package com.sc.Nerve.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sc.Nerve.Entity.Procedure;
import com.sc.Nerve.Entity.User;
import com.sc.Nerve.Request.ProcedureNerveRequest;
import com.sc.Nerve.Service.ProcedureService;
import com.sc.Nerve.Util.ProcedureStatus;


@RestController
@RequestMapping("users/{id}/procedures")
public class ProcedureController {

	@Autowired
	private ProcedureService service;
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getProcedures() {
		try {
			return new ResponseEntity<Object>(service.getProcedures(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{procedureId}", method = RequestMethod.POST)
	public ResponseEntity<Object> createProcedure(@RequestBody Procedure procedure) {
		return new ResponseEntity<Object>(service.createProcedure(procedure), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{procedureId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProceduresById(Long id) {
		try {
			return new ResponseEntity<Object>(service.getProcedures(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

@RequestMapping(method = RequestMethod.PUT)
public ResponseEntity<Object> submitNewProcedure(@RequestBody Set<Long> nerveIds, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.submitNewProcedure(nerveIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping( value = "/{procedureId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProcedure(@RequestBody Procedure procedure, @PathVariable Long procedureId) {
		try {
			if (procedure.getStatus().equals(ProcedureStatus.CANCELLED)) {
				return new ResponseEntity<Object>(service.cancelProcedure(procedureId), HttpStatus.OK);
			} else if(procedure.getStatus().equals(ProcedureStatus.COMPLETED)) {
				return new ResponseEntity<Object>(service.completeProcedure(procedureId), HttpStatus.OK);
			}
			return new ResponseEntity<Object>("Invalid update request. ", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Object> initializeNewProcedure(@RequestBody Set<Long> nerveIds, @RequestBody User user) {
		try {
			return new ResponseEntity<Object>(service.initializeNewProcedure(nerveIds, user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{procedureId}/nerves", method = RequestMethod.POST)
	public ResponseEntity<Object> addNerveToProcedure(@PathVariable Long procedureId, @RequestBody ProcedureNerveRequest request) {
		return new ResponseEntity<Object>(service.addNerveToProcedure(procedureId, request), HttpStatus.CREATED);
	}

}
