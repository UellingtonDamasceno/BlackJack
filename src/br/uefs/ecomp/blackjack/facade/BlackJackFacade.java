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

    /**
     *
     */
    public BlackJackFacade() {
        this.controllerArquivo = new ControllerArquivos();
        this.controllerPartida = new ControllerPartida();
    }

    /**
     *
     * @param arquivo
     * @return
     * @throws IOException
     */
    public boolean carregarUsers() throws IOException {
        return controllerArquivo.carregarUsers();
    }

    /**
     *
     * @param user
     * @param senha
     * @return
     * @throws IOException
     */
    public boolean cadastrarNovoJogador(String user, String senha) throws IOException {
        return controllerArquivo.cadastrarNovoJogador(user, senha);
    }

    /**
     *
     * @return
     */
    public ListaEncadeada getUsers() {
        return controllerArquivo.getUsers();
    }
    
    /**
     *
     * @param user
     * @param senha
     * @return
     */
    public Object obterJogador(String user, String senha){
        return controllerArquivo.obterJogador(user, senha);
    }
    
    /**
     *
     * @return
     */
    public Iterador listaDeUsers() {
        return controllerArquivo.listaDeUsers();
    }

    /**
     *
     * @param user
     * @param senha
     * @param listaDeUser
     * @return
     */
    public int inserirJogadorEmPartida(String user, String senha, Iterador listaDeUser) {
        return controllerPartida.salaDeEspera(user, senha, listaDeUser);
    }
    
    /**
     *
     * @param qtdBaralho
     * @return
     */
    public Baralho criarBaralho(int qtdBaralho){
       return controllerPartida.criaBaralho(qtdBaralho);
    }
    
    public void iniciarPartida(){
        controllerPartida.iniciarPartida();
    }  
    
    /**
     *
     * @param jogador
     */
    public void daCarta(Jogador jogador){
        controllerPartida.daCarta(jogador);
    }
    
    /**
     *
     * @return
     */
    public Iterador jogadoresEmPartida() {
        return controllerPartida.jogadoresEmPartida();
    }
    
    /**
     *
     * @param baralho
     */
    public void ordena(Baralho baralho){
        controllerPartida.ordena(baralho);
    }
    
    /**
     *
     * @param info
     */
    public void addHistorico(String info) {
        controllerPartida.addHistorico(info);
    }

    /**
     *
     * @param pos
     * @return
     */
    public Object getInfoHistorico(int pos) {
        return controllerPartida.getInfoHistorico(pos);
    }

    /**
     *
     */
    public void zerarSalaDeEspera(){
        controllerPartida.zerarSalaDeEspera();
    }
    
    public void atualizarArquivos() throws IOException{
        controllerArquivo.atualizarArquivos();
    }
    
    public void vezDoCroupier(){
        controllerPartida.vezDoCroupier();
    }
    
    public void finalizarPartida(){
        controllerPartida.finalizarPartida();
    }
    public void premiacao(){
        controllerPartida.premiacao();
    }
}
