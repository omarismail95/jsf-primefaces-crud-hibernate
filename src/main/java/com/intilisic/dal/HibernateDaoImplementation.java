package com.intilisic.dal;

import java.util.List;
import java.util.Optional;

import org.hibernate.Transaction;

import com.intilisic.core.exception.DaoException;
import com.intilisic.dto.Employee;

import org.hibernate.Session;

public class HibernateDaoImplementation implements Dao<Employee> {

	private Session getCurrentSession() {
		return DatabaseConnectionManager.getCurrentSession();
	}

	@Override
	public Optional<Employee> get(long id) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Employee employee = null;
		try (Session session = getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			employee = session.get(Employee.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return employee != null ? Optional.of(employee) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		List<Employee> listOfEmployee = null;
		try (Session session = getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfEmployee = session.createQuery("from Employee").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfEmployee;

	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(employee);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void update(Employee employee) throws DaoException {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(employee);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DaoException("No Employee With This Id Found");
		}

	}

	@Override
	public void delete(long id) throws DaoException {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try (Session session = getCurrentSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a user object
			Employee employee = session.get(Employee.class, id);
			session.delete(employee);
			System.out.println("Employee is deleted");
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DaoException("No Employee With This Id Found");
		}

	}

}
