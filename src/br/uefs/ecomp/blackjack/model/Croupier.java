package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.Pilha;

/**
 *
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Croupier extends Jogador {

    private MaoDeCarta maoDeCarta;

    public Croupier() {
        super("Croupier", "***", 1000);
        this.maoDeCarta = new MaoDeCarta();
    }

    public boolean querCarta() {
        return Croupier.super.pontosEmMao() <= 17;
    }

    public Carta pegarCarta(Pilha baralho) {
        Carta carta = (Carta) baralho.pop();
        Croupier.super.receberCarta(carta);
        return carta;
    }

    public Carta daCarta(Pilha baralho) {
        return (Carta) baralho.pop();
    }

}
