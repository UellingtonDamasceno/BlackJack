package br.uefs.ecomp.blackjack.model;
/**
 * Classe responsável por gerar baralhos que irão para a pilha e serão usados em partidas.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Baralho {
    private Carta[] cartas;
    private final int qtdDeCartas;
    
    /**
     * Construtor da classe Baralho que recebe por parâmetro uma quantidade de baralhos especificada.
     * @param qtdDeBaralhos Corresponde a quantidade de baralhos escolhidas para a partida.
     */
    public Baralho(int qtdDeBaralhos){
        this.qtdDeCartas = 52;
        this.cartas = new Carta[qtdDeBaralhos*qtdDeCartas];
        gerarBaralho();
    }
    /**
     * Método responsável por retornar o vetor de cartas do baralho.
     * @return vetor de cartas.
     */
    public Carta[] getCartas(){
        return cartas;
    }
    /**
     * Método responsável por permitir alterar o vetor de cartas do baralho, recebendo um outro vetor substituto.
     * @param cartas vetor que será usado para substituir o vetor de cartas atual;
     */
    public void setCartas(Carta[] cartas){
        this.cartas = cartas;
    }
    /**
     * Método responsável por gerar baralhos com 52 cartas cada, sendo que cada carta contém um naipe e um número ou face.  
     */
    private void gerarBaralho(){
        String[] nipes = {"♦", "♥", "♣", "♠"};
        String[] faces = {"A","2","3","4","5","6","7","8","9","10", "J", "Q", "K"};
        int posicao = 0;
        for (int i = 0; i < cartas.length/qtdDeCartas; i++) {
            for (String nipe : nipes) {
                for (String face : faces) {
                    cartas[posicao++] = new Carta(nipe, face);
                }
            }
        }
    }
}
