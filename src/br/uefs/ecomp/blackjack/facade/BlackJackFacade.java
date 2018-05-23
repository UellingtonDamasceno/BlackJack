/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.facade;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Uellington Damasceno
 */
public class BlackJackFacade {

    ControllerArquivos controllerArquivo;
    ControllerPartida controllerPartida;

    public BlackJackFacade() {
        this.controllerArquivo = new ControllerArquivos();
        this.controllerPartida = new ControllerPartida();
    }

    public boolean carregarUsers(File arquivo) throws IOException {
        return controllerArquivo.carregarUsers(arquivo);
    }

    public boolean cadastrarNovoJogador(File nomeArq, String user, String senha) throws IOException {
        return controllerArquivo.cadastrarNovoJogador(nomeArq, user, senha);
    }

    public ListaEncadeada getUsers() {
        return controllerArquivo.getUsers();
    }
    
    public Object obterJogador(String user, String senha){
        return controllerArquivo.obterJogador(user, senha);
    }
    
    public Iterador listaDeUsers() {
        return controllerArquivo.listaDeUsers();
    }

    public int inserirJogadorEmPartida(String user, String senha, Iterador listaDeUser) {
        return controllerPartida.inserirJogadorEmPartida(user, senha, listaDeUser);
    }
    
    public Baralho getBaralho(){
        return controllerPartida.getBaralho();
    }
    
    public Baralho criarBaralho(int qtdBaralho){
       return controllerPartida.criaBaralho(qtdBaralho);
    }
    
    public Partida iniciarPartida(){
        return controllerPartida.iniciarPartida();
    }
    
    public ListaEncadeada getJogadoresEmPartida() {
        return controllerPartida.getJogadoresEmEspera();
    }
    
    public Carta daCarta(Jogador jogador){
        return controllerPartida.getPartida().daCarta(jogador);
    }
    
    public Iterador jogadoresEmPartida() {
        return controllerPartida.getPartida().jogadoresEmPartida();
    }
    
    public Pilha embaralha(Baralho baralho){
        return controllerPartida.embaralha(baralho);
    }
    
    public void ordena(Baralho baralho){
        controllerPartida.ordena(baralho);
    }
    
    public void addHistorico(String info) {
        controllerPartida.getPartida().addHistorico(info);
    }

    public Object getInfoHistorico(int pos) {
        return controllerPartida.getPartida().getInfoHistorico(pos);
    }

    public void zerarHistorico() {
        controllerPartida.getPartida().zerarHistorico();
    }
    
    public void zerarMaoJogadores(){
        controllerPartida.zerarMaoJogadores();
    }
    
    public void zerarJogadoresEmEspera(){
        controllerPartida.zerarJogadoresEmEspera();
    }

}
