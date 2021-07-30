package dev.bahner.repos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.bahner.models.BenefitsCoordinator;
import dev.bahner.util.HibernateUtil;

public class BenCoordImpl implements BenCoordRepo {

	@Override
	public BenefitsCoordinator getBenCoord(int id) {
		Session sess = HibernateUtil.getSession();
		BenefitsCoordinator b = null;
		try {
			b = sess.get(BenefitsCoordinator.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return b;
	}

	@Override
	public BenefitsCoordinator getBenCoord(String name) {
		Session sess = HibernateUtil.getSession();
		BenefitsCoordinator b = null;
		try {
			Criteria crit = sess.createCriteria(BenefitsCoordinator.class);
			crit.add(Restrictions.eq("username", name));
			if (crit.list().size() > 0)
				b = (BenefitsCoordinator) crit.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BenefitsCoordinator> getAllBenCoords() {
		Session sess = HibernateUtil.getSession();
		List<BenefitsCoordinator> benCoords = null;
		try {
			benCoords = sess.createQuery("FROM BenefitsCoordinator").list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return benCoords;
	}

	@Override
	public BenefitsCoordinator addBenCoord(BenefitsCoordinator b) {
		Session sess = HibernateUtil.getSession();
		try {
			sess.beginTransaction();
			b.setId((int) sess.save(b));
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			b = null;
		} finally {
			sess.close();
		}
		return b;
	}

	@Override
	public BenefitsCoordinator updateBenCoord(BenefitsCoordinator change) {
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
	public BenefitsCoordinator deleteBenCoord(int id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		BenefitsCoordinator b = sess.get(BenefitsCoordinator.class, id);
		try {
			tx = sess.beginTransaction();
			sess.delete(b);
			tx.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return b;
	}

}
