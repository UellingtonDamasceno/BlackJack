package br.uefs.ecomp.blackjack.model;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */

public class Croupier extends Jogador{
    private MaoDeCarta cartas;
    
    public Croupier(String user, String senha){
        super(user, senha);
        cartas = new MaoDeCarta();
    }
    /*Estava funcionando, caso dê erro voltar para a versão do dia 02/05
    - Modificação: antes uma variavel armazenava a pilha de cartas, 
        agora ela é acessada diretamente*/
    public void setCartas(MaoDeCarta carta){
        this.cartas = carta;
    }
    public MaoDeCarta getCartas (){
        return cartas;
    }
    /*
        Pode ser melhorado fazendo verficações sobre a quanitdade de as 
    e qm sabe calcular a as chances de tirar uma carta favoravel.
    */
    public boolean querCarta(){
        return Croupier.super.pontosEmMao() < 21;
    }
    
}
