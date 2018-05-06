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
public class Carta {
    private int ordem;
    private String nipe;
    private String face;
    
    public Carta(String nipe, String face, int ordem){
        this.nipe = nipe;
        this.face = face;
        this.ordem = ordem;
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
    public int getOrdem(){
        return ordem;
    }
    @Override
    public String toString(){
        return  "+===========+\n"
                +"| Face: "+face+ "   |\n" 
                +"| Nipe: "+ nipe + "  |\n"+
                "+===========+\n"; 
    }
    /*
    Não sei se esse metodo é realmente relevante. --- É NECESSARIO VERIFICAR A LOGICA 
    DOS PONTOS EM MÃO.
    */
    public boolean isAz(){
        return face.equals("A");
    }
    public int valorReal(){
        switch (face) {
            case "K":
            case "Q":
            case "J":
                return 10;
            case "A":
                return 1;
            default:
                return Integer.parseInt(face);
        }
    }
}
