package dev.bahner.services;

import java.util.List;

import dev.bahner.models.ReimburseForm;
import dev.bahner.repos.ReimburseFormRepo;

public class RFormServiceImpl implements RFormService {

	public ReimburseFormRepo rr;
	
	public RFormServiceImpl(ReimburseFormRepo rr) {
		this.rr = rr;
	}
	
	@Override
	public ReimburseForm getRForm(int id) {
		return rr.getRForm(id);
	}

	@Override
	public List<ReimburseForm> getAllRForms() {
		return rr.getAllRForms();
	}

	@Override
	public ReimburseForm addRForm(ReimburseForm r) {
		return rr.addRForm(r);
	}

	@Override
	public ReimburseForm updateRForm(ReimburseForm change) {
		return rr.updateRForm(change);
	}

	@Override
	public ReimburseForm deleteRForm(int id) {
		return rr.deleteRForm(id);
	}

}
