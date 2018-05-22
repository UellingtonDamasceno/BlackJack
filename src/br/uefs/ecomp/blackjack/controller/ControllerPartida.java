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
    
    private ListaEncadeada jogadoresEmEspera;
    private ListaEncadeada historico;
    private final Croupier croupier;
    private Baralho baralho;
    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
        this.historico = new ListaEncadeada();
        this.croupier = new Croupier("Croupier", "123");
    }

    public Croupier getCroupier() {
        return croupier;
    }
    public Baralho getBaralho(){
        return baralho;
    }
    public Baralho criaBaralho(int qtdBaralho) {
        baralho = new Baralho(qtdBaralho);
        return baralho;
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

    public void ordena(Baralho baralho) {
        quickSort(baralho.getCartas(), 0, baralho.getCartas().length - 1);
        //insertionSort(cartas);
    }

    public void insertionSort(Carta[] cartas) {
        for (int i = 1; i < cartas.length; i++) {
            Carta aux = cartas[i];
            int j = i;
            while ((j > 0) && (cartas[j - 1].compareTo(aux) < 0)) {
                cartas[j] = cartas[j - 1];
                j--;
            }
            cartas[j] = aux;
        }

    }

    private void quickSort(Carta dataset[], int inicio, int fim) {
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && dataset[pe].compareTo(dataset[pivo]) < 0) {
                    pe++;
                }
                while (pe <= pd && dataset[pd].compareTo(dataset[pivo]) > 0) {
                    pd--;
                }
                if (pe <= pd) {
                    swap(dataset, pe, pd);
                    pe++;
                    pd--;
                }
            }
            swap(dataset, pe, pivo);
            quickSort(dataset, inicio, pe - 1);
            quickSort(dataset, pe + 1, fim);
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
