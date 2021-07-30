package dev.bahner.services;

import java.util.List;

import dev.bahner.models.BenefitsCoordinator;

public interface BenCoordService {
	
	public BenefitsCoordinator getBenCoord(int id);
	
	public BenefitsCoordinator getBenCoord(String name);
	
	public List<BenefitsCoordinator> getAllBenCoords();
	
	public BenefitsCoordinator addBenCoord(BenefitsCoordinator b);

	public BenefitsCoordinator updateBenCoord(BenefitsCoordinator change);
	
	public BenefitsCoordinator deleteBenCoord(int id);
}
