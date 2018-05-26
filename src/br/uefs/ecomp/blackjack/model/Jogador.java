/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int pontos;
    private int partidas;
    private MaoDeCarta maoDeCartas;
    private final int BLACKJACK;

    /**
     *
     * @param user
     * @param senha
     * @param pontos
     */
    public Jogador(String user, String senha, int pontos) {
        this.BLACKJACK = 21;
        this.user = user;
        this.senha = senha;
        this.pontos = pontos;
        this.maoDeCartas = new MaoDeCarta();
    }

    /**
     *
     * @param user
     * @param senha
     * @param pontos
     * @param partidas
     */
    public Jogador(String user, String senha, int pontos, int partidas) {
        this(user, senha, pontos);
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
     * @param pontos
     */
    public void setPontos(int pontos) {
        this.pontos += pontos;
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
    public int getPontos() {
        return pontos;
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
        return user + " : " + pontos + " : " + partidas;
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
        return this.pontos - outro.pontos;
    }

}
