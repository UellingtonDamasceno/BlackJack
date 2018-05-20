/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.controller;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class ControllerPartida {
    private Partida partida;
    private ListaEncadeada jogadoresEmPartida;
    private ListaEncadeada historico;
    
    public ControllerPartida(){
        this.jogadoresEmPartida = new ListaEncadeada();
        this.historico = new ListaEncadeada();
    }
    
    public ListaEncadeada getHistorico(){
        return historico;
    }
    
    public void setHistorico(ListaEncadeada historico){
        this.historico = historico;
    }
    
    public ListaEncadeada getJogadoresEmPartida(){
        return jogadoresEmPartida;
    }
    
    public void setJogadoresEmPartida(ListaEncadeada jogadoresEmPartida){
        this.jogadoresEmPartida = jogadoresEmPartida;
    }
    
    public Object getInfoHistorico(int pos){
        return historico.tamanho() > pos ? historico.get(pos): "";
    }
    
    public void addHistorico(String info){
        historico.insereInicio(info);
    }
    
    public Iterador baralhoUsado(){
        return null;
    }
    
    public int inserirJogadorEmPartida(String user, String senha, Iterador listaDeUser){
        while(listaDeUser.hasNext()){
            Jogador jogadorObtido = (Jogador) listaDeUser.next();
            if(jogadorObtido.getUser().equals(user) && jogadorObtido.getSenha().equals(senha)){
                if(jogadoresEmPartida.contem(jogadorObtido)){
                    return 2;
                }else if(jogadoresEmPartida.tamanho() < 6){
                    jogadoresEmPartida.insereFinal(jogadorObtido);
                    return 1;
                }else{
                    return -1;
                }
            }
        }
        return 0;
    }
    
    public void zerarHistorico(){
        while(!historico.estaVazia()){
            historico.removeInicio();
        }
    }
    
    public Iterador verJogadoresEmPartida(){
        return jogadoresEmPartida.iterador();
    }
}
