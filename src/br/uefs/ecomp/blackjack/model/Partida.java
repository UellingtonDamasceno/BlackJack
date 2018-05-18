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
        embaralha(baralho);
        Jogador jogadorAtual;
        
    }
    public Iterador jogadoresEmPartida(){
        return jogadores.iterador();
    }
    public void embaralha(Baralho baralho){
        Random gerador = new Random();
        Pilha cartasDoBaralho = new Pilha();
        Carta suporte[] = baralho.getCartas();
        for(int i = 0; i < suporte.length; i++){
            swap(suporte, i, gerador.nextInt(suporte.length -1));
            }
        for(Carta carta : suporte){
            cartasDoBaralho.push(carta);
            System.out.println(cartasDoBaralho.peek());
        }
    }
    private void swap(Object[] c, int posUm, int posDois){
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }
    public Pilha ordena(Pilha cartas){
        Object cartasRestantes[] = new Object[cartas.size()];
        for (Object carta : cartasRestantes) {
            carta = cartas.pop();
        }
        return cartas;
    }
}
