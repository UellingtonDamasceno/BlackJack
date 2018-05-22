package br.uefs.ecomp.blackjack.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class CartaTest{    
    private Carta aux;
    
    @Before
    public void setUp() throws Exception {
        aux = new Carta("♦", "10");
    }
    
    @Test
    public void testesBasicos(){
        assertEquals("♦", aux.getNaipe());
        assertEquals("10", aux.getFace());
        
        aux.setNaipe("♣");
        aux.setFace("A");
        
        assertEquals("♣", aux.getNaipe());
        assertEquals("A", aux.getFace());
        
        String t1 = aux.toString();  
        assertEquals("A ♣",t1);
    }
    
    @Test
    public void valorRealTest(){
        aux.setFace("K");
        int helper = aux.valorReal(false);
        
        assertSame(10, helper);
        
        
    }
}
