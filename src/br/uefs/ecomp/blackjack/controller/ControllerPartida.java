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
    
    /**
     *
     */
    public ControllerPartida() {
        this.jogadoresEmEspera = new ListaEncadeada();
    }

    /**
     *
     * @return
     */
    public Baralho getBaralho(){
        return baralho;
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

    /**
     *
     * @return
     */
    public Partida getPartida(){
        return partida;
    }
    
    /**
     *
     * @return
     */
    public Partida iniciarPartida(){
        partida = new Partida(jogadoresEmEspera, embaralha(baralho));
        partida.addHistorico("Inicio de Partida");
        partida.addHistorico("Baralho Embaralhado!");
        return partida;
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
                swap(suporte, j, gerador.nextInt(suporte.length));
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
        quickSort(baralho.getCartas(), 0, baralho.getCartas().length - 1);
    }

    private void quickSort(Carta vetor[], int inicio, int fim) {
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && vetor[pe].compareTo(vetor[pivo]) > 0) {
                    pe++;
                }
                while (pe <= pd && vetor[pd].compareTo(vetor[pivo]) < 0) {
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

    /**
     *
     * @return
     */
    public Iterador jogadoresEmPartida() {
        return jogadoresEmEspera.iterador();
    }
    
    public Iterador winners(){
        return partida.getVencedores().iterador();
    }
    
    public Iterador draw(){
        return partida.getEmpates().iterador();
    }
    
    public Iterador losers(){
        return partida.getPerdedores().iterador();
    }
}
