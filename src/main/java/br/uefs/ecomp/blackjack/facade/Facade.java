/*
 * Foi tomado como desnecessário comentar os métodos dos controllers, já que 
 * os métodos do façade estão comentados e o façade chama esses métodos dos 
 * controllers. Devido a isso, os comentários da classe Facade foram deixados 
 * o mais indicativos possível sobre qual controller e quais métodos são 
 * chamados pelo façade.
 */

package br.uefs.ecomp.blackjack.facade;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.IOException;

/**
 * Classe responsável por criar objetos que funcionam como facilitadores de acesso da View para os controllers. No objeto criado por essa classe são instânciados os controllers.
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Facade {

    ControllerArquivos controllerArquivo;
    ControllerPartida controllerPartida;

    /**
     * Construtor da classe Facade. Aqui são instânciados os controllers.
     */
    public Facade() {
        this.controllerArquivo = new ControllerArquivos();
        this.controllerPartida = new ControllerPartida();
    }

    /**
     * Método que chama o método de leitura do controller de arquivos, pegando os dados de um arquivo txt e passando pra uma lista.
     *
     * @return true ou false - se existir um arquivo, ele lê o arquivo e retorna true. Se não, ele cria um arquivo e tenta ler ele. Se for possivel criar o arquivo, esse novo arquivo é lido. Se não, retorna false.
     * @throws IOException - exceções geradas com entrada e saidas de dados.
     */
    public boolean carregarUsers() throws IOException {
        return controllerArquivo.carregarUsers();
    }

    /**
     * Método que chama o método para a criação de um novo jogador no controller de arquivos. Ele cadastra um novo jogador o inserindo em uma lista de users e por fim, o coloca no arquivo de usuarios cadastrados.
     *
     * @param user - String que contém o nome de usuário desejado.
     * @param senha - String que contém a senha desejada para o usuário.
     * @return true ou false - true para cadastro efetuado com sucesso e false se já existir um jogador com o mesmo user.
     * @throws IOException - exceções geradas com entrada e saidas de dados.
     */
    public boolean cadastrarNovoJogador(String user, String senha) throws IOException {
        return controllerArquivo.cadastrarNovoJogador(user, senha);
    }

    /**
     * Método que chama o método do controller de arquivos que retorna a lista de usuários cadastrados.
     *
     * @return lista de usuários.
     */
    public ListaEncadeada getUsers() {
        return controllerArquivo.getUsers();
    }

    /**
     * Método que chama o método do controller de arquivos que retorna um jogador pesquisado.
     *
     * @param user - nome de usuário a ser pesquisado.
     * @param senha - senha do usuário a ser pesquisado.
     * @return O jogador pesquisado ou nulo - nulo se a lista de users estiver vazia, ou o jogador pesquisado não for encontrado nela. Retorna o jogador pesquisado se ele estiver na lista.
     */
    public Jogador obterJogador(String user, String senha) {
        return controllerArquivo.obterJogador(user, senha);
    }

    /**
     * Método que chama o método do controller de arquivos que retorna o iterador que auxilia o percorrer da lista de usuários.
     *
     * @return iterador da lista de usuários.
     */
    public Iterador listaDeUsers() {
        return controllerArquivo.listaDeUsers();
    }

    /**
     * Metodo responsavel por inserir o jogador em uma lista de espera pré partida utilizando o controller da partida.
     *
     * @param user - nome do jogador.
     * @param senha - senha do jogador.
     * @param listaDeUser - lista de usuarios cadastrados no sistema.
     * @return -1 para pontos insuficientes, 1 para inserido com sucesso, 2 para jogador já cadastrado;
     */
    public int inserirJogadorEmPartida(String user, String senha, Iterador listaDeUser) {
        return controllerPartida.salaDeEspera(user, senha, listaDeUser);
    }

    /**
     * Cria um baralho utilizando o controller da partida.
     *
     * @param qtdBaralho - número correspondente a quantidade de baralhos desejado.
     * @return baralho criado.
     */
    public Baralho criarBaralho(int qtdBaralho) {
        return controllerPartida.criaBaralho(qtdBaralho);
    }

    /**
     * Metódo que chama o método de iniciar partida do controller da partida.
     */
    public void iniciarPartida() {
        controllerPartida.iniciarPartida();
    }

    /**
     * Método que chama o método de que dá carta a um jogador do controller da partida.
     *
     * @param jogador - jogador que irá receber a carta.
     */
    public void daCarta(Jogador jogador) {
        controllerPartida.daCarta(jogador);
    }

    /**
     * Método que chama o método do controller de arquivos que retorna o iterador que auxilia o percorrer da lista de jogadores em partida.
     *
     * @return iterador da lista de jogadores em partida.
     */
    public Iterador jogadoresEmPartida() {
        return controllerPartida.jogadoresEmPartida();
    }

    /**
     * Método que chama o método do controller da partida que ordena um baralho.
     *
     * @param baralho - baralho a ser ordenado.
     */
    public void ordena(Baralho baralho) {
        controllerPartida.ordena(baralho);
    }

    /**
     * Método que chama o método do controller da partida que adiciona informações ao histórico da partida.
     *
     * @param info - informação a ser adicionada no histórico.
     */
    public void addHistorico(String info) {
        controllerPartida.addHistorico(info);
    }

    /**
     * Método que chama o método do controller da partida que retorna informações do histórico da partida, dada uma determinada posição.
     *
     * @param pos - posição desejada.
     * @return informação na posição.
     */
    public Object getInfoHistorico(int pos) {
        return controllerPartida.getInfoHistorico(pos);
    }

    /**
     * Método que chama o método do controller da partida que remove todos os jogadores que estão a espera de uma partida.
     */
    public void zerarSalaDeEspera() {
        controllerPartida.zerarSalaDeEspera();
    }
    
    /**
     * Método que chama o método do controller da partida que apaga os conteúdos dos arquivos txt de usuários cadastrados e de pontuação, reescrevendo-os.
     * @return true ou false - true se conseguir abrir os arquivos e reescreve-los, e false se não.
     */
    public boolean atualizarArquivos() {
        return controllerArquivo.atualizarArquivos();
    }
    
    /**
     * Método que chama o método do controller da partida que realiza o turno do croupier.
     */
    public void vezDoCroupier() {
        controllerPartida.vezDoCroupier();
    }
    /**
     * Método que chama o método do controller da partida que auxilia no fim de cada partida, limpando as mãos de cartas dos jogadores e o histórico.
     */
    public void finalizarPartida() {
        controllerPartida.finalizarPartida();
    }
    
    /**
     * Método que chama o método do controller da partida que realiza a premiação adequada a situação dos jogadores nas partidas.
     */
    public void premiacao() {
        controllerPartida.premiacao();
    }

    /**
     * Método que chama o método do controller da partida que exibe o painel final que contém as informações sobre a partida jogada.
     */
    public void consideracoes() {
        controllerPartida.consideracoes();
    }
}
