package dev.bahner.repos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.bahner.models.ReimburseForm;
import dev.bahner.util.HibernateUtil;

public class RFormImpl implements ReimburseFormRepo {

	@Override
	public ReimburseForm getRForm(int id) {
		Session sess = HibernateUtil.getSession();
		ReimburseForm r = null;
		try {
			r = sess.get(ReimburseForm.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return r;
	}

	@Override
	public List<ReimburseForm> getAllRForms() {
		Session sess = HibernateUtil.getSession();
		List<ReimburseForm> rforms = null;
		try {
			rforms = sess.createQuery("FROM ReimburseForm").list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return rforms;
	}

	@Override
	public ReimburseForm addRForm(ReimburseForm r) {
		Session sess = HibernateUtil.getSession();
		try {
			sess.beginTransaction();
			r.setId((int) sess.save(r));
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			r = null;
		} finally {
			sess.close();
		}
		return r;
	}

	@Override
	public ReimburseForm updateRForm(ReimburseForm change) {
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
	public ReimburseForm deleteRForm(int id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		ReimburseForm r = sess.get(ReimburseForm.class, id);
		try {
			tx = sess.beginTransaction();
			sess.delete(r);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return r;
	}

}
