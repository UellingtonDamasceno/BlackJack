package br.uefs.ecomp.blackjack.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import br.uefs.ecomp.blackjack.util.Pilha;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class CroupierTest {
    
    Carta ca1, ca2, ca3, ca4;
    Croupier c;
    Pilha p;
    Jogador j1;
    
    @Before
    public void setUp() {
        ca1 = new Carta("♣", "4");
        ca2 = new Carta("♦", "10");
        ca3 = new Carta("♥", "9");
        
        c = new Croupier();
        p = new Pilha();
        j1 = new Jogador("Clovis","123", 100);
        
        p.push(ca1);
        p.push(ca2);
        p.push(ca3);

    }
    /**
     * Testes básicos do croupier.
     * Esse método verifica se o croupier criado é realmente um croupier, verifica se ele pega cartas do baralho,
     * verifica se ele pega cartas do baralho e dá para um jogador e verifica se ele pega cartas do baralho para
     * ele mesmo.
     */
    @Test
    public void testeBasico(){
       
        assertTrue(c.ehCroupier());
        assertEquals(ca3, c.daCarta(p));
        
        j1.receberCarta(c.daCarta(p)); 
        assertEquals(10, j1.pontosEmMao());
        
        assertEquals(ca1, c.pegarCarta(p));
         
    }
  
}
