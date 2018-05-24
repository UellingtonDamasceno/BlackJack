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

    public Partida(ListaEncadeada jogadores, Croupier croupier, Pilha baralho) {
        this.jogadores = jogadores;
        this.croupier = croupier;
        this.baralho = baralho;
        this.historico = new ListaEncadeada();
    }

    public Pilha getBaralho(){
        return baralho;
    }
    public ListaEncadeada getJogadores() {
        return jogadores;
    }

    public ListaEncadeada getHistorico() {
        return historico;
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public void setHistorico(ListaEncadeada historico) {
        this.historico = historico;
    }

    public void iniciar() {
        for (int i = 0; i < 2; i++) {
            Iterador lJogadores = jogadores.iterador();
            addHistorico("Croupier distribuindo " + (i + 1) + "ª rodada de cartas!");
            while (lJogadores.hasNext()) {
                Jogador jogadorAtual = (Jogador) lJogadores.next();
                Carta carta = croupier.daCarta(baralho);
                jogadorAtual.receberCarta(carta);
                addHistorico(jogadorAtual.getUser() + " Recebeu: " + carta);
            }
            Carta carta = croupier.pegarCarta(baralho);
            if (i == 0) {
                addHistorico("Croupier recebeu: " + carta);
            } else {
                carta.setStatus(false);
                addHistorico("Croupier recebeu uma carta virada!");
            }
        }/*
            jogadores.insereFinal(croupier);
            se inserir o croupier na lista de jogadores é necessario zerar a mão dele.
            estou pensando em umas solução para deixar o jogador que está com a vez com maior 
            visibildade na partida.  
            por isso essa parte está comentada. 
        */
        
    }

    public Carta daCarta(Jogador jogador) {
        Carta carta = croupier.daCarta(baralho);
        jogador.receberCarta(carta);
        return carta;
    }

    public void vezDoCroupier() {
        Carta carta;
        addHistorico("Vez do Croupier!");
        carta = croupier.virarCarta();
        addHistorico("O Croupier desvirou a carta: " + carta);
        addHistorico("Agora Croupier possui: " + croupier.pontosEmMao() + "pontos!");
        while (true) {
            if (croupier.querCarta()) {
                carta = croupier.pegarCarta(baralho);
                addHistorico("Croupier pegou: " + carta+"!");
            }else{
                addHistorico("Croupier Finalizou!");
                break;
            }
        }
        //croupier.limparMaoDeCartas();
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

    public Iterador jogadoresEmPartida() {
        return jogadores.iterador();
    }
}
