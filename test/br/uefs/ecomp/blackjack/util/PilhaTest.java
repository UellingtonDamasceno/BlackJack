/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Uellington Damasceno
 */
public class PilhaTest {

    Pilha pilha;
    Object data1, data2, data3;
    @Before
    public void setUp() {
        pilha = new Pilha();
        data1 = "data1";
        data2 = "data2";
        data3 = "data3";
    }

    @Test
    public void testEstaVazia() {
        assertTrue(pilha.isEmpty());
    }

    @Test
    public void testTamanho() {
        assertEquals(0, pilha.size());

        pilha.push(data1);
        assertEquals(1, pilha.size());

        pilha.push(data2);
        pilha.push(data3);
        assertEquals(3, pilha.size());

        pilha.pop();
        assertEquals(2, pilha.size());

        pilha.pop();
        pilha.pop();
        assertEquals(0, pilha.size());
        
        for(int i = 0; i < 100; i++){
            assertEquals(i, pilha.size());
            pilha.push(data1);
        }
    }
    @Test
    public void testPop(){
        pilha.push(data1);
        pilha.push(data2);
        pilha.push(data3);
        pilha.push(data2);
        pilha.push(data1);
        pilha.push(data3);
        
        assertEquals(6, pilha.size());
        assertEquals(data3, pilha.pop());
        
        assertEquals(5, pilha.size());
        assertEquals(data1, pilha.pop());
        
        assertEquals(4, pilha.size());
        assertEquals(data2, pilha.pop());
        
        assertEquals(3, pilha.size());
        assertEquals(data3, pilha.pop());
        
        assertEquals(2, pilha.size());
        assertEquals(data2, pilha.pop());
        
        assertEquals(1, pilha.size());
        assertEquals(data1, pilha.pop());
        
        assertEquals(0, pilha.size());
        assertTrue(pilha.isEmpty());
    }
}
