package br.uefs.ecomp.blackjack.facade;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.IOException;

/**
 * Classe responsável por criar objetos que funcionam como facilitadores de acesso da View para os controllers.
 * No objeto criado por essa classe são instânciados os controllers.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Facade {

    ControllerArquivos controllerArquivo;
    ControllerPartida controllerPartida;

    /**
     * Construtor da classe Facade.
     * Aqui são instânciados os controllers.
     */
    public Facade() {
        this.controllerArquivo = new ControllerArquivos();
        this.controllerPartida = new ControllerPartida();
    }

    /**
     * Método responsável por chamar o método do controller de arquivos responsável por carregar os usuários em 
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
    public Jogador obterJogador(String user, String senha){
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
    
    public boolean atualizarArquivos() {
        return controllerArquivo.atualizarArquivos();
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
 
    public void consideracoes(){
        controllerPartida.consideracoes();
    }
}
