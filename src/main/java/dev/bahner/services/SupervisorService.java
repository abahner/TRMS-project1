package dev.bahner.services;

import java.util.List;

import dev.bahner.models.Supervisor;

public interface SupervisorService {
	
	public Supervisor getSupervisor(int id);

	public Supervisor getSupervisor(String name);

	public List<Supervisor> getAllSupervisors();

	public Supervisor addSupervisor(Supervisor s);

	public Supervisor updateSupervisor(Supervisor change);

	public Supervisor deleteSupervisor(int id);

}
