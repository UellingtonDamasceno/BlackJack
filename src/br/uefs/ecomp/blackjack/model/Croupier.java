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
    public Baralho embaralha(Baralho cartas){
        Random gerador = new Random();
        
        boolean sortearNovamente;
        int numGerado;
        Object[] suporte = new Object[52];
        for (Pilha baralho : cartas.getBaralhos()) {
            for (int i = 0; i < 52; i++) {
                do {
                    sortearNovamente = true;
                    numGerado = gerador.nextInt(52);
                    if (suporte[numGerado] == null) {
                        suporte[numGerado] = baralho.pop();
                        sortearNovamente = false;
                    }
                } while (sortearNovamente);
            }
            for(Object carta : suporte){
                baralho.push(carta);
            }
            Arrays.fill(suporte, 0, suporte.length, null);
        }
        return cartas;
    }
    private Baralho misturaBaralhos(Baralho baralhos){
        
        return baralhos;
    }
    public Pilha ordena(Pilha cartas){
        return cartas;
    }
}
