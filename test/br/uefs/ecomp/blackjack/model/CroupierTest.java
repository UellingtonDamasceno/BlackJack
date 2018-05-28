/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Uellington Damasceno
 */
public class CroupierTest {
    
    Croupier c;
    Baralho b;
    
    @Before
    public void setUp() {
        c = new Croupier();
        b = new Baralho(1);
    }
    
    @Test
    public void testeBasico(){
        assertTrue(c.ehCroupier());
        
    }
    
}
