package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.*;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno
 */
public class Partida {
    private ListaEncadeada jogadores;
    private Croupier croupier;
    private Baralho baralho;
    
    public Partida (ListaEncadeada jogadores, Croupier croupier, Baralho baralho){
        this.jogadores = jogadores;
        this.croupier = croupier;
        this.baralho = baralho;  
    }
    public void iniciar(){
        jogadores.insereFinal(croupier);
        // Percorrer a lista de jogadores 2 vezes dando uma carta a cada um.
        Iterador itr = jogadores.iterador();
        Jogador jogadorAtual;
        
    }
    public Iterador jogadoresEmPartida(){
        return jogadores.iterador();
    }
}
