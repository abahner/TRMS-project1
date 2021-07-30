package dev.bahner.repos;

import java.util.List;

import dev.bahner.models.ReimburseForm;

public interface ReimburseFormRepo {

	public ReimburseForm getRForm(int id);
	
	public List<ReimburseForm> getAllRForms();
	
	public ReimburseForm addRForm(ReimburseForm r);
	
	public ReimburseForm updateRForm(ReimburseForm change);
	
	public ReimburseForm deleteRForm(int id);
	
}
