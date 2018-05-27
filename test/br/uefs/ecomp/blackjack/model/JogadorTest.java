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
public class JogadorTest {
    Jogador j;
    @Before
    public void setUp() {
        j = new Jogador("Marcos", "123", 100);
    }
    
    @Test
    public void testeBasico(){
        assertEquals("Marcos", j.getUser());
        assertEquals("123", j.getSenha());
        assertEquals(100, j.getScore());
        
        j.setUser("Maria");
        j.setSenha("321");
        j.setScore(200);
        
        assertEquals("Maria", j.getUser());
        assertEquals("321", j.getSenha());
        assertEquals(300, j.getScore());
        
        Jogador temp = new Jogador("Maria", "321", 200);
        assertTrue(temp.equals(j));
        
        j.setUser("João");
        assertFalse(temp.equals(j));
        
        j.setSenha("132");
        j.setScore(350);
        assertFalse(temp.equals(j));
        
        j.setUser("Maria");
        j.setSenha("132");
        assertFalse(temp.equals(j));
        
        assertFalse(j.ehCroupier());
    }    
}
