package br.uefs.ecomp.blackjack.util;

public interface ILista {

	public boolean estaVazia();
	
	public int tamanho();
	
	public void insereInicio(Object o);

	public void insereFinal(Object o);
	
	public Object removeInicio();

	public Object removeUltimo();
	
        public boolean contem(Object o);
        
        public void set(int indice, Object dado);
        
        public Object get(int indice);
        
	public Iterador iterador();
	
}
