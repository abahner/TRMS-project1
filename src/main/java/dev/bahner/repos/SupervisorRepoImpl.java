package dev.bahner.repos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.bahner.models.Supervisor;
import dev.bahner.util.HibernateUtil;

public class SupervisorRepoImpl implements SupervisorRepo {

	@Override
	public Supervisor getSupervisor(int id) {
		Session sess = HibernateUtil.getSession();
		Supervisor s = null;
		try {
			s = sess.get(Supervisor.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return s;
	}

	@Override
	public Supervisor getSupervisor(String name) {
		Session sess = HibernateUtil.getSession();
		Supervisor s = null;
		try {
			Criteria crit = sess.createCriteria(Supervisor.class);
			crit.add(Restrictions.eq("username", name));
			if (crit.list().size() > 0)
				s = (Supervisor) crit.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supervisor> getAllSupervisors() {
		Session sess = HibernateUtil.getSession();
		List<Supervisor> supervisors = null;
		try {
			supervisors = sess.createQuery("FROM Supervisor").list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return supervisors;
	}

	@Override
	public Supervisor addSupervisor(Supervisor s) {
		Session sess = HibernateUtil.getSession();
		try {
			sess.beginTransaction();
			s.setId((int) sess.save(s));
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			s = null;
		} finally {
			sess.close();
		}
		return s;
	}

	@Override
	public Supervisor updateSupervisor(Supervisor change) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.update(change);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return change;
	}

	@Override
	public Supervisor deleteSupervisor(int id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Supervisor s = sess.get(Supervisor.class, id);
		try {
			tx = sess.beginTransaction();
			sess.delete(s);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return s;
	}

}
