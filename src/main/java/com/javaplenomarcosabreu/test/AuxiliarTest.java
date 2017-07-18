package com.javaplenomarcosabreu.test;

import org.junit.Test;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;

import junit.framework.Assert;

public class AuxiliarTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testGerarDataEhoraAtual() {
		Auxiliar aux = new Auxiliar();
		try {
			Assert.assertEquals(null, aux.gerarDataEhoraAtual());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
