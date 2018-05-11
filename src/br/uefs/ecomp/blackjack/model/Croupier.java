/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.Iterador;
import br.uefs.ecomp.blackjack.util.Pilha;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Uellington Damasceno
 */

public class Croupier extends Jogador{
    private MaoDeCarta cartas;
    
    public Croupier(String user, String senha){
        super(user, senha);
        cartas = new MaoDeCarta();
    }
    /*Estava funcionando, caso dê erro voltar para a versão do dia 02/05
    - Modificação: antes uma variavel armazenava a pilha de cartas, 
        agora ela é acessada diretamente*/
    public void setCartas(MaoDeCarta carta){
        this.cartas = carta;
    }
    public MaoDeCarta getCartas (){
        return cartas;
    }
    /*
        Pode ser melhorado fazendo verficações sobre a quanitdade de as 
    e qm sabe calcular a as chances de tirar uma carta favoravel.
    */
    public boolean querCarta(){
        return Croupier.super.pontosEmMao() < 21;
    }
    public Pilha embaralha(Baralho baralho){
        Random gerador = new Random();
        Pilha cartasDoBaralho = new Pilha();
        Carta suporte[] = baralho.getCartas();
        for(int i = 0; i < suporte.length; i++){
            swap(suporte, i, gerador.nextInt(suporte.length -1));
        }
        for(Carta carta : suporte){
            cartasDoBaralho.push(carta);
            System.out.println(cartasDoBaralho.peek());
        }
        return cartasDoBaralho;
    }
    private void swap(Object[] c, int posUm, int posDois){
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }
    public Pilha ordena(Pilha cartas){
        return cartas;
    }
}
