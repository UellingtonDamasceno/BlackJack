/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.model;
import br.uefs.ecomp.blackjack.util.Pilha;
/**
 *
 * @author Uellington Damasceno
 */
public class Baralho {
    private Pilha[] baralhos;

    public Baralho(int quantidadeDeBaralho){
        this.baralhos = new Pilha[quantidadeDeBaralho];
        gerarBaralho();
        
    }
    public Pilha[] getBaralhos(){
        return baralhos;
    }
    public void setBaralhos(Pilha[] baralhos){
        this.baralhos = baralhos;
    }
    private void gerarBaralho(){
        String[] nipes = {"♥", "♠", "♦", "♣"};
        String[] faces = {"A","2","3","4","5","6","7","8","9","10", "J", "Q", "K"};
        int id = 0;
        for (int i = 0; i < baralhos.length; i++) {
            baralhos[i] = new Pilha();
            for (String nipe : nipes) {
                for (String face : faces) {
                    Carta carta = new Carta(nipe, face, id++);
                    baralhos[i].push(carta);
                }
            }
        }
    }
}
