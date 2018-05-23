package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.Pilha;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */

public class Croupier extends Jogador{
    private MaoDeCarta maoDeCarta;
    
    public Croupier(String user, String senha){
        super(user, senha, 1000);
        maoDeCarta = new MaoDeCarta();
    }
    
    public void setMaoDeCarta(MaoDeCarta carta){
        this.maoDeCarta = carta;
    }
    public MaoDeCarta getMaoDeCarta (){
        return maoDeCarta;
    }
   
    public boolean querCarta(){
        return Croupier.super.pontosEmMao() <= 17;
    }
    
    public Carta pegarCarta(Pilha baralho){
        Carta c = (Carta) baralho.pop();
        Croupier.super.receberCarta(c);
        return c;
    }
    
    public Carta daCarta(Pilha b){
        return (Carta) b.pop();
    }
    
}
