package dev.bahner.services;

import java.util.List;

import dev.bahner.models.BenefitsCoordinator;
import dev.bahner.repos.BenCoordRepo;

public class BenCoordServiceImpl implements BenCoordService {

	public BenCoordRepo br;
	
	public BenCoordServiceImpl(BenCoordRepo br) {
		this.br = br;
	}
	
	@Override
	public BenefitsCoordinator getBenCoord(int id) {
		return br.getBenCoord(id);
	}

	@Override
	public BenefitsCoordinator getBenCoord(String name) {
		return br.getBenCoord(name);
	}

	@Override
	public List<BenefitsCoordinator> getAllBenCoords() {
		return br.getAllBenCoords();
	}

	@Override
	public BenefitsCoordinator addBenCoord(BenefitsCoordinator b) {
		return br.addBenCoord(b);
	}

	@Override
	public BenefitsCoordinator updateBenCoord(BenefitsCoordinator change) {
		return br.updateBenCoord(change);
	}

	@Override
	public BenefitsCoordinator deleteBenCoord(int id) {
		return br.deleteBenCoord(id);
	}

}
