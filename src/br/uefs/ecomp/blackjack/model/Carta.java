package br.uefs.ecomp.blackjack.model;

/**
 * Classe responsável por criar cartas que irão posteriormente para baralhos.
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Carta implements Comparable {

    private String naipe;
    private String face;
    private boolean status;
    
    /**
     *
     * @param naipe
     * @param face
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
     *
     * @param status
     */
    public void setStatus(boolean status){
        this.status = status;
    }
    /**
     * Método para retorno do status da carta.
     * @return status - Estado da carta(se está virada ou não).
     */
    public boolean getStatus(){
        return status;
    }
    
    /**
     *
     * @param naipe
     */
    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }
    /**
     * Método para retorno da face da carta.
     * @return face - Pontuação da carta, sendo um número ou uma letra.
     */
    public String getFace() {
        return face;
    }

    /**
     *
     * @param face
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
     *
     * @return
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
            // Fazer do Ás.
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
    public boolean equals(Object objeto) {
        if (objeto instanceof Carta){
            Carta outraCarta = (Carta) objeto;
            return getNaipe().equals(outraCarta.getNaipe()) && getFace().equals(outraCarta.getFace());
        }
       return false;
    }

}
