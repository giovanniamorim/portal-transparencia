package com.sindifisco.portal.service.event.planoconta;

import com.sindifisco.portal.model.PlanoConta;

public class PlanoContaSalvaEvent{

	private PlanoConta planoConta;
	
	public PlanoContaSalvaEvent(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}
	
	public PlanoConta getPlanoConta() {
		return planoConta;
	}

}
