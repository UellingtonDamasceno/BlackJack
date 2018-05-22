package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.Pilha;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */

public class Croupier extends Jogador{
    private MaoDeCarta cartas;
    
    public Croupier(String user, String senha){
        super(user, senha, 1000);
        cartas = new MaoDeCarta();
    }
    
    public void setCartas(MaoDeCarta carta){
        this.cartas = carta;
    }
    public MaoDeCarta getCartas (){
        return cartas;
    }
   
    public boolean querCarta(){
        return Croupier.super.pontosEmMao() <= 17;
    }
    
    public void pegarCarta(Pilha baralho){
        Croupier.super.addCartas((Carta) baralho.pop());
    }
    
    public Carta daCarta(Pilha b){
        return (Carta) b.pop();
    }
    
}
