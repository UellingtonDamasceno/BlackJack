package br.uefs.ecomp.blackjack.model;

import br.uefs.ecomp.blackjack.util.*;

/**
 * Classe responsável por gerar a partida onde será jogado o BlackJack.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Partida {

    private ListaEncadeada jogadores;
    private ListaEncadeada vencedores;
    private ListaEncadeada perdedores;
    private ListaEncadeada empates;
    private final Croupier croupier;
    private Pilha baralho;
    private ListaEncadeada historico;

    /**
     * Construtor da classe Partida que recebe uma lista de jogadores e o baralho a ser usado na partida.
     * Aqui também é instanciado um croupier, e quatro listas encadeadas: 
     * histórico - onde são armazenadas informações sobre o decorrer da partida.
     * vencedores - lista de jogadores que venceram a partida.
     * perdedores - lista de jogadores que perderam a partida.
     * empates - lista de jogadores que empataram com o croupier na partida.
     * 
     * @param jogadores - lista de jogadores que jogarão a partida.
     * @param baralho - baralho já na pilha, que será usado na partida.
     */
    public Partida(ListaEncadeada jogadores, Pilha baralho) {
        this.jogadores = jogadores;
        this.croupier = new Croupier();
        this.baralho = baralho;
        this.historico = new ListaEncadeada();
        this.vencedores = new ListaEncadeada();
        this.perdedores = new ListaEncadeada();
        this.empates = new ListaEncadeada();
    }

    /**
     * Método que retorna o baralho da partida.
     * @return baralho - pilha contendo as cartas utilizadas em partida.
     */
    public Pilha getBaralho() {
        return baralho;
    }

    /**
     * Método que retorna a lista de jogadores da partida.
     * @return jogadores - lista de jogadores.
     */
    public ListaEncadeada getJogadores() {
        return jogadores;
    }

    /**
     * Método que retorna o histórico da partida.
     * @return historico - lista encadeada que contém as ações acontecidas na partida.
     */
    public ListaEncadeada getHistorico() {
        return historico;
    }

    /**
     * Método que retorna o croupier da partida.
     * @return croupier - adversário dos jogadores e responsável pela administração da partida.
     */
    public Croupier getCroupier() {
        return croupier;
    }

    /**
     * Método realiza a ação inicial na partida, que é a distribuição de 2 cartas e registra as ações no histórico.
     * O método percorre a lista dos jogadores dando a cada um duas cartas viradas para cima, e em seguida dá as mesmas
     * duas cartas ao croupier, só que uma delas é virada para baixo. Após fazer a distribuição das cartas iniciais,
     * o croupier é inserido por último na lista de jogador. As ações principais são registradas no histórico.
     */
    public void jogadaInicial() {
        addHistorico("Inicio de Partida");
        addHistorico("Baralho Embaralhado!");
        for (int i = 0; i < 2; i++) {
            Iterador lJogadores = jogadores.iterador();
            addHistorico("Croupier distribuindo " + (i + 1) + "ª rodada de cartas!");
            while (lJogadores.hasNext()) {
                Jogador jogadorAtual = (Jogador) lJogadores.next();
                Carta carta = croupier.daCarta(baralho);
                jogadorAtual.receberCarta(carta);
                addHistorico(jogadorAtual.getUser() + " Recebeu: " + carta);
            }
            Carta carta = croupier.pegarCarta(baralho);
            if (i == 0) {
                addHistorico("Croupier pegou: " + carta);
            } else {
                carta.setStatus(false);
                addHistorico("Croupier pegou uma carta virada!");
            }
        }
        jogadores.insereFinal(croupier);
    }

    /**
     * Método que limpa o histórico da partida, e a mão dos jogadores.
     * Esse método chama dois outros métodos de limpeza, auxiliando
     * o fim de cada partida.
     */
    public void finalizar() {
        zerarHistorico();
        zerarMaoJogadores();
    }

    /**
     * Método que pega uma carta removida pelo croupier do baralho e dá para um jogador.
     * É adicionada ao histórico que jogador pediu a carta e qual carta ele recebeu.
     * @param jogador - jogador que pediu e irá receber a carta.
     */
    public void daCarta(Jogador jogador) {
        Carta carta = croupier.daCarta(baralho);
        jogador.receberCarta(carta);
        addHistorico(jogador.getUser() + " Pediu carta e recebeu: " + carta);
    }

    /**
     * Método que realiza as ações de jogadas do croupier(dealer), da partida.
     * A carta para baixo do croupier é virada, e é feita a soma dos pontos da sua mão,
     * para verificar até quando ele pode pegar mais cartas. Se ele estourar ou parar,
     * a vez dele acaba. Todas as principais ações são registradas no histórico da partida.
     */
    public void vezDoCroupier() {
        Carta carta;
        addHistorico("Vez do Croupier!");
        carta = croupier.virarCarta();
        addHistorico("O Croupier desvirou a carta: " + carta);
        addHistorico("Agora Croupier possui: " + croupier.pontosEmMao() + " pontos!");
        while (true) {
            if (croupier.querCarta()) {
                carta = croupier.pegarCarta(baralho);
                addHistorico("Croupier pegou: " + carta + "!");
            } else {
                addHistorico("Croupier Finalizou!");
                break;
            }
        }
    }

    /**
     * Método que limpa a mão de cartas dos dos participantes da partida.
     */
    public void zerarMaoJogadores() {
        Iterador lJogadores = jogadores.iterador();
        while (lJogadores.hasNext()) {
            Jogador jogadorAtual = (Jogador) lJogadores.next();
            jogadorAtual.limparMaoDeCartas();
        }
        croupier.limparMaoDeCartas();
    }

    /**
     * Método que adiciona informações ao histórico da partida.
     * @param info - informação a ser adicionada.
     */
    public void addHistorico(String info) {
        historico.insereInicio(info);
    }

    /**
     * Método que recupera a informação no histórico da partida, dada uma posição.
     * @param pos - posição da informação.
     * @return informação na posição. 
     */
    public Object getInfoHistorico(int pos) {
        return historico.tamanho() > pos ? historico.get(pos) : "";
    }
    
    /**
     * Método que limpa o histórico da partida.
     */
    private void zerarHistorico() {
        while (!historico.estaVazia()) {
            historico.removeInicio();
        }
    }
    
    /**
     * Método que chama métodos que geram a tabela final que mostra os vencedores, perdedores e empates da partida.
     */
    public void consideracoes() {
        zerarHistorico();
        listarVencedores();
        listarPerdedores();
        listarEmpates();
    }
    
    /**
     * Método que percorre a lista de vencedores os adicionando junto com suas respectivas pontuações no histórico para exibição.
     * Se a lista estiver vazia, quer dizer que não teve vencedores na partida, então adiciona essa informação ao histórico.
     */
    private void listarVencedores() {
        addHistorico("♥    Pontos do Croupier = " + croupier.pontosEmMao() + "   ♥");
        if (vencedores.estaVazia()) {
            addHistorico("!!!NÃO HOUVE VENCEDORES!!!");
        } else {
            Iterador lVencedores = vencedores.iterador();
            while (lVencedores.hasNext()) {
                Jogador jogadorAtual = (Jogador) lVencedores.next();
                addHistorico(jogadorAtual.getUser() + " = " + jogadorAtual.pontosEmMao());
            }
        }
    }
    
    /**
     * Método que percorre a lista de perdedores os adicionando junto com suas respectivas pontuações no histórico para exibição.
     * Se a lista estiver vazia, quer dizer que não teve perdedores na partida, então adiciona essa informação ao histórico.
     */
    private void listarPerdedores() {
        addHistorico("♣ PERDEDORES E SUAS PONTUAÇÕES ♣");
        if (perdedores.estaVazia()) {
            addHistorico("!!!NÃO HOUVE PERDEDORES!!!");
        } else {
            Iterador lPerdedores = perdedores.iterador();
            while (lPerdedores.hasNext()) {
                Jogador jogadorAtual = (Jogador) lPerdedores.next();
                addHistorico(jogadorAtual.getUser() + " = " + jogadorAtual.pontosEmMao());
            }
        }
    }
    
    /**
     * Método que percorre a lista de empates adicionando os jogadores que empataram junto com suas respectivas pontuações no histórico para exibição.
     * Se a lista estiver vazia, quer dizer que não teve empates na partida, então adiciona essa informação ao histórico.
     */
    private void listarEmpates() {
        addHistorico("♠  EMPATES  E SUAS PONTUAÇÕES  ♠");
        if (empates.estaVazia()) {
            addHistorico("!!!NÃO HOUVERAM EMPATES!!!");
        } else {
            Iterador lEmpates = empates.iterador();
            while (lEmpates.hasNext()) {
                Jogador jogadorAtual = (Jogador) lEmpates.next();
                addHistorico(jogadorAtual.getUser() + " = " + jogadorAtual.pontosEmMao());
            }
        }
    }
    
    /**
     * Método responsável por alterar a pontuação os jogadores e inserir cada jogador que venceu, perdeu e empatou, em listas.
     * Esse método adiciona 10 pontos ao score dos jogadores que venceram, remove 10 pontos dos que perderam, não altera pontuação dos que empataram.
     * Além de inserir cada jogador em uma das três listas os selecionando por suas pontuações.
     * 
     */
    public void premiacao() {
        jogadores.removeUltimo();
        Iterador lJogadores = jogadores.iterador();
        int pontosC = croupier.pontosEmMao();
        Jogador jogadorAtual;
        while (lJogadores.hasNext()) {
            jogadorAtual = (Jogador) lJogadores.next();
            int pontosJ = jogadorAtual.pontosEmMao();
            if (croupier.estourou() && jogadorAtual.estourou()) {
                empates.insereFinal(jogadorAtual);
            } else if (croupier.estourou() && !jogadorAtual.estourou()) {
                jogadorAtual.setScore(10);
                jogadorAtual.setPartidasVencidas(1);
                addHistorico(jogadorAtual.getUser() + " Ganhou: 10 pontos");
                vencedores.insereFinal(jogadorAtual);
            } else if (!croupier.estourou() && jogadorAtual.estourou()) {
                jogadorAtual.setScore(-10);
                perdedores.insereFinal(jogadorAtual);
            } else if (pontosJ < pontosC) {
                jogadorAtual.setScore(-10);
                perdedores.insereFinal(jogadorAtual);
            } else if (pontosJ > pontosC) {
                jogadorAtual.setScore(10);
                jogadorAtual.setPartidasVencidas(1);
                addHistorico(jogadorAtual.getUser() + " Ganhou: 10 pontos");
                vencedores.insereFinal(jogadorAtual);
            } else {
                empates.insereFinal(jogadorAtual);
            }
        }
    }

    /**
     * Método que retorna o iterador da lista de jogadores que estão em partida.
     *
     * @return jogadores.iterador 
     */
    public Iterador jogadoresEmPartida() {
        return jogadores.iterador();
    }
}
