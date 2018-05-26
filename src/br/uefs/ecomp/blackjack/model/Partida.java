package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.*;

/**
 *
 * @author Uellington Damasceno
 */
public class Partida {

    private ListaEncadeada jogadores;
    private final Croupier croupier;
    private Pilha baralho;
    private ListaEncadeada historico;

    /**
     *
     * @param jogadores
     * @param baralho
     */
    public Partida(ListaEncadeada jogadores, Pilha baralho) {
        this.jogadores = jogadores;
        this.croupier = new Croupier();
        this.baralho = baralho;
        this.historico = new ListaEncadeada();
    }

    /**
     *
     * @return
     */
    public Pilha getBaralho() {
        return baralho;
    }

    /**
     *
     * @return
     */
    public ListaEncadeada getJogadores() {
        return jogadores;
    }

    /**
     *
     * @return
     */
    public ListaEncadeada getHistorico() {
        return historico;
    }

    /**
     *
     * @return
     */
    public Croupier getCroupier() {
        return croupier;
    }

    /**
     *
     */
    public void jogadaInicial() {
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
                addHistorico("Croupier pegou: " + carta);
            } else {
                carta.setStatus(false);
                addHistorico("Croupier pegou uma carta virada!");
            }
        }/*
            jogadores.insereFinal(croupier);
            se inserir o croupier na lista de jogadores é necessario zerar a mão dele.
            estou pensando em umas solução para deixar o jogador que está com a vez com maior 
            visibildade na partida.  
            por isso essa parte está comentada. 
         */

    }

    /**
     *
     */
    public void finalizar() {
        zerarHistorico();
        zerarMaoJogadores();
    }

    /**
     *
     * @param jogador
     * @return
     */
    public Carta daCarta(Jogador jogador) {
        Carta carta = croupier.daCarta(baralho);
        jogador.receberCarta(carta);
        return carta;
    }

    /**
     *
     */
    public void vezDoCroupier() {
        Carta carta;
        addHistorico("Vez do Croupier!");
        carta = croupier.virarCarta();
        addHistorico("O Croupier desvirou a carta: " + carta);
        addHistorico("Agora Croupier possui: " + croupier.pontosEmMao() + "pontos!");
        while (true) {
            if (croupier.querCarta()) {
                carta = croupier.pegarCarta(baralho);
                addHistorico("Croupier pegou: " + carta + "!");
            } else {
                addHistorico("Croupier Finalizou!");
                break;
            }
        }
    }

    /**
     *
     */
    public void zerarMaoJogadores() {
        Iterador lJogadores = jogadores.iterador();
        while (lJogadores.hasNext()) {
            Jogador jogadorAtual = (Jogador) lJogadores.next();
            jogadorAtual.limparMaoDeCartas();
        }
        croupier.limparMaoDeCartas();
    }

    /**
     *
     * @param info
     */
    public void addHistorico(String info) {
        historico.insereInicio(info);
    }

    /**
     *
     * @param pos
     * @return
     */
    public Object getInfoHistorico(int pos) {
        return historico.tamanho() > pos ? historico.get(pos) : "";
    }

    private void zerarHistorico() {
        while (!historico.estaVazia()) {
            historico.removeInicio();
        }
    }

    public void premiacao() {
        Iterador lJogadores = jogadores.iterador();
        while (lJogadores.hasNext()) {
            Jogador jogadorAtual = (Jogador) lJogadores.next();
            System.out.println(jogadorAtual);
            if (croupier.venceu()) {
                addHistorico("!!!O CROUPIER ganhou a partida!!!");
                jogadorAtual.setPontos(-5);
            } else if (croupier.estourou() && !jogadorAtual.estourou()) {
                jogadorAtual.setPontos(10);
                addHistorico(jogadorAtual.getUser() + " Ganhou: 10 pontos");
            } else if(!croupier.estourou() && jogadorAtual.pontosEmMao() > croupier.pontosEmMao()){
                jogadorAtual.setPontos(10);
                addHistorico(jogadorAtual.getUser() + "Ganhou: 10 pontos");
            } else if (jogadorAtual.estourou()) {
                addHistorico(jogadorAtual.getUser() + " Estorou e perdeu: 5 pontos");
                jogadorAtual.setPontos(-5);
            } else {
                addHistorico(jogadorAtual.getUser() + "Venceu do Croupier e ganhou: 10 pontos");
                jogadorAtual.setPontos(10);
            }
            jogadorAtual.setPartidas(1);
        }
    }

    /**
     *
     * @return
     */
    public Iterador jogadoresEmPartida() {
        return jogadores.iterador();
    }
}
