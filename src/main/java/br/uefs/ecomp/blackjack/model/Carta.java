package br.uefs.ecomp.blackjack.model;

import java.util.Objects;

/**
 * Classe responsável por criar cartas que irão posteriormente para baralhos.
 * A classe implementa a interface Comparable para fins de ordenação.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Carta implements Comparable {

    private String naipe;
    private String face;
    private boolean status;
    
    /**
     * Construtor da classe Carta que recebe dois parametros que definem suas características.
     * @param naipe - Símbolo gráfico distintivo que diz a que grupo de cartas essa carta pertence.
     * @param face - Letra ou número que caracterizam o valor de pontuação da carta.
     */
    public Carta(String naipe, String face) {
        this.naipe = naipe;
        this.face = face;
        this.status = true;
    }
    /**
     * Método para retorno de naipe da carta.
     * @return naipe - Símbolo identificador de um grupo de cartas.
     */
    public String getNaipe() {
        return naipe;
    }

    /**
     * Método que atribui um estado à carta.
     * @param status - Atributo que identifica se a carta está virada para cima ou para baixo.
     */
    public void setStatus(boolean status){
        this.status = status;
    }
    /**
     * Método que retorna o estado da carta.
     * @return status - Atributo que identifica se a carta está virada para cima ou para baixo.
     */
    public boolean getStatus(){
        return status;
    }
    
    /**
     * Método que atribui um naipe à carta.
     * @param naipe - Símbolo identificador de um grupo de cartas. 
     */
    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }
    /**
     * Método para retorno da face da carta.
     * @return face - Letra ou número que caracterizam o valor de pontuação da carta.
     */
    public String getFace() {
        return face;
    }

    /**
     * Método que atribui uma face à carta.
     * @param face - Letra ou número que caracterizam o valor de pontuação da carta.
     */
    public void setFace(String face) {
        this.face = face;
    }
    /**
     * Método de identificação do objeto carta. 
     * @return face e nipe (se a carta estiver virada para cima), ou dois x maiúsculos em seus lugares( se a carta estiver virada para baixo).
     */
    @Override
    public String toString() {
        if(status){
            return face+ " " + naipe;
        }
        return "X X";
    }

    /**
     * Método que verifica se a carta atual é um ás.
     * @return true ou false, dependendo do valor da carta.
     */
    public boolean ehAs() {
        return face.equals("A");
    }
    /**
     * Método que verifica e retorna a pontuação da face da carta, ou retorna sua posição no seu naipe(se sua face for uma letra).
     * @param opcao - se for true, quer dizer que o chamador desse método quer saber a pontuação da carta atual, e se for false, sua posição no naipe.
     * @return int - pontuação da carta, ou sua posição no naipe.
     */
    public int valorReal(boolean opcao) {
        switch (face) {
            case "K":
                return opcao ? 10 : 13;
            case "Q":
                return opcao ? 10 : 12;
            case "J":
                return opcao ? 10 : 11;
            case "A":{
                return 1;
            }   
            default:
                return Integer.parseInt(face);
        }
    }
    
    @Override
    public int compareTo(Object objeto) {
        Carta outraCarta = (Carta) objeto;
        if(this.equals(objeto)){
            return 0;
        }
        else if(outraCarta.getNaipe().equals(this.getNaipe())){
            return outraCarta.valorReal(false) > this.valorReal(false) ? 1 : -1; 
        }
        else if(outraCarta.getNaipe().compareTo(this.getNaipe())<0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.naipe);
        hash = 23 * hash + Objects.hashCode(this.face);
        hash = 23 * hash + (this.status ? 1 : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Carta){
            Carta outraCarta = (Carta) objeto;
            return getNaipe().equals(outraCarta.getNaipe()) && getFace().equals(outraCarta.getFace());
        }
       return false;
    }
}
