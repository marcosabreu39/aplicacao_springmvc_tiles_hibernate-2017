package com.javaplenomarcosabreu.test;

import org.junit.Test;

import com.javaplenomarcosabreu.dao.DepartamentoDao;

import junit.framework.Assert;

public class DepartamentoDaoTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testDepartamentos() {
		
		
		DepartamentoDao departamentoDao = new DepartamentoDao();
		
		try {
			Assert.assertEquals("vendas", departamentoDao.obterDepartamentos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
