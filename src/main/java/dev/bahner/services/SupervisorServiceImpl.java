package dev.bahner.services;

import java.util.List;

import dev.bahner.models.Supervisor;
import dev.bahner.repos.SupervisorRepo;

public class SupervisorServiceImpl implements SupervisorService {

	public SupervisorRepo sr;

	public SupervisorServiceImpl(SupervisorRepo sr) {
		this.sr = sr;
	}
	
	@Override
	public Supervisor getSupervisor(int id) {
		return sr.getSupervisor(id);
	}

	@Override
	public Supervisor getSupervisor(String name) {
		return sr.getSupervisor(name);
	}

	@Override
	public List<Supervisor> getAllSupervisors() {
		return sr.getAllSupervisors();
	}

	@Override
	public Supervisor addSupervisor(Supervisor s) {
		return sr.addSupervisor(s);
	}

	@Override
	public Supervisor updateSupervisor(Supervisor change) {
		return sr.updateSupervisor(change);
	}

	@Override
	public Supervisor deleteSupervisor(int id) {
		return sr.deleteSupervisor(id);
	}

}
