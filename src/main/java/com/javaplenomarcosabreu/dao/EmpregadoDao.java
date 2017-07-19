package com.javaplenomarcosabreu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.javaplenomarcosabreu.config.HibernateUtil;
import com.javaplenomarcosabreu.model.Empregado;

@Repository
public class EmpregadoDao implements Dao<Empregado> {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session session;

	@Override
	public void persist(Empregado empregado) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(empregado);
		session.getTransaction().commit();

	}

	@Override
	public void merge(Empregado t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Empregado t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean searchByAttribute(String attributeName, String attributeValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
