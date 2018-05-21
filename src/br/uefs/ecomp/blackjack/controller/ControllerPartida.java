/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private ListaEncadeada historico;
    private Croupier croupier;
    private Pilha baralho; 
    
    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
        this.historico = new ListaEncadeada();
        this.croupier = new Croupier("Croupier", "123");
        this.baralho = new Pilha();
    }
    
    public Croupier getCroupier(){
        return croupier;
    }
    
    public Pilha criaBaralho(int qtdBaralho){
        Baralho b = new Baralho(qtdBaralho);
        return embaralha(b);
    }
    
    public ListaEncadeada getHistorico() {
        return historico;
    }

    public void setHistorico(ListaEncadeada historico) {
        this.historico = historico;
    }

    public ListaEncadeada getJogadoresEmEspera() {
        return jogadoresEmEspera;
    }

    public void setJogadoresEmEspera(ListaEncadeada jogadoresEmEspera) {
        this.jogadoresEmEspera = jogadoresEmEspera;
    }

    public Object getInfoHistorico(int pos) {
        return historico.tamanho() > pos ? historico.get(pos) : "";
    }

    public void addHistorico(String info) {
        historico.insereInicio(info);
    }
    
    public int inserirJogadorEmPartida(String user, String senha, Iterador listaDeUser) {
        while (listaDeUser.hasNext()) {
            Jogador jogadorObtido = (Jogador) listaDeUser.next();
            if (jogadorObtido.getUser().equals(user) && jogadorObtido.getSenha().equals(senha)) {
                if (jogadoresEmEspera.contem(jogadorObtido)) {
                    return 2;
                } else if (jogadorObtido.getPontos() >= 10) {
                    jogadoresEmEspera.insereFinal(jogadorObtido);
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    public Pilha embaralha(Baralho baralho) {
        Random gerador = new Random();
        Pilha cartasDoBaralho = new Pilha();
        Carta suporte[] = baralho.getCartas();
        for (int i = 0; i < suporte.length; i++) {
            swap(suporte, i, gerador.nextInt(suporte.length - 1));
        }
        for (Carta carta : suporte) {
            cartasDoBaralho.push(carta);
        }
        return cartasDoBaralho;
    }

    private void swap(Object[] c, int posUm, int posDois) {
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }

    public Pilha ordena(Pilha cartas) {
        Object cartasRestantes[] = new Object[cartas.size()];
        for (Object carta : cartasRestantes) {
            carta = cartas.pop();
        }
        return cartas;
    }

    public void zerarHistorico() {
        while (!historico.estaVazia()) {
            historico.removeInicio();
        }
    }

    public void zerarJogadoresEmPartida() {
        while (!jogadoresEmEspera.estaVazia()) {
            jogadoresEmEspera.removeInicio();
        }
    }
    
    public void zerarMaoJogadores(){
        Iterador lJogadores = jogadoresEmEspera.iterador();
        while(lJogadores.hasNext()){
            Jogador jogadorAtual = (Jogador) lJogadores.next();
            jogadorAtual.limparMaoDeCartas();
        }
    }

    public Iterador verJogadoresEmPartida() {
        return jogadoresEmEspera.iterador();
    }
}
