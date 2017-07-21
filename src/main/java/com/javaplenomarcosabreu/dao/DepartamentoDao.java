package com.javaplenomarcosabreu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.javaplenomarcosabreu.config.HibernateUtil;
import com.javaplenomarcosabreu.model.Departamento;

@Repository
public class DepartamentoDao implements Dao<Departamento> {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session session;
	
	@Override
	public void persist(Departamento departamento) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(departamento);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void merge(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Departamento departamento) throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean searchByAttribute(String attributeName, String attributeValue) {
		boolean finded = true;
		
		session = sessionFactory.openSession();
		Query query = session.createQuery("from Usuario where " + attributeName + " = :" + attributeName);
		query.setParameter(attributeName, attributeValue);

		List<Departamento> result = query.list();

		finded = result.isEmpty() ? false : finded;

		session.close();
		
		return finded;

	}


	@SuppressWarnings("unchecked")
	public List<Departamento> obterDepartamentos() throws Exception {

		session = sessionFactory.openSession();

		/*String hql = "SELECT D.nomeDepartamento FROM Departamento D";*/
		String hql = "FROM Departamento D";

		Query query = session.createQuery(hql);

		List<Departamento> departamentos = query.list();

		session.close();
		
		return departamentos;

	}

	@SuppressWarnings("unchecked")
	public boolean checarDepartamento(String nome) throws Exception {

		session = sessionFactory.openSession();
		
		String hql = "FROM Departamento WHERE nomeDepartamento = :nomeDepartamento";

		Query query = session.createQuery(hql);
		
		query.setParameter("nomeDepartamento", nome);

		List<Departamento> checking = query.list();

		boolean existe = checking.isEmpty() ? false : true;

		session.close();
		
		return existe;

	}
	
	@SuppressWarnings("unchecked")
	public Departamento buscarDepartamento(Integer id) throws Exception {

		session = sessionFactory.openSession();
		
		String hql = "FROM Departamento WHERE id = :id";

		Query query = session.createQuery(hql);
		
		query.setParameter("id", id);

		List<Departamento> checking = query.list();
		
		Departamento departamento = new Departamento();
		
		for (Departamento d : checking) {
			
			departamento = d;
		}
		

		session.close();
		
		return departamento ;

	}

}
