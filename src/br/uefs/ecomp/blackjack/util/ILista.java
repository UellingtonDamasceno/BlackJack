package br.uefs.ecomp.blackjack.util;

public interface ILista {

	public boolean estaVazia();
	
	public int tamanho();
	
	public void insereInicio(Object o);

	public void insereFinal(Object o);
	
	public Object removeInicio();

	public Object removeUltimo();
	
        public boolean contem(Object o);
        
	public Iterador iterador();
	
}
