package dev.bahner.services;

import java.util.List;

import dev.bahner.models.ReimburseForm;

public interface RFormService {
	
	public ReimburseForm getRForm(int id);
	
	public List<ReimburseForm> getAllRForms();
	
	public ReimburseForm addRForm(ReimburseForm r);
	
	public ReimburseForm updateRForm(ReimburseForm change);
	
	public ReimburseForm deleteRForm(int id);
	
}
