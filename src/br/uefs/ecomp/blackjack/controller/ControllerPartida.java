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
    private final Croupier croupier;
    private Baralho baralho;
    
    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
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

    public Partida getPartida(){
        return partida;
    }
    //TIPO MALS PELA DEMORA KKK VAMO LÁ
    public ListaEncadeada getJogadoresEmEspera() {
        return jogadoresEmEspera;
    }

    public void setJogadoresEmEspera(ListaEncadeada jogadoresEmEspera) {
        this.jogadoresEmEspera = jogadoresEmEspera;
    }
    
    public Partida iniciarPartida(){
        Pilha baralhoTemp = embaralha(baralho);
        partida = new Partida(jogadoresEmEspera, croupier, baralhoTemp);
        partida.addHistorico("Inicio de Partida");
        partida.addHistorico("Baralho Embaralhado!");
        zerarJogadoresEmEspera();
        return partida;
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
    }

    public Carta[] ordena(Pilha baralho) {
        Carta cartas[] = new Carta[baralho.size()];
        for (int i = 0; i < baralho.size(); i++) {
            cartas[i] = (Carta) baralho.pop();
        }
        quickSort(cartas, 0, baralho.size());
        return cartas;
    }

    private void quickSort(Carta vetor[], int inicio, int fim) {
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && vetor[pe].compareTo(vetor[pivo]) < 0) {
                    pe++;
                }
                while (pe <= pd && vetor[pd].compareTo(vetor[pivo]) > 0) {
                    pd--;
                }
                if (pe <= pd) {
                    swap(vetor, pe, pd);
                    pe++;
                    pd--;
                }
            }
            swap(vetor, pe, pivo);
            quickSort(vetor, inicio, pe - 1);
            quickSort(vetor, pe + 1, fim);
        }

    }

    private void swap(Object[] c, int posUm, int posDois) {
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }

    public void zerarJogadoresEmEspera() {
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
