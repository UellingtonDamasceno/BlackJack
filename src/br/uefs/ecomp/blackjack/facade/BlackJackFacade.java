/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.facade;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.util.Iterador;
import br.uefs.ecomp.blackjack.util.ListaEncadeada;
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

    public ListaEncadeada getJogadoresEmPartida() {
        return controllerPartida.getJogadoresEmPartida();
    }

    public Iterador verJogadoresEmPartida() {
        return controllerPartida.verJogadoresEmPartida();
    }

    public void addHistorico(String info) {
        controllerPartida.addHistorico(info);
    }

    public Object getInfoHistorico(int pos) {
        return controllerPartida.getInfoHistorico(pos);
    }

    public void zerarHistorico() {
        controllerPartida.zerarHistorico();
    }

}
