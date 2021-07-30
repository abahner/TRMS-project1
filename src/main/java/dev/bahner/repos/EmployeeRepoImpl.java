package dev.bahner.repos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.bahner.models.Employee;
import dev.bahner.util.HibernateUtil;

public class EmployeeRepoImpl implements EmployeeRepo {

	@Override
	public Employee getEmployee(int id) {
		Session sess = HibernateUtil.getSession();
		Employee e = null;
		try {
			e = sess.get(Employee.class, id);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			sess.close();
		}
		return e;
	}

	@Override
	public Employee getEmployee(String name) {
		Session sess = HibernateUtil.getSession();
		Employee e = null;
		try {
			Criteria crit = sess.createCriteria(Employee.class);
			crit.add(Restrictions.eq("username", name));
			
			if (crit.list().size() > 0)
				e = (Employee) crit.list().get(0);
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			sess.close();
		}
		return e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		Session sess = HibernateUtil.getSession();
		List<Employee> employees = null;
		try {
			employees = sess.createQuery("FROM Employee").list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return employees;
	}

	@Override
	public Employee addEmployee(Employee e) {
		Session sess = HibernateUtil.getSession();
		try {
			sess.beginTransaction();
			e.setId((int) sess.save(e));
			sess.getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			sess.getTransaction().rollback();
			e = null;
		} finally {
			sess.close();
		}
		return e;
	}

	@Override
	public Employee updateEmployee(Employee change) {
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
	public Employee deleteEmployee(int id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Employee e = sess.get(Employee.class, id);
		try {
			tx = sess.beginTransaction();
			sess.delete(e);
			tx.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return e;
	}
}
