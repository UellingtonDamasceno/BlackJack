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
    private Pilha baralho;
    private ListaEncadeada historico;
    
    public Partida (ListaEncadeada jogadores, Croupier croupier, Pilha baralho){
        this.jogadores = jogadores;
        this.croupier = croupier;
        this.baralho = baralho;  
        this.historico = new ListaEncadeada();
    }
    
    public ListaEncadeada getJogadores(){
        return jogadores;
    }
    
    public ListaEncadeada getHistorico() {
        return historico;
    }

    public void setHistorico(ListaEncadeada historico) {
        this.historico = historico;
    } 
   
    public void iniciar(){
        for(int i = 0; i < 2; i++){ 
            Iterador lJogadores = jogadores.iterador(); 
            addHistorico("Croupier distribuindo " + (i + 1) + "ª rodada de cartas!");
            while(lJogadores.hasNext()){
                Jogador jogadorAtual = (Jogador) lJogadores.next();
                Carta carta = croupier.daCarta(baralho);
                jogadorAtual.receberCarta(carta);
                addHistorico(jogadorAtual.getUser() + " Recebeu: " + carta);
            }
            Carta carta = croupier.pegarCarta(baralho);
            if(i == 0){ 
                addHistorico("Croupier recebeu: " + carta);
            }else{
                carta.setStatus(false);
                addHistorico("Croupier recebeu uma carta virada!");
            }
        }
    }
    public Carta daCarta(Jogador jogador){
        Carta carta = croupier.daCarta(baralho);
        jogador.receberCarta(carta);
        return carta;
    }
    
    public void finalizar(){
        
    }
    
    public void addHistorico(String info) {
        historico.insereInicio(info);
    }
    
    public Object getInfoHistorico(int pos) {
        return historico.tamanho() > pos ? historico.get(pos) : "";
    }
    
    public void zerarHistorico() {
        while (!historico.estaVazia()) {
            historico.removeInicio();
        }
    }
    
    public Iterador jogadoresEmPartida(){
        return jogadores.iterador();
    }
}
