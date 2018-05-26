package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.*;
import java.util.Objects;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Jogador implements  Comparable{

    private String user;
    private String senha;
    private int score;
    private int partidas;
    private MaoDeCarta maoDeCartas;
    private final int BLACKJACK;

    /**
     *
     * @param user
     * @param senha
     * @param score
     */
    public Jogador(String user, String senha, int score) {
        this.BLACKJACK = 21;
        this.user = user;
        this.senha = senha;
        this.score = score;
        this.maoDeCartas = new MaoDeCarta();
    }

    /**
     *
     * @param user
     * @param senha
     * @param score
     * @param partidas
     */
    public Jogador(String user, String senha, int score, int partidas) {
        this(user, senha, score);
        this.partidas = partidas;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     *
     * @param partidas
     */
    public void setPartidas(int partidas) {
        this.partidas += partidas;
    }

    /**
     *
     * @param cartas
     */
    public void setMaoDeCartas(MaoDeCarta cartas) {
        this.maoDeCartas = cartas;
    }

    /**
     *
     * @return
     */
    public MaoDeCarta getMaoDeCartas() {
        return maoDeCartas;
    }

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return
     */
    public int getPartidas() {
        return partidas;
    }

    /**
     *
     * @return
     */
    public boolean estourou() {
        return pontosEmMao() > BLACKJACK;
    }

    /**
     *
     * @param carta
     */
    public void receberCarta(Carta carta) {
        maoDeCartas.getCartas().insereFinal(carta);
    }

    /**
     *
     * @return
     */
    public boolean venceu() {
        return pontosEmMao() == BLACKJACK;
    }

    /**
     *
     */
    public void limparMaoDeCartas() {
        while (!maoDeCartas.getCartas().estaVazia()) {
            maoDeCartas.getCartas().removeInicio();
        }
    }

    @Override
    public String toString() {
        return user + " : " + score + " : " + partidas;
    }

    /**
     *
     * @return
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
    
    public boolean ehCroupier(){
        return this instanceof Croupier;
    }
    /**
     *
     * @return
     */
    public Carta virarCarta() {
        Iterador lCartas = maoDeCartas.getCartas().iterador();
        while (lCartas.hasNext()) {
            Carta carta = (Carta) lCartas.next();
            if (!carta.getStatus()) {
                carta.setStatus(true);
                return carta;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        final Jogador other = (Jogador) obj;
        return Objects.equals(this.user, other.user);
    }

    @Override
    public int compareTo(Object o) {
        Jogador outro = (Jogador) o;
        return this.score - outro.score;
    }

}
