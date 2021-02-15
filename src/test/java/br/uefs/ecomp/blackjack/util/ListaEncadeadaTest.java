package br.uefs.ecomp.blackjack.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class ListaEncadeadaTest {

	ListaEncadeada lista;
	Object data1, data2, data3;

	@Before
	public void setUp() throws Exception {
		lista = new ListaEncadeada();
		data1 = "Data1";
		data2 = "Data2";
		data3 = "Data3";
	}

	@Test
	public void testEstaVazia() {
		assertTrue(lista.estaVazia());
	}

	@Test
	public void testTamanho() {
		assertEquals(0, lista.tamanho());

		lista.insereInicio(data1);
		assertEquals(1, lista.tamanho());

		lista.insereInicio(data2);
		lista.insereInicio(data3);
		assertEquals(3, lista.tamanho());

		lista.removeUltimo();
		assertEquals(2, lista.tamanho());

		lista.removeInicio();
		lista.removeUltimo();
		assertEquals(0, lista.tamanho());
	}

	@Test
	public void testAdicionaInicioRemoveInicio() {
		lista.insereInicio(data1);
		lista.insereInicio(data2);
		lista.insereInicio(data3);
		assertFalse(lista.estaVazia());

		assertSame(data3, lista.removeInicio());
		assertFalse(lista.estaVazia());

		assertSame(data2, lista.removeInicio());
		assertFalse(lista.estaVazia());

		assertSame(data1, lista.removeInicio());
		assertTrue(lista.estaVazia());	
	}

	@Test
	public void testAdicionaInicioRemoveFinal() {
		lista.insereInicio(data1);
		lista.insereInicio(data2);
		lista.insereInicio(data3);
		assertFalse(lista.estaVazia());

		assertSame(data1, lista.removeUltimo());
		assertFalse(lista.estaVazia());

		assertSame(data2, lista.removeUltimo());
		assertFalse(lista.estaVazia());

		assertSame(data3, lista.removeUltimo());
		assertTrue(lista.estaVazia());	
	}

	@Test
	public void testAdicionaFinalRemoveInicio() {
		lista.insereFinal(data1);
		lista.insereFinal(data2);
		lista.insereFinal(data3);
		assertFalse(lista.estaVazia());

		assertSame(data1, lista.removeInicio());
		assertFalse(lista.estaVazia());

		assertSame(data2, lista.removeInicio());
		assertFalse(lista.estaVazia());

		assertSame(data3, lista.removeInicio());
		assertTrue(lista.estaVazia());	
	}

	@Test
	public void testAdicionaFinalRemoveFinal() {
		lista.insereFinal(data1);
		lista.insereFinal(data2);
		lista.insereFinal(data3);
		assertFalse(lista.estaVazia());

		assertSame(data3, lista.removeUltimo());
		assertFalse(lista.estaVazia());

		assertSame(data2, lista.removeUltimo());
		assertFalse(lista.estaVazia());

		assertSame(data1, lista.removeUltimo());
		assertTrue(lista.estaVazia());	
	}

	@Test
	public void testRecupera() {
		lista.insereFinal(data1);
		lista.insereFinal(data2);
		lista.insereInicio(data3);
		assertEquals(data3, lista.get(0));
		assertEquals(data1, lista.get(1));
		assertEquals(data2, lista.get(2));

		lista.removeUltimo();
		assertEquals(data3, lista.get(0));
		assertEquals(data1, lista.get(1));
		
		lista.removeInicio();
		assertEquals(data1, lista.get(0));		
	}
	
	@Test
	public void testIterador() {
		Iterador it = lista.iterador();
		assertFalse(it.hasNext());
		
		lista.insereFinal(data1);
		lista.insereFinal(data2);
		lista.insereInicio(data3);
		it = lista.iterador();
		assertTrue(it.hasNext());
		assertSame(data3, it.next());
		assertTrue(it.hasNext());
		assertSame(data1, it.next());
		assertTrue(it.hasNext());
		assertSame(data2, it.next());
		assertFalse(it.hasNext());
	}

}
