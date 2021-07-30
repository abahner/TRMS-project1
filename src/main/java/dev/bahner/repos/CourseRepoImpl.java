package dev.bahner.repos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dev.bahner.models.Course;
import dev.bahner.util.HibernateUtil;

public class CourseRepoImpl implements CourseRepo {

	@Override
	public Course getCourse(int id) {
		Session sess = HibernateUtil.getSession();
		Course c = null;
		try {
			c = sess.get(Course.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return c;
	}

	@Override
	public Course getCourse(String name) {
		Session sess = HibernateUtil.getSession();
		Course c = null;
		try {
			@SuppressWarnings("deprecation")
			Criteria crit = sess.createCriteria(Course.class);
			crit.add(Restrictions.eq("name", name));
			if (crit.list().size() > 0)
				c = (Course) crit.list().get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return c;
	}


	@Override
	public List<Course> getAllCourses() {
		Session sess = HibernateUtil.getSession();
		List<Course> courses = null;
		try {
			courses = sess.createQuery("FROM Course").list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return courses;
	}

	@Override
	public Course addCourse(Course c) {
		Session sess = HibernateUtil.getSession();
		try {
			sess.beginTransaction();
			c.setId((int) sess.save(c));
			sess.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			sess.getTransaction().rollback();
			c = null;
		} finally {
			sess.close();
		}
		return c;
	}

	@Override
	public Course updateCourse(Course change) {
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
	public Course deleteCourse(int id) {
		Session sess = HibernateUtil.getSession();
		Transaction tx = null;
		Course c = sess.get(Course.class, id);
		try {
			tx = sess.beginTransaction();
			sess.delete(c);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return null;
		} finally {
			sess.close();
		}
		return c;
	}

}
