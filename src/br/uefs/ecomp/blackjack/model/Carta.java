package br.uefs.ecomp.blackjack.model;

/**
 * Classe responsável por criar cartas que irão posteriormente para baralhos.
 * A classe tem dois atributos: naipe e face;
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Carta implements Comparable {

    private String naipe;
    private String face;

    public Carta(String nipe, String face) {
        this.naipe = nipe;
        this.face = face;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNipe(String naipe) {
        this.naipe = naipe;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return face+ " " + naipe;
    }

    /*
    Não sei se esse metodo é realmente relevante. --- É NECESSARIO VERIFICAR A LOGICA 
    DOS PONTOS EM MÃO.
     */
    public boolean ehAs() {
        return face.equals("A");
    }

    public int valorReal(boolean paraComparar) {
        switch (face) {
            case "K":{
                if(paraComparar){
                    return 13;
                }
                return 10;
            }
            case "Q":{
                if(paraComparar){
                    return 12;
                }
                return 10;
            }
            case "J":{
                if(paraComparar){
                    return 11;
                }
                return 10;
            }
            case "A":
                return 1;
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
            return outraCarta.valorReal(true) > this.valorReal(true) ? 1 : -1; 
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
