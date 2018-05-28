package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.Iterador;
import br.uefs.ecomp.blackjack.util.Pilha;

/**
 * Classe responsável por gerar o objeto Croupier, que será o adversário dos jogadores nas partidas.
 * Essa classe é uma subclasse da superclasse Jogador. Herdando assim suas caracteristicas, e tendo características adicionais.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Croupier extends Jogador {

    private MaoDeCarta maoDeCarta;

    /**
     * Construtor da classe Croupier.
     * Aqui o croupier recebe um user e senha, e sua mão de cartas é inicializada.
     */
    public Croupier() {
        super("Croupier", "***", 1000);
        this.maoDeCarta = new MaoDeCarta();
    }
    
    /**
     *  Método que muda o estado da carta.
     * @return carta ou null - se tiver cartas na mão do jogador, ele vira todas elas. Se não, ele retorna nulo.
     */
    public Carta virarCarta() {
        Iterador lCartas = maoDeCarta.getCartas().iterador();
        while (lCartas.hasNext()) {
            Carta carta = (Carta) lCartas.next();
            if (!carta.getStatus()) {
                carta.setStatus(true);
                return carta;
            }
        }
        return null;
    }

    /**
     * Método que verifica se ainda é possível os jogadores receberem mais cartas.
     * @return true ou false, dependendo da quantidade de pontos dos jogadores.
     */
    public boolean querCarta() {
        return Croupier.super.pontosEmMao() <= 17;
    }

    /**
     * Método que faz com que o croupier retire uma carta de um baralho.
     * @param baralho - baralho para os jogos armazenado em uma pilha.
     * @return carta - retorna a carta removida do topo da pilha.
     */
    public Carta pegarCarta(Pilha baralho) {
        Carta carta = (Carta) baralho.pop();
        Croupier.super.receberCarta(carta);
        return carta;
    }

    /**
     * Método que da cartas removidas do baralho pelo croupier.
     * @param baralho - baralho para os jogos armazenado em uma pilha.
     * @return carta na mão do croupier.
     */
    public Carta daCarta(Pilha baralho) {
        return (Carta) baralho.pop();
    }

}
