/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.model;

/**
 *
 * @author Uellington Damasceno
 */
public class Carta implements Comparable {

    private String nipe;
    private String face;

    public Carta(String nipe, String face) {
        this.nipe = nipe;
        this.face = face;
    }

    public String getNipe() {
        return nipe;
    }

    public void setNipe(String nipe) {
        this.nipe = nipe;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return face+ " " + nipe;
    }

    /*
    Não sei se esse metodo é realmente relevante. --- É NECESSARIO VERIFICAR A LOGICA 
    DOS PONTOS EM MÃO.
     */
    public boolean isAz() {
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
        else if(outraCarta.getNipe().equals(this.getNipe())){
            return outraCarta.valorReal(true) > this.valorReal(true) ? 1 : -1; 
        }
        else if(outraCarta.getNipe().compareTo(this.getNipe())<0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Carta){
            Carta outraCarta = (Carta) objeto;
            return getNipe().equals(outraCarta.getNipe()) && getFace().equals(outraCarta.getFace());
        }
       return false;
    }

}
