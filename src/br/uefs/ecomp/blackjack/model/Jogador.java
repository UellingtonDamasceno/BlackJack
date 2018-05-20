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
public class Jogador {
    
    private String user;
    private String senha;
    private int pontos;
    private int partidas;
    private MaoDeCarta maoDeCartas;
    private final int BLACKJACK;
    
    public Jogador(String user, String senha){
        this.BLACKJACK = 21;
        this.user = user;
        this.senha = senha;
        this.maoDeCartas = new MaoDeCarta();
    }   
    public Jogador(String user, String senha, int pontos, int partidas){
        this(user, senha);
        this.pontos = pontos;
        this.partidas = partidas;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setPontos(int pontos){
        this.pontos += pontos;
    }
    public void setPartidas(int partidas){
        this.partidas = partidas;
    }
    public void setMaoDeCarta(MaoDeCarta cartas){
        this.maoDeCartas = cartas;
    }
    public String getUser(){
        return user;
    }
    public String getSenha(){
        return senha;
    }
    public int getPontos(){
        return pontos;
    }
    public int getPartidas(){
        return partidas;
    }
    
    public boolean estourou(){
        return pontosEmMao() > BLACKJACK;
    }
    
    // Ver a questão do Ás aqui.
    public void addCartas(Carta carta){
        ListaEncadeada aux = maoDeCartas.getCartas();
        if(!estourou()){
            aux.insereFinal(carta);    
        }
    }
    public boolean venceu(){
        return pontosEmMao() == BLACKJACK;
    }
    public void limparMaoDeCartas(){
        ListaEncadeada aux = maoDeCartas.getCartas();
        
        while(!aux.estaVazia()){
            aux.removeInicio();
        }
    }
    
    @Override
    public String toString(){
        return user + " : " + pontos + " : " + partidas;
    }
    public int pontosEmMao(){
        int pontosEmMao = 0;
        Iterador lCartas = (maoDeCartas.getCartas()).iterador();
        while(lCartas.hasNext()){
            Carta cartaObtida = (Carta) lCartas.next();
            pontosEmMao += cartaObtida.valorReal(false);
        }
        return pontosEmMao;
    }
    @Override
    public boolean equals(Object obj) {
        final Jogador other = (Jogador) obj;
        return Objects.equals(this.user, other.user);
    }
    
}
