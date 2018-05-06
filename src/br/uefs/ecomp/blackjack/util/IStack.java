
package br.uefs.ecomp.blackjack.util;

/**
 *
 * @author Uellington Damasceno
 */
public interface IStack {
    
    public void push(Object o);
    
    public Object pop();
    
    public Object peek();
    
    public boolean isEmpty();
    
    public int size();
    
    public Iterador iterador();
}
