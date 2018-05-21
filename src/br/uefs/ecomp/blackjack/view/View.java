/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.view;

import br.uefs.ecomp.blackjack.facade.BlackJackFacade;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Uellington Damasceno
 */
public class View {

    static BlackJackFacade blackJackFacade = new BlackJackFacade();

    public static void main(String[] args) throws IOException {
        final int TAMANHO_MENU = 30;
        boolean repetirMenuPrincipal, repetirMenuSalas, repetirCarregarArquivo;
        boolean repetirQtdJogador, repetirRecarga, querCadastrar = false;
        File arquivo = new File("Logins.txt");
        do {
            repetirCarregarArquivo = false;
            try {
                if (!blackJackFacade.carregarUsers(arquivo)) {
                    arquivo.createNewFile();
                    repetirCarregarArquivo = true;
                }
            } catch (IOException ex) {
                repetirCarregarArquivo = false;
                erroCarregarArquivo(TAMANHO_MENU);
                switch (lerInt(true, 1, 2)) {
                    case 1: {
                        mensagem(TAMANHO_MENU, "Digite o nome do arquivo", false);
                        Scanner input = new Scanner(System.in);
                        arquivo = new File(input.nextLine());
                        repetirCarregarArquivo = true;
                        break;
                    }
                    case 2: {
                        mensagem(TAMANHO_MENU, "Até a proxima", false);
                        System.exit(0);
                        break;
                    }
                }
            }
        } while (repetirCarregarArquivo);
        
        int numDeBaralho = 0;
        int qtdJogadores;
        
        do {
            repetirMenuPrincipal = false;
            menuPrincipal(30);
            switch (lerInt(true, 1, 5)) {
                case 1: {
                    repetirMenuPrincipal = cadastrarUser(TAMANHO_MENU, arquivo);
                    break;
                }
                case 2: {
                    do {
                        repetirQtdJogador = false;
                        if (blackJackFacade.getUsers().estaVazia() || querCadastrar) {
                            barra(TAMANHO_MENU, true);
                            textoSimples(TAMANHO_MENU, "!!!ERRO!!!", true, true);
                            textoSimples(TAMANHO_MENU, "Nº Jogadores insuficiente", true, true);
                            mensagem(TAMANHO_MENU, "Deseja cadastar novo jogador?", true);
                            switch (lerInt(true, 1, 2)) {
                                case 1: {
                                    cadastrarUser(TAMANHO_MENU, arquivo);
                                    repetirMenuPrincipal = true;
                                    break;
                                }
                                case 2: {
                                    repetirMenuPrincipal = true;
                                    break;
                                }
                            }
                            querCadastrar = false;
                        } else {
                            menuQuantidadeJogadores(TAMANHO_MENU);
                            qtdJogadores = lerInt(true, 1, 6);
                            if (qtdJogadores == 6) {
                                repetirMenuPrincipal = true;
                            } else if (blackJackFacade.getUsers().tamanho() < qtdJogadores) {
                                repetirQtdJogador = true;
                                querCadastrar = true;
                            } else {
                                do {
                                    repetirMenuSalas = false;
                                    menuRegras(30);
                                    switch (lerInt(true, 1, 5)) {
                                        case 1: {
                                            numDeBaralho = 2;
                                            break;
                                        }
                                        case 2: {
                                            numDeBaralho = 4;
                                            break;
                                        }
                                        case 3: {
                                            numDeBaralho = 8;
                                            break;
                                        }
                                        case 4: {
                                            mensagem(TAMANHO_MENU, "Quantos baralhos?", false);
                                            numDeBaralho = lerInt(true, 1, 0);
                                            break;
                                        }
                                        case 5: {
                                            repetirQtdJogador = true;
                                            break;
                                        }
                                    }
                                    if (!repetirQtdJogador) {
                                        boolean repetirPartida, inserirNovoJogador, repetirFimPartida;
                                        
                                        for (int i = 0; i < qtdJogadores; i++) {
                                            do {
                                                inserirNovoJogador = false;
                                                if (!escolherJogadores(TAMANHO_MENU)) {
                                                    mensagem(TAMANHO_MENU, "Deseja recarregar?", true);
                                                    switch (lerInt(true, 1, 2)) {
                                                        case 1: {
                                                            do {
                                                                repetirRecarga = false;
                                                                switch (menuRecarrega(TAMANHO_MENU)) {
                                                                    case -1: {
                                                                        inserirNovoJogador = true;
                                                                        break;
                                                                    }
                                                                    case 0: {
                                                                        barra(TAMANHO_MENU, true);
                                                                        textoSimples(TAMANHO_MENU, "Sucesso!", true, true);
                                                                        mensagem(TAMANHO_MENU, "Recarregar novamente?", true);
                                                                        switch (lerInt(true, 1, 2)) {
                                                                            case 1: {
                                                                                repetirRecarga = true;
                                                                                break;
                                                                            }
                                                                            case 2: {
                                                                                inserirNovoJogador = true;
                                                                                break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                }
                                                            } while (repetirRecarga);
                                                            break;
                                                        }
                                                        case 2: {
                                                            inserirNovoJogador = true;
                                                            break;
                                                        }
                                                    }

                                                }
                                            } while (inserirNovoJogador);
                                        }
                                        
                                        
                                        do {
                                            repetirPartida = false;
                                            partida(TAMANHO_MENU, blackJackFacade.verJogadoresEmPartida(), numDeBaralho);
                                            do {
                                                repetirFimPartida = false;
                                                blackJackFacade.zerarMaoJogadores();
                                                blackJackFacade.zerarHistorico();
                                                menuFimPartida(TAMANHO_MENU);
                                                switch (lerInt(true, 1, 4)) {
                                                    case 1: {

                                                        break;
                                                    }
                                                    case 2: {
                                                        repetirPartida = true;
                                                        break;
                                                    }
                                                    case 3: {
                                                        repetirQtdJogador = true;
                                                        blackJackFacade.zerarJogadoresEmPartida();

                                                        break;
                                                    }
                                                    case 4: {
                                                        blackJackFacade.zerarJogadoresEmPartida();
                                                        repetirMenuPrincipal = true;
                                                        break;
                                                    }
                                                }
                                            } while (repetirFimPartida);
                                        } while (repetirPartida);
                                    }
                                } while (repetirMenuSalas);
                            }
                        }
                    } while (repetirQtdJogador);

                    break;
                }
                case 3: {
                    Iterador lJogadores = blackJackFacade.listaDeUsers();
                    while (lJogadores.hasNext()) {
                        Jogador jogadorObtido = (Jogador) lJogadores.next();
                        System.out.println(jogadorObtido);
                    }
                    repetirMenuPrincipal = true;
                    break;
                }
                case 4: {
                    repetirMenuPrincipal = true;
                    break;
                }
                case 5: {
                    mensagem(TAMANHO_MENU, "Realmente deseja sair?", true);
                    switch (lerInt(true, 1, 2)) {
                        case 1: {
                            mensagem(TAMANHO_MENU, "Até a proxima", false);
                            System.exit(0);
                            break;
                        }
                        case 2: {
                            repetirMenuPrincipal = true;
                            break;
                        }
                    }
                    break;
                }
            }
        } while (repetirMenuPrincipal);
    }

    private static void menuPrincipal(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "BlackJack", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "Novo jogador", "1", true);
        novoItem(tamanho, "Nova Partida", "2", true);
        novoItem(tamanho, "Pontuação", "3", true);
        novoItem(tamanho, "Regras", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Sair", "5", true);
        barra(tamanho, true);
    }

    private static int menuRecarrega(int tamanho) {
        Scanner input = new Scanner(System.in);
        boolean repetirRecarga;
        String user, senha;
        do {
            repetirRecarga = false;
            mensagem(tamanho, "Faça login!", false);
            System.out.print("User: ");
            user = input.nextLine();
            System.out.print("Senha: ");
            senha = input.nextLine();
            Jogador jogadorObtido = (Jogador) blackJackFacade.obterJogador(user, senha);
            if (jogadorObtido == null) {
                barra(tamanho, true);
                textoSimples(tamanho, "!!!Erro!!!", true, true);
                textoSimples(tamanho, "Jogador não encontrado!", true, true);
                mensagem(tamanho, "Gostaria de tentar novamente?", true);
                switch (lerInt(true, 1, 2)) {
                    case 1: {
                        repetirRecarga = true;
                        break;
                    }
                    case 2: {
                        return -1;
                    }
                }
            } else {
                opcoesRecarga(tamanho);
                switch (lerInt(true, 1, 5)) {
                    case 1: {
                        jogadorObtido.setPontos(25);
                        break;
                    }
                    case 2: {
                        jogadorObtido.setPontos(50);
                        break;
                    }
                    case 3: {
                        jogadorObtido.setPontos(75);
                        break;
                    }
                    case 4: {
                        jogadorObtido.setPontos(100);
                        break;
                    }
                    case 5: {
                        return -1;
                    }
                }
            }
        } while (repetirRecarga);
        return 0;
    }

    private static void opcoesRecarga(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Opçoes de recarga", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "R$ 12,00/25F", "1", true);
        novoItem(tamanho, "R$ 20,00/50F", "2", true);
        novoItem(tamanho, "R$ 25,00/75F", "3", true);
        novoItem(tamanho, "R$ 30,00/100F", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "5", true);
        barra(tamanho, true);
    }

    private static boolean cadastrarUser(int tamanho, File nomeArq) {
        String user, senha, confirmaSenha;
        boolean repetirCadastro;
        Scanner input = new Scanner(System.in);
        do {
            repetirCadastro = false;
            System.out.print("Usuario: ");
            user = input.nextLine();
            System.out.print("Senha: ");
            senha = input.nextLine();
            System.out.print("Confirma Senha: ");
            confirmaSenha = input.nextLine();
            if (senha.equals(confirmaSenha)) {
                try {
                    if (blackJackFacade.cadastrarNovoJogador(nomeArq, user, senha)) {
                        mensagem(tamanho, "Deseja cadastar novo jogador?", true);
                        switch (lerInt(true, 1, 2)) {
                            case 1: {
                                repetirCadastro = true;
                                break;
                            }
                            case 2: {
                                return true;
                            }
                        }
                    } else {
                        barra(tamanho, true);
                        textoSimples(tamanho, "Jogador não cadastrado!", true, true);
                        textoSimples(tamanho, "Jogador já existe!", true, true);
                        barra(tamanho, true);
                        repetirCadastro = true;
                    }
                } catch (IOException ex) {
                    return false;
                }
            } else {
                mensagem(tamanho, "Senha não confere!", false);
                repetirCadastro = true;
            }
        } while (repetirCadastro);
        return false;
    }

    private static void menuQuantidadeJogadores(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Quantos jogadores vão jogar?", false, true);
        textoDuplo(tamanho, "Min_(01)", true, "Max_(05)", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "6", true);
        barra(tamanho, true);
    }

    private static boolean escolherJogadores(int tamanho) {
        boolean repetirInserirUser;
        Scanner input = new Scanner(System.in);

        do {
            repetirInserirUser = false;
            Iterador listaDeUser = blackJackFacade.listaDeUsers();
            System.out.println("Inserir em partida!");
            System.out.print("User: ");
            String user = input.nextLine();
            System.out.print("Senha: ");
            String senha = input.nextLine();
            switch (blackJackFacade.inserirJogadorEmPartida(user, senha, listaDeUser)) {
                case -1: {
                    barra(tamanho, true);
                    textoSimples(tamanho, "Pontos insuficiente!", true, true);
                    barra(tamanho, true);
                    return false;
                }
                case 0: {
                    barra(tamanho, true);
                    textoSimples(tamanho, "Usuario ou senha invalido!", true, true);
                    textoSimples(tamanho, "Tente novamente!", true, true);
                    barra(tamanho, true);
                    repetirInserirUser = true;
                    break;
                }
                case 1: {
                    barra(tamanho, true);
                    textoSimples(tamanho, ("Usuario: " + user + " irá jogar!"), true, true);
                    barra(tamanho, true);
                    break;
                }
                case 2: {
                    barra(tamanho, true);
                    textoSimples(tamanho, user + " Já está cadastrado(a)!", true, true);
                    textoSimples(tamanho, "Cadastre outro jogador!", true, true);
                    barra(tamanho, true);
                    repetirInserirUser = true;
                    break;
                }
            }
        } while (repetirInserirUser);
        return true;
    }

    private static void texto(int tamanho, String texto) {
        int tamanhoDaBarra = tamanho - texto.length();
        for(int i = 0; i <tamanhoDaBarra / 2; i++){
            System.out.print(' ');
        }
        System.out.print(texto);
        for(int i = 0; i < tamanhoDaBarra - tamanhoDaBarra / 2; i++){
            System.out.print(' ');
        }
    }

    private static void menuRegras(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Menu de regras", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "2 Baralhos", "1", true);
        novoItem(tamanho, "4 Baralhos", "2", true);
        novoItem(tamanho, "8 Baralhos", "3", true);
        novoItem(tamanho, "Personalizado", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "5", true);
        barra(tamanho, true);
    }

    private static void round(int tamanho, int numRound, int numDeJogadores) {
        Jogador jogadorAtual;
        barra((tamanho * numDeJogadores), true);
        textoSimples((tamanho * numDeJogadores), "Rodada Nº: " + numRound, true, true);
        for (int info = 0; info < 6; info++) {
            Iterador lJogadores = blackJackFacade.verJogadoresEmPartida();
            while (lJogadores.hasNext()) {
                jogadorAtual = (Jogador) lJogadores.next();
                switch (info) {
                    case 0:
                        barra(tamanho, false);
                        break;
                    case 1:
                        novoItem(tamanho, "Jogador", jogadorAtual.getUser(), false);
                        break;
                    case 2:
                        texto(tamanho, "Cartas Em Mãos");
                        break;
                    case 3:
                        Iterador cartasEmMao = jogadorAtual.getMaoDeCartas().getCartas().iterador();
                        String cartas = "|";
                        System.out.print("|");
                        while (cartasEmMao.hasNext()) {
                            Carta carta = (Carta) cartasEmMao.next();
                            cartas = cartas.concat(carta + "|");
                        }
                        texto(tamanho, cartas);
                        System.out.print("|");
                        break;
                    case 4:
                        novoItem(tamanho, "Pontos em mãos", Integer.toString(jogadorAtual.pontosEmMao()), false);
                        break;
                    case 5:
                        barra(tamanho, false);
                }
            }
            System.out.println();
        }
    }

    private static void menuFimPartida(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Fim de partida", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "VER BARALHO USADO", "1", true);
        novoItem(tamanho, "Repetir partida", "2", true);
        novoItem(tamanho, "Nova partida", "3", true);
        separador(tamanho, true);
        novoItem(tamanho, "Menu Principal", "4", true);
        barra(tamanho, true);
    }

    private static void exibirHistorico(int tamanho) {
        String opcao = " ", log;
        barra((tamanho * 3), true);
        textoDuplo((tamanho * 3), "Opções do Menu", true, "Historico", true);
        barra((tamanho * 3), true);
        for (int i = 9; i != -1; i--) {
            log = (String) blackJackFacade.getInfoHistorico(i);
            if (i <= 3) {
                switch (3 - i) {
                    case 1: {
                        opcao = novoItemSimples(tamanho * 3 / 2, "Pedir Carta", "1");
                        break;
                    }
                    case 2: {
                        opcao = novoItemSimples(tamanho * 3 / 2, "Finalizar jogada", "2");
                        break;
                    }
                    case 3: {
                        opcao = novoItemSimples(tamanho * 3 / 2, "Desistir", "3");
                        break;
                    }
                }

            }
            textoDuplo((tamanho * 3), opcao, false, log, false);
        }
        barra((tamanho * 3), true);

    }

    private static void rodadaInicial(Croupier c, Pilha b) {
        for (int i = 0; i < 2; i++) {
            Iterador lJogadores = blackJackFacade.verJogadoresEmPartida();
            blackJackFacade.addHistorico("Croupier distribuindo " + (i + 1) + "ª rodada de cartas!");
            while (lJogadores.hasNext()) {
                Jogador jogadorAtual = (Jogador) lJogadores.next();
                Carta carta = c.daCarta(b);
                blackJackFacade.addHistorico(jogadorAtual.getUser() + " Recebeu: " + carta);
                jogadorAtual.addCartas(carta);
            }
        }
    }

    private static void atualizarInterface(int tamanho, int rodada) {
        round(tamanho, rodada++, blackJackFacade.getJogadoresEmPartida().tamanho());
        exibirHistorico(tamanho);
    }

    private static void partida(int tamanho, Iterador lJogadores, int qtdBaralho) {
        int rodada = 1;
        boolean querCarta;
        //So para Exemplo: 
        Croupier c = new Croupier("asfd", "asdf");
        Jogador jogadorAtual;
        Pilha b = blackJackFacade.criarBaralho(qtdBaralho); // Agora já temos um baralho embaralhado <3 kkk vamos ao metodo de dar cartas. 
        blackJackFacade.addHistorico("Inicio de Partida");
        blackJackFacade.addHistorico("Baralho Embaralhado!");
        //Embaralha o baralho!
        rodadaInicial(c, b);
        atualizarInterface(tamanho, rodada);
        while (lJogadores.hasNext()) {
            jogadorAtual = (Jogador) lJogadores.next();
            rodada++;
            blackJackFacade.addHistorico(jogadorAtual.getUser() + " Está com a vez!");
            atualizarInterface(tamanho, rodada);
            do {
                querCarta = false;
                switch (lerInt(true, 1, 3)) {
                    case 1: {
                        blackJackFacade.addHistorico("Jogador: " + jogadorAtual.getUser() + " Pediu carta!");
                        jogadorAtual.addCartas(c.daCarta(b)); // Aki eu pego o croupier e faço ele dar uma carta para o jogador. 
                        if (jogadorAtual.estourou()) {
                            blackJackFacade.addHistorico("Jogador: " + jogadorAtual.getUser() + " Estorou!");
                            atualizarInterface(tamanho, rodada);
                            jogadorAtual.setPontos(-5);
                            jogadorAtual.setPartidas(1);
                        } else if (jogadorAtual.venceu()) {
                            blackJackFacade.addHistorico(jogadorAtual.getUser() + "Venceu!");
                            jogadorAtual.setPontos(10);
                            jogadorAtual.setPartidas(1);
                            atualizarInterface(tamanho, rodada);
                        } else {
                            querCarta = true;
                        }
                        //adiciona cartas na mão do jogador;
                        break;
                    }
                    case 2: {
                        blackJackFacade.addHistorico("O jogador: " + jogadorAtual.getUser() + " Finalizou!");
                        jogadorAtual.setPartidas(1);
                        break;
                    }
                    case 3: {
                        jogadorAtual.setPartidas(1);
                        blackJackFacade.addHistorico(jogadorAtual.getUser() + " Desistiu!");
                        jogadorAtual.setPontos(-10);
                    }
                }
                atualizarInterface(tamanho, rodada);
            } while (querCarta);
        }
    }

    private static void erroCarregarArquivo(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "!!!ERRO!!!", true, true);
        separador(tamanho, true);
        textoSimples(tamanho, "Problema ao abrir o arquivo", true, true);
        textoSimples(tamanho, "Inserir arquivo manualmente?", true, true);
        separador(tamanho, true);
        textoDuplo(tamanho, "Sim__(01)", true, "Não__(02)", true);
        barra(tamanho, true);
    }

    /**
     * Método responsavel por inserir uma barra de inicialização ou finalização de menu e ou mensagem.
     *
     * @param tamanho Define o tamanho da barra.
     */
    private static void barra(int tamanho, boolean pulaLinha) {
        System.out.print("+");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("=");
        }
        if (pulaLinha) {
            System.out.println("+");
        } else {
            System.out.print("+");
        }
    }

    /**
     * Método responsavel por exibir um texto simples entre bordas.
     *
     * @param tamanho Define espaço disponivel para a inserção do texto.
     * @param texto Mensagem que será exibida.
     * @param centralizar Define se a mensagem terá o alinhamento centralizado ou a esquerda.
     */
    private static void textoSimples(int tamanho, String texto, boolean centralizar, boolean pulaLinha) {
        int tamanhoDaBarra = tamanho - texto.length();
        int espacosDaEsquerda, espacosDaDireita;
        espacosDaEsquerda = centralizar ? tamanhoDaBarra / 2 : 1;
        espacosDaDireita = tamanhoDaBarra - espacosDaEsquerda;
        fazTraco(espacosDaEsquerda, ' ', true, false);
        System.out.print(texto);
        fazTraco(espacosDaDireita, ' ', false, pulaLinha);
    }

    /**
     * Método responsavel por inserir exibir uma mensagem com dois conteudos separados de forma que o conteudo não utrapasse o espaço disponivel.
     *
     * PS: O espaço deve ser maior do que as mensagens.
     *
     * @param tamanho Determina o espaço disponivel para a adequação dos textos.
     * @param txt1
     * @param cTxt1
     * @param txt2
     * @param cTxt2
     */
    public static void textoDuplo(int tamanho, String txt1, boolean cTxt1, String txt2, boolean cTxt2) {
        textoSimples(tamanho / 2 - 1, txt1, cTxt1, false);
        textoSimples(tamanho / 2, txt2, cTxt2, true);
    }

    /**
     * Método responsavel por separar uma opções de um menu ou mensagem.
     *
     * @param tamanho determina o tamanho do separador.
     * @param comTraco Define a visibilidade do separador.
     */
    private static void separador(int tamanho, boolean comTraco) {
        char lateral = '|', meio = ' ';
        if (comTraco == true) {
            lateral = '+';
            meio = '=';
        }
        System.out.print(lateral);
        fazTraco(tamanho, meio, false, false);
        System.out.println(lateral);
    }

    /**
     * Método utilizado para inserir um novo item/opção em determinado menu seguindo a "formatação".
     *
     * @param tamanho Define o tamanho disponivel para a inserção do item e do peso.
     * @param item Nome do item/opção que será inserido.
     * @param pesoDoItem Valor correspondente ao peso do item inserido.
     */
    private static void novoItem(int tamanho, String item, String pesoDoItem, boolean pulaLinha) {

        int qtdDeTracos = ((tamanho - item.length()) - (pesoDoItem.length() + 4));
        if (pesoDoItem.length() == 1) {
            qtdDeTracos--;
        }
        System.out.print("| " + item);
        fazTraco(qtdDeTracos, '.', false, false);
        pesoDoItem = (pesoDoItem.length() >= 2) ? "(" + pesoDoItem + ")" : "(0" + pesoDoItem + ")";
        System.out.print(pesoDoItem);
        if (pulaLinha) {
            System.out.println(" |");
        } else {
            System.out.print(" |");
        }

    }

    private static String novoItemSimples(int tamanho, String item, String pesoDoItem) {
        String s = new String();

        int qtdDeTracos = ((tamanho - item.length() - 2) - (pesoDoItem.length() + 4));

        s += item;
        for (int i = 0; i < qtdDeTracos; i++) {
            s += '.';
        }
        pesoDoItem = (pesoDoItem.length() >= 2) ? "(" + pesoDoItem + ")" : "(0" + pesoDoItem + ")";
        s += pesoDoItem;
        return s;
    }

    /**
     * Método responsavel por exibir uma mensagem simples ou composta com duas escolhas (Sim ou Não) formatados.
     *
     * @param tamanho Determina o tamanho do limite das bordas.
     * @param mensagem Mensagem a ser exibida.
     * @param poemEscolha Determina se deve exitir opções na mensagem.
     */
    private static void mensagem(int tamanho, String mensagem, boolean poemEscolha) {
        barra(tamanho, true);
        textoSimples(tamanho, mensagem, true, true);
        if (poemEscolha) {
            textoDuplo(tamanho, "Sim__(01)", true, "Não__(02)", true);
        }
        barra(tamanho, true);

    }

    /**
     * Método responsavel por fazer um traço.
     *
     * @param tamanho Determina o tamanho do traço.
     * @param estilo Determina o traço que deve ser utilizado.
     * @param bordaE Determina se o traço deve ser inicado com uma "borda".
     * @param bordaD Determina se o traço deve ser finalizado com uma "borda".
     */
    private static String fazTraco(int tamanho, char estilo, boolean bordaE, boolean bordaD) {
        String s = "";
        if (bordaE) {
            System.out.print("|");
        }
        for (int i = 0; i < tamanho; i++) {
            System.out.print(estilo);
            s += estilo;
        }
        if (bordaD) {
            System.out.println("|");
        }
        return s;
    }

    /**
     * Método utilizado para fazer leitura de valores inteiros, tratando as exeções e possiveis problemas gerados por causa do buffer do teclado.
     *
     * @param limite Define se o valor a ser lido deve está em uma determinada faixa de valores.
     * @param min Valor minimo do intervalo de leitura caso limite = true;
     * @param max Valor Máximo do intervalo de leitura caso limite = true;
     * @return Valor inteiro.
     */
    private static int lerInt(boolean limite, int min, int max) {
        Scanner input = new Scanner(System.in);
        int valor;
        boolean repetir;
        do {
            try {
                valor = input.nextInt();
                input.nextLine();
                if ((limite && max == 0) && (valor >= min)) {
                    return valor;
                } else if (limite) {
                    if (valor >= min && valor <= max) {
                        return valor;
                    } else {
                        repetir = true;
                    }
                } else {
                    return valor;
                }

            } catch (Exception e) {
                input.nextLine();
                repetir = true;
            }
        } while (repetir);
        return 0;
    }
}
