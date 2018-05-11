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
    private Carta[] cartas;
    private final int qtdDeCartas;
    
    public Baralho(int qtdDeBaralhos){
        this.qtdDeCartas = 52;
        this.cartas = new Carta[qtdDeBaralhos*qtdDeCartas];
        gerarBaralho();
    }
    public Carta[] getCartas(){
        return cartas;
    }
    public void setCartas(Carta[] cartas){
        this.cartas = cartas;
    }
    private void gerarBaralho(){
        String[] nipes = {"♥", "♠", "♦", "♣"};
        String[] faces = {"A","2","3","4","5","6","7","8","9","10", "J", "Q", "K"};
        int id = 0;
        for (int i = 0; i < cartas.length/qtdDeCartas; i++) {
            for (String nipe : nipes) {
                for (String face : faces) {
                    cartas[id] = new Carta(nipe, face, id++);
                }
            }
        }
    }
}
