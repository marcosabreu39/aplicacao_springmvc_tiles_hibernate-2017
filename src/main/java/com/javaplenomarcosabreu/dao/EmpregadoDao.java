package com.javaplenomarcosabreu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.javaplenomarcosabreu.config.HibernateUtil;
import com.javaplenomarcosabreu.model.Departamento;
import com.javaplenomarcosabreu.model.Empregado;

@Repository
public class EmpregadoDao implements Dao<Empregado> {
	
	public EmpregadoDao(){
		super();
	}

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session session;

	@Override
	public void persist(Empregado empregado) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(empregado);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void merge(Empregado empregado) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.merge(empregado);
		session.getTransaction().commit();
		session.close();
		
	}

	
	@Override
	public void delete(Empregado empregado) throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(empregado);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public boolean searchByAttribute(String attributeName, String attributeValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Empregado> obterTodosEmpregados() throws Exception {

		session = sessionFactory.openSession();
		
		String hql = "FROM Empregado E";

		Query query = session.createQuery(hql);

		List<Empregado> empregados = query.list();

		session.close();
	
		return empregados;

	}
	
	@SuppressWarnings("unchecked")
	public List<Empregado> buscarEmpregados(Departamento codDepartamento) throws Exception {

		session = sessionFactory.openSession();
		
		String hql = "FROM Empregado WHERE codDepartamento = :codDepartamento";

		Query query = session.createQuery(hql);
		
		query.setParameter("codDepartamento", codDepartamento);

		List<Empregado> empregados = query.list();
		
		session.close();
			
		return empregados ;

	}

	
	@SuppressWarnings("unchecked")
	public Empregado buscarEmpregadoViaCpf(String cpf) throws Exception {

		Empregado empregado = null;
		
		session = sessionFactory.openSession();
		
		String hql = "FROM Empregado WHERE cpf = :cpf";

		Query query = session.createQuery(hql);
		
		query.setParameter("cpf", cpf);

		List<Empregado> empregados = query.list();
		
		for (Empregado emp: empregados) {
			empregado = emp;
		}
		
		session.close();
		
		return empregado ;

	}
	
}
