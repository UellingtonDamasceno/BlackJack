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
    public boolean carregarUsers(File arquivo) throws IOException {
        return controllerArquivo.carregarUsers(arquivo);
    }

    /**
     *
     * @param nomeArq
     * @param user
     * @param senha
     * @return
     * @throws IOException
     */
    public boolean cadastrarNovoJogador(File nomeArq, String user, String senha) throws IOException {
        return controllerArquivo.cadastrarNovoJogador(nomeArq, user, senha);
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
     * @return
     */
    public Baralho getBaralho(){
        return controllerPartida.getBaralho();
    }
    
    /**
     *
     * @param qtdBaralho
     * @return
     */
    public Baralho criarBaralho(int qtdBaralho){
       return controllerPartida.criaBaralho(qtdBaralho);
    }
    
    /**
     *
     * @return
     */
    public Partida iniciarPartida(){
        return controllerPartida.iniciarPartida();
    }
    
    /**
     *
     * @return
     */
    public Partida getPartida(){
        return controllerPartida.getPartida();
    }
    
    /**
     *
     * @return
     */
    public ListaEncadeada getJogadoresEmPartida() {
        return controllerPartida.getPartida().getJogadores();
    }
    
    /**
     *
     * @param jogador
     * @return
     */
    public Carta daCarta(Jogador jogador){
        return controllerPartida.getPartida().daCarta(jogador);
    }
    
    /**
     *
     * @param baralho
     * @return
     */
    public Pilha embaralha(Baralho baralho){
        return controllerPartida.embaralha(baralho);
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
        controllerPartida.getPartida().addHistorico(info);
    }

    /**
     *
     * @param pos
     * @return
     */
    public Object getInfoHistorico(int pos) {
        return controllerPartida.getPartida().getInfoHistorico(pos);
    }

    /**
     *
     */
    public void zerarSalaDeEspera(){
        controllerPartida.zerarSalaDeEspera();
    }
    
    public void gravarArquivo(ListaEncadeada temp) throws IOException{
        controllerArquivo.gravarEmArquivo(temp);
    }

    /**
     *
     * @return
     */
    public Iterador jogadoresEmPartida() {
        return controllerPartida.getPartida().jogadoresEmPartida();
    }
    
    public Iterador Vencedores(){
        return controllerPartida.winners();
    }
    
    public Iterador Empates(){
        return controllerPartida.draw();
    }
    
    public Iterador Perdedores(){
        return controllerPartida.losers();
    }
    
}
