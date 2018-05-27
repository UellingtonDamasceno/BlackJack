package br.uefs.ecomp.blackjack.controller;

import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class ControllerPartida {
    private Partida partida;
    private ListaEncadeada jogadoresEmEspera;
    private Baralho baralho;
    private final QuickSort ordena;
    
    /**
     *
     */
    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
        this.ordena = new QuickSort();
    }


    /**
     *
     * @param qtdBaralho
     * @return
     */
    public Baralho criaBaralho(int qtdBaralho) {
        baralho = new Baralho(qtdBaralho);
        return baralho;
    }

    public void iniciarPartida(){
        partida = new Partida(jogadoresEmEspera, embaralha(baralho));
        partida.jogadaInicial();
    }
    
    /**
     *
     * @param user
     * @param senha
     * @param listaDeUser
     * @return
     */
    public int salaDeEspera(String user, String senha, Iterador listaDeUser) {
        while (listaDeUser.hasNext()) {
            Jogador jogadorObtido = (Jogador) listaDeUser.next();
            if (jogadorObtido.getUser().equals(user) && jogadorObtido.getSenha().equals(senha)) {
                if (jogadoresEmEspera.contem(jogadorObtido)) {
                    return 2;
                } else if (jogadorObtido.getScore() >= 10) {
                    jogadoresEmEspera.insereFinal(jogadorObtido);
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     *
     */
    public void zerarSalaDeEspera(){
        while(!jogadoresEmEspera.estaVazia()){
            jogadoresEmEspera.removeInicio();
        }
    }
    
    /**
     *
     * @param baralho
     * @return
     */
    public Pilha embaralha(Baralho baralho) {
        Random gerador = new Random();
        Pilha cartasDoBaralho = new Pilha();
        Carta suporte[] = baralho.getCartas();
        for (int i = 0; i <= suporte.length / 52; i++) {
            for (int j = 0; j < suporte.length; j++) {
                ordena.swap(suporte, j, gerador.nextInt(suporte.length));
            }
        }
        for (Carta carta : suporte) {
            cartasDoBaralho.push(carta);
        }
        return cartasDoBaralho;
    }

    /**
     *
     * @param baralho
     */
    public void ordena(Baralho baralho) {
        ordena.quickSort(baralho.getCartas(), 0, baralho.getCartas().length - 1);
    }

    /**
     *
     * @return
     */
    public Iterador jogadoresEmPartida() {
        return partida.jogadoresEmPartida();
    }
    
    public void vezDoCroupier(){
        partida.vezDoCroupier();
    }
    
    public void finalizarPartida(){
        partida.finalizar();
    }
    
    public void premiacao(){
        partida.premiacao();
    }
    
    public void addHistorico(String info){
        partida.addHistorico(info);
    }
    
    public Object getInfoHistorico(int pos){
        return partida.getInfoHistorico(pos);
    }
    
    public void daCarta(Jogador jogador){
        partida.daCarta(jogador);
    }
    
    public void consideracoes(){
        partida.consideracoes();
    }
}
