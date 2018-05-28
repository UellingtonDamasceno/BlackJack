package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.*;
import java.util.Objects;

/**
 * Classe que cria objetos do tipo Jogador, que serão os participantes da partida.
 * A classe implementa a interface Comparable para fins de ordenação.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Jogador implements  Comparable{

    private String user;
    private String senha;
    private int score;
    private int partidasVencidas;
    private MaoDeCarta maoDeCartas;
    private final int BLACKJACK;

    /**
     * Construtor da classe Jogador que recebe um nome de usuário, uma senha e uma pontuação inicial.
     *
     * @param user - nome de usuário escolhido 
     * @param senha - senha escolhida pelo utilizador do programa.
     * @param score - pontuação inicial padrão recebida pelos usuários.
     */
    public Jogador(String user, String senha, int score) {
        this.BLACKJACK = 21;
        this.user = user;
        this.senha = senha;
        this.score = score;
        this.maoDeCartas = new MaoDeCarta();
    }

    /**
     * Construtor da classe Jogador que recebe um nome de usuário, uma senha, uma pontuação inicial e uma quantidade de partidas vencidas.
     * Esse construtor é usado para ler os jogadores da lista que estão no arquivo .txt.
     * 
     * @param user - nome de usuário escolhido 
     * @param senha - senha escolhida pelo utilizador do programa.
     * @param score - pontuação inicial padrão recebida pelos usuários.
     * @param partidas - partidas vencidas pelo jogador.
     */
    public Jogador(String user, String senha, int score, int partidas) {
        this(user, senha, score);
        this.partidasVencidas = partidas;
    }

    /**
     * Método que atribui um nome de usuário de login ao jogador.
     * @param user - nome desejado para substituição.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Método que atribui uma senha de login ao jogador.
     * @param senha - senha desejada para substituição
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Método que incrementa um valor de pontuação ao jogador.
     * @param score - valor a ser incrementado.
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     * Método que incrementa a quantidade de partidas vencidas pelo jogador.
     *
     * @param quantidade - valor a ser incrementado.
     */
    public void setPartidasVencidas(int quantidade) {
        this.partidasVencidas += quantidade;
    }

    /**
     * Método que atribui uma mão de cartas ao jogador.
     *
     * @param cartas - nova mão de carta .
     */
    public void setMaoDeCartas(MaoDeCarta cartas) {
        this.maoDeCartas = cartas;
    }

    /**
     * Método que retorna a mão de cartas do jogador.
     * @return maoDeCartas - local onde ficam as cartas do jogador.
     */
    public MaoDeCarta getMaoDeCartas() {
        return maoDeCartas;
    }

    /**
     * Método que retorna o nome de usuário do jogador.
     * @return user - string que contém o nome de usuário.
     */
    public String getUser() {
        return user;
    }

    /**
     * Método que retorna a senha de login do jogador.
     * @return senha - string que contém a senha.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Método que retorna a pontuação do jogador.
     * @return score - inteiro que contém a pontuação.
     */
    public int getScore() {
        return score;
    }

    /**
     * Método que retorna a quantidade de partidas ganhas pelo jogador.
     * @return partidasVencidas - inteiro que contém a quantidade de partidas vencidas.
     */
    public int getPartidasVencidas() {
        return partidasVencidas;
    }

    /**
     * Método que retorna se a pontuação das cartas do jogador está além de 21 ou não.
     * @return true ou false - depende da pontuação das cartas.
     */
    public boolean estourou() {
        return pontosEmMao() > BLACKJACK;
    }

    /**
     * Método que insere uma carta recebida na lista de cartas contida na mão de cartas do jogador.
     * @param carta - carta recebida.
     */
    public void receberCarta(Carta carta) {
        maoDeCartas.getCartas().insereFinal(carta);
    }

    /**
     * Método que verifica se o jogador venceu ou não.
     * @return true ou false - depende da pontuação das cartas contidas na mão do jogador. Se for exatamente 21, significa que ele automaticamente
     * ganhou a partida (salvo se croupier tambem ter 21), retornando true. Se não, retorna false.
     */
    public boolean venceu() {
        return pontosEmMao() == BLACKJACK;
    }

    /**
     * Método que remove todas as cartas da mão do jogador.
     */
    public void limparMaoDeCartas() {
        while (!maoDeCartas.getCartas().estaVazia()) {
            maoDeCartas.getCartas().removeInicio();
        }
    }

    @Override
    public String toString() {
        return user + " : " + score + " : " + partidasVencidas;
    }

    /**
     * Método que verifica e retorna a pontuação total das cartas contidas na mão do jogador.
     * @return int - quantidade de pontos.
     */
    public int pontosEmMao() {
        int pontosEmMao = 0, numDeAs = 0;
        Iterador lCartas = (maoDeCartas.getCartas()).iterador();
        while (lCartas.hasNext()) {
            Carta cartaObtida = (Carta) lCartas.next();
            if (cartaObtida.getStatus() && cartaObtida.ehAs()) {
                numDeAs++;
            } else if (cartaObtida.getStatus()) {
                pontosEmMao += cartaObtida.valorReal(true);
            }
        }
        return (pontosEmMao <= 10 && numDeAs >= 1) ? (pontosEmMao + 10 + numDeAs) : pontosEmMao + numDeAs;
    }
    
    /**
     * Método que verifica se o jogador é o croupier.
     * @return true ou false - depende se é ou não.
     */
    public boolean ehCroupier(){
        return this instanceof Croupier;
    }

    @Override
    public boolean equals(Object obj) {
        final Jogador other = (Jogador) obj;
        return this.user.equals(other.user) && this.senha.equals(other.senha);
    }

    @Override
    public int compareTo(Object o) {
        Jogador outro = (Jogador) o;
        return this.score - outro.score;
    }

}
