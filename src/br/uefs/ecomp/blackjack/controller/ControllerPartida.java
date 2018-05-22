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
    private final Croupier croupier;
    private Pilha baralho;

    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
        this.historico = new ListaEncadeada();
        this.croupier = new Croupier("Croupier", "123");
        this.baralho = new Pilha();
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public Pilha criaBaralho(int qtdBaralho) {
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
        for (int i = 0; i <= suporte.length / 52; i++) {
            for (int j = 0; j < suporte.length; j++) {
                swap(suporte, j, gerador.nextInt(suporte.length));
            }
        }
        for (Carta carta : suporte) {
            cartasDoBaralho.push(carta);
        }
        return cartasDoBaralho;
    }

    public Carta[] ordena(Pilha baralho) {
        Carta cartas[] = new Carta[baralho.size()];
        for (int i = 0; i < baralho.size(); i++) {
            cartas[i] = (Carta) baralho.pop();
        }
        quickSort(cartas, 0, baralho.size());
        return cartas;
    }

    private void quickSort(Object p[], int esquerda, int direita) {
        int esq = esquerda;
        int dir = direita;
        Carta v[] = (Carta[]) p;
        Carta pivo = v[(esq + dir) / 2];
        while (esq <= dir) {
            while (v[esq].compareTo(pivo) == -1) {
                esq++;
            }
            while (v[dir].compareTo(pivo) == 1) {
                dir--;
            }
            if (esq <= dir) {
                swap(v, esq, dir);
                esq += 1;
                dir -= 1;
            }
        }
        if (dir > esquerda) {
            quickSort(v, esquerda, dir);
        }
        if (esq < direita) {
            quickSort(v, esq, direita);
        }
    }

    private void swap(Object[] c, int posUm, int posDois) {
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
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

    public void zerarMaoJogadores() {
        Iterador lJogadores = jogadoresEmEspera.iterador();
        while (lJogadores.hasNext()) {
            Jogador jogadorAtual = (Jogador) lJogadores.next();
            jogadorAtual.limparMaoDeCartas();
        }
    }

    public Iterador jogadoresEmPartida() {
        return jogadoresEmEspera.iterador();
    }
}
