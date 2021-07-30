package dev.bahner.repos;

import java.util.List;

import dev.bahner.models.Supervisor;

public interface SupervisorRepo {

	public Supervisor getSupervisor(int id);

	public Supervisor getSupervisor(String name);

	public List<Supervisor> getAllSupervisors();

	public Supervisor addSupervisor(Supervisor s);

	public Supervisor updateSupervisor(Supervisor change);

	public Supervisor deleteSupervisor(int id);
	
}
