package com.sc.Nerve.Request;

import java.util.List;

public class UserProcedureRequest {
	
	private List<Long> procedureIds;

	public List<Long> getProcedureIds() {
		return procedureIds;
	}

	public void setProcedureIds(List<Long> procedureIds) {
		this.procedureIds = procedureIds;
	}

}
