package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.ListaEncadeada;

/**
 * Classe responsável por criar objetos que armazenam as cartas que os jogadores tem.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class MaoDeCarta {
    private ListaEncadeada cartas;
    
    /**
     * Construtor da classe MaoDeCarta que inicializa a lista de cartas que ela tem como atributo.
     */
    public MaoDeCarta(){
        this.cartas = new ListaEncadeada();
    }
    
    /**
     * Método que atribui uma lista de cartas as cartas da mão.
     * @param cartas - lista utilizada para a substituição.
     */
    public void setCartas(ListaEncadeada cartas){
        this.cartas = cartas;
    }
    
    /**
     * Método que retorna a lista de cartas contida na mão do jogador.
     * @return cartas - lista de cartas.
     */
    public ListaEncadeada getCartas(){
        return cartas;
    }
       
}
