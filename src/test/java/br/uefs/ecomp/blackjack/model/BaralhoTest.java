package br.uefs.ecomp.blackjack.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class BaralhoTest {
    private Baralho b;
    
    @Before
    public void setUp() {
        b = new Baralho(1);
    }
    
    @Test
    public void testesBasicos(){
        Carta[] aux = b.getCartas();
        
        assertTrue(aux.length == 52);  
        assertEquals(aux[0].getFace(), "A");
        assertEquals(aux[0].getNaipe(), "♦");
        
        assertEquals(aux[13].getFace(), "A");
        assertEquals(aux[13].getNaipe(), "♥");
        
        assertEquals(aux[26].getFace(), "A");
        assertEquals(aux[26].getNaipe(), "♣");
        
        assertEquals(aux[39].getFace(), "A");
        assertEquals(aux[39].getNaipe(), "♠");
        
    }
}
