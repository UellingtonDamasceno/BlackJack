/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.ListaEncadeada;

/**
 *
 * @author Uellington Damasceno
 */
public class MaoDeCarta {
    private ListaEncadeada cartas;
    
    /**
     *
     */
    public MaoDeCarta(){
        this.cartas = new ListaEncadeada();
    }
    
    /**
     *
     * @param cartas
     */
    public void setCartas(ListaEncadeada cartas){
        this.cartas = cartas;
    }
    
    /**
     *
     * @return
     */
    public ListaEncadeada getCartas(){
        return cartas;
    }
       
}
