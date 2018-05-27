package br.uefs.ecomp.blackjack.view;

import br.uefs.ecomp.blackjack.facade.BlackJackFacade;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Uellington Damasceno
 */
public class View {

    static BlackJackFacade blackJackFacade = new BlackJackFacade();

    /**
     * Método principal, utilizado como referencia para execução do programa.
     *
     * @param args
     */
    public static void main(String[] args) {
        final int TAMANHO_MENU = 30;
        boolean repetirMenuPrincipal, repetirMenuSalas;
        boolean repetirQtdJogador, repetirRecarga, querCadastrar = false;
        boolean repetirPartida, inserirNovoJogador, repetirFimPartida;
        int numDeBaralho = 0, qtdJogadores;

        try {
            blackJackFacade.carregarUsers();
        } catch (IOException e) {
            mensagem(TAMANHO_MENU, "Erro ao abrir arquivo", true);
            System.exit(0);
        }
        do {
            repetirMenuPrincipal = false;
            menuPrincipal(TAMANHO_MENU);
            switch (lerInt(true, 1, 6)) {
                case 1: {
                    if (cadastrarUser(TAMANHO_MENU)) {
                        mensagem(TAMANHO_MENU, "Sucesso!", false);
                    }
                    repetirMenuPrincipal = true;
                    blackJackFacade.atualizarArquivos();
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
                                    if (cadastrarUser(TAMANHO_MENU)) {
                                        mensagem(TAMANHO_MENU, "Sucesso!", false);
                                    }
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
                                            Baralho baralho = blackJackFacade.criarBaralho(numDeBaralho);
                                            blackJackFacade.iniciarPartida();
                                            partidaMecanica(TAMANHO_MENU);
                                            blackJackFacade.finalizarPartida();
                                            if (!blackJackFacade.atualizarArquivos()) {
                                                mensagem(TAMANHO_MENU, "Erro ao atualizar arquivos", false);
                                                System.exit(0);
                                            }
                                            Carta[] baralhoTemp = Arrays.copyOf(baralho.getCartas(), baralho.getCartas().length);
                                            blackJackFacade.ordena(baralho);
                                            do {
                                                repetirFimPartida = false;
                                                menuFimPartida(TAMANHO_MENU);
                                                switch (lerInt(true, 1, 4)) {
                                                    case 1: {
                                                        textoSimples(TAMANHO_MENU, "Baralho na ordem de saida >>>", true, true);
                                                        mostrarBaralho(baralhoTemp);
                                                        textoSimples(TAMANHO_MENU, "Baralho ordenado>>>", true, true);
                                                        mostrarBaralho(baralho.getCartas());
                                                        repetirFimPartida = true;
                                                        break;
                                                    }
                                                    case 2: {
                                                        repetirPartida = true;
                                                        break;
                                                    }
                                                    case 3: {
                                                        blackJackFacade.zerarSalaDeEspera();
                                                        repetirQtdJogador = true;
                                                        break;
                                                    }
                                                    case 4: {
                                                        blackJackFacade.zerarSalaDeEspera();
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
                    do {
                        repetirRecarga = false;
                        switch (menuRecarrega(TAMANHO_MENU)) {
                            case -1: {
                                repetirMenuPrincipal = true;
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
                                        repetirMenuPrincipal = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    } while (repetirRecarga);
                    blackJackFacade.atualizarArquivos();
                    break;
                }
                case 4: {
                    exibirRecord(TAMANHO_MENU);
                    repetirMenuPrincipal = true;
                    break;
                }
                case 5: {
                    regras();
                    repetirMenuPrincipal = true;
                    break;
                }
                case 6: {
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
        novoItem(tamanho, "Iniciar partida", "2", true);
        novoItem(tamanho, "Recarregar", "3", true);
        novoItem(tamanho, "Recordes", "4", true);
        novoItem(tamanho, "Regras", "5", true);
        separador(tamanho, true);
        novoItem(tamanho, "Sair", "6", true);
        barra(tamanho, true);
    }

    private static void exibirRecord(int tamanho) {
        if (!blackJackFacade.getUsers().estaVazia()) {
            barra(tamanho, true);
            Iterador lJogadores = blackJackFacade.listaDeUsers();
            while (lJogadores.hasNext()) {
                Jogador jogadorObtido = (Jogador) lJogadores.next();
                System.out.println(jogadorObtido);
            }
            barra(tamanho, true);
        } else {
            mensagem(tamanho, "Sem records!", false);
        }
    }

    private static void opcoesRecarrga(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Opçoes de recarga", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "R$ 12,00::25F", "1", true);
        novoItem(tamanho, "R$ 20,00::50F", "2", true);
        novoItem(tamanho, "R$ 25,00::75F", "3", true);
        novoItem(tamanho, "R$ 30,00::100F", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "5", true);
        barra(tamanho, true);
    }

    private static void mostrarBaralho(Carta[] cartas) {
        int limite = 0;
        for (Carta carta : cartas) {
            if (limite <= 10) {
                System.out.print("|" + carta + "|");
                limite++;
            } else {
                System.out.print("\n|" + carta + "|");
                limite = 0;
            }
        }
        System.out.println("");
    }

    private static void menuQuantidadeJogadores(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Quantos jogadores vão jogar?", false, true);
        textoDuplo(tamanho, "Min_(01)", true, "Max_(05)", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "6", true);
        barra(tamanho, true);
    }

    private static void menuRegras(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Estilo de partida", true, true);
        separador(tamanho, true);
        novoItem(tamanho, "Rapida: 2 Baralhos", "1", true);
        novoItem(tamanho, "Media: 4 Baralhos", "2", true);
        novoItem(tamanho, "Demorada: 8 Baralhos", "3", true);
        novoItem(tamanho, "Personalizado", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "5", true);
        barra(tamanho, true);
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

    private static void regras() {
        String mensagem = "BlackJack Rules:\n\n"
                + "O objetivo do BlackJack é vencer o croupier. Os jogadores ganham se tiverem uma mão de cartas \n"
                + "com numeração maior do que a mão de cartas do croupier só que sem estourar(a soma da \n"
                + "numeração das cartas não exceder 21). Os jogadores também ganham se o dealer estourar. O \n"
                + "baralho do BlackJack contém 52 cartas.As \n"
                + "cartas contém um naipe e número ou letra. Um naipe contém 13 cartas e eles não\n"
                + "influenciam nos valores das cartas. As cartas que contém números valem em pontos\n"
                + "seus respectivos números, as cartas que contém as letras K Q ou J valem 10. A carta que\n"
                + "contém a letra A tem um valor especial no jogo. Podem valer 1 ou 11, dependendo do\n"
                + "valor que irá fornecer vantagem a mão.";

        JOptionPane.showMessageDialog(null, mensagem);
    }

    private static int menuRecarrega(int tamanho) {
        Scanner input = new Scanner(System.in);
        boolean repetirRecarga;
        String user, senha;
        if (blackJackFacade.getUsers().estaVazia()) {
            mensagem(tamanho, "Não existe jogadores!", false);
            return -1;
        } else {
            do {
                repetirRecarga = false;
                mensagem(tamanho, "Faça login!", false);
                System.out.print("User: ");
                user = input.nextLine();
                System.out.print("Senha: ");
                senha = input.nextLine();
                Jogador jogadorObtido = blackJackFacade.obterJogador(user, senha);
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
                    opcoesRecarrga(tamanho);
                    switch (lerInt(true, 1, 5)) {
                        case 1: {
                            jogadorObtido.setScore(25);
                            break;
                        }
                        case 2: {
                            jogadorObtido.setScore(50);
                            break;
                        }
                        case 3: {
                            jogadorObtido.setScore(75);
                            break;
                        }
                        case 4: {
                            jogadorObtido.setScore(100);
                            break;
                        }
                        case 5: {
                            return -1;
                        }
                    }
                }
            } while (repetirRecarga);
        }
        return 0;
    }

    private static boolean cadastrarUser(int tamanho) {
        String user, senha, confirmaSenha;
        boolean repetirCadastro;
        Scanner input = new Scanner(System.in);

        do {
            repetirCadastro = false;
            mensagem(tamanho, "Para voltar user = sair", false);
            System.out.print("Usuario: ");
            user = input.nextLine();
            if (user.equals("sair")) {
                return false;
            } else {
                System.out.print("Senha: ");
                senha = input.nextLine();
                System.out.print("Confirma Senha: ");
                confirmaSenha = input.nextLine();
                if (senha.equals(confirmaSenha)) {
                    try {
                        if (blackJackFacade.cadastrarNovoJogador(user, senha)) {
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
            }
        } while (repetirCadastro);
        return false;
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

    private static void lJogadores(int tamanho, Jogador jogadorComVez) {
        Jogador jogadorAtual;
        for (int info = 0; info < 6; info++) {
            Iterador lJogadores = blackJackFacade.jogadoresEmPartida();
            while (lJogadores.hasNext()) {
                jogadorAtual = (Jogador) lJogadores.next();
                if (jogadorAtual.equals(jogadorComVez)) {
                    System.out.print(">");
                } else {
                    switch (info) {
                        case 0:
                            barra(tamanho, false);
                            break;
                        case 1:
                            novoItem(tamanho, "Jogador", jogadorAtual.getUser(), false);
                            break;
                        case 2:
                            novoItem(tamanho, "Pontos em mãos", Integer.toString(jogadorAtual.pontosEmMao()), false);
                            break;
                        case 3:
                            System.out.print('|');
                            texto(tamanho, "Cartas Em Mãos");
                            System.out.print('|');
                            break;
                        case 4:
                            Iterador cartasEmMao = jogadorAtual.getMaoDeCartas().getCartas().iterador();
                            String cartas = "|";
                            while (cartasEmMao.hasNext()) {
                                Carta carta = (Carta) cartasEmMao.next();
                                cartas = cartas.concat(carta + "|");
                            }
                            texto(tamanho, cartas);
                            break;
                        case 5:
                            barra(tamanho, false);
                            break;
                    }
                }
            }
            System.out.println();
        }
    }

    private static void exibirHistorico(Jogador jogador, boolean duplo) {
        String opcao, log;
        int tamanho;
        boolean cTxt1 = false;
        if (duplo) {
            tamanho = 90;
            barra(tamanho, true);
            textoDuplo(tamanho, "A vez está com o jogador: " + jogador.getUser(), true, "Historico", true);
        } else {
            tamanho = 45;
            barra(tamanho, true);
            textoSimples(tamanho, "♦ JOGADORES E SUAS PONTUAÇÕES ♦", true, true);
        }
        barra(tamanho, true);
        for (int i = 9; i != -1; i--) {
            log = duplo ? (String) blackJackFacade.getInfoHistorico(i + 1) : (String) blackJackFacade.getInfoHistorico(i);
            if (jogador.ehCroupier() && i <= 3) {
                opcao = " ";
            } else {
                switch (i) {
                    case 9: {
                        opcao = novoItemSimples(tamanho / 2, "Jogador", jogador.getUser());
                        break;
                    }
                    case 8: {
                        opcao = novoItemSimples(tamanho / 2, "Total de pontos", Integer.toString(jogador.pontosEmMao()));
                        break;
                    }
                    case 7: {
                        opcao = "Pontos em mãos";
                        cTxt1 = true;
                        break;
                    }
                    case 6: {
                        Iterador cartasEmMao = jogador.getMaoDeCartas().getCartas().iterador();
                        String cartas = "|";
                        while (cartasEmMao.hasNext()) {
                            Carta carta = (Carta) cartasEmMao.next();
                            cartas = cartas.concat(carta + "|");
                        }
                        opcao = cartas;
                        cTxt1 = true;
                        break;
                    }
                    case 5: {
                        if (jogador.ehCroupier()) {
                            opcao = "APERTE UMA TECLA PARA CONTINUAR!";
                            cTxt1 = true;
                        } else {
                            opcao = " ";
                        }
                        break;
                    }
                    case 4: {
                        opcao = (String) blackJackFacade.getInfoHistorico(0);
                        cTxt1 = true;
                        break;
                    }
                    case 3: {
                        opcao = "VV-OPÇÕES DE JOGADA-VV";
                        cTxt1 = true;
                        break;
                    }
                    case 2: {
                        opcao = novoItemSimples(tamanho / 2, "Pedir Carta", "1");
                        break;
                    }
                    case 1: {
                        opcao = novoItemSimples(tamanho / 2, "Finalizar jogada", "2");
                        break;
                    }
                    case 0: {
                        opcao = novoItemSimples(tamanho / 2, "Desistir", "3");
                        break;
                    }
                    default: {
                        opcao = " ";
                        break;
                    }
                }
            }
            if (duplo) {
                textoDuplo(tamanho, opcao, cTxt1, log, false);
            } else {
                textoSimples(tamanho, log, true, true);
            }
            cTxt1 = false;
        }
        barra(tamanho, true);
    }

    private static void partidaMecanica(int tamanho) {
        boolean querCarta;
        Jogador jogadorAtual;
        Iterador lJogadores = blackJackFacade.jogadoresEmPartida();
        while (lJogadores.hasNext()) {
            jogadorAtual = (Jogador) lJogadores.next();
            blackJackFacade.addHistorico(jogadorAtual.getUser() + " Está com a vez!");
            atualizarInterface(tamanho, jogadorAtual, true);
            if (jogadorAtual.ehCroupier()) {
                blackJackFacade.vezDoCroupier();
                atualizarInterface(tamanho, jogadorAtual, true);
                blackJackFacade.premiacao();
                atualizarInterface(tamanho, jogadorAtual, true);
                blackJackFacade.consideracoes();
                exibirHistorico(jogadorAtual, false);
                System.out.print("Digite qualquer numero para continuar...");
                lerInt(false, 0, 0);
                for(int i = 0; i < 4; i++){
                    System.out.println("");
                }
            } else {
                do {
                    querCarta = false;
                    switch (lerInt(true, 1, 3)) {
                        case 1: {
                            blackJackFacade.daCarta(jogadorAtual);
                            if (jogadorAtual.estourou()) {
                                blackJackFacade.addHistorico(jogadorAtual.getUser() + " Estorou com: " + jogadorAtual.pontosEmMao() + " Pontos");
                                atualizarInterface(tamanho, jogadorAtual, true);
                            } else if (jogadorAtual.venceu()) {
                                blackJackFacade.addHistorico(jogadorAtual.getUser() + "Fez 21 Pontos!!!");
                                atualizarInterface(tamanho, jogadorAtual, true);
                            } else {
                                querCarta = true;
                            }
                            break;
                        }
                        case 2: {
                            blackJackFacade.addHistorico("O jogador: " + jogadorAtual.getUser() + " Finalizou!");
                            break;
                        }
                        case 3: {
                            blackJackFacade.addHistorico(jogadorAtual.getUser() + " Desistiu!");
                        }
                    }
                    atualizarInterface(tamanho, jogadorAtual, true);
                } while (querCarta);
            }
        }
    }

    private static void atualizarInterface(int tamanho, Jogador jogador, boolean duplo) {
        lJogadores(tamanho, jogador);
        exibirHistorico(jogador, duplo);
    }

    /**
     * *****************************************************************************************************************************************
     *************************************** MÉTODOS DE UTILIZADOS PARA CRIAÇÃO DOS MENUS *******************************************************
     *******************************************************************************************************************************************/
    private static void texto(int tamanho, String texto) {
        int tamanhoDaBarra = tamanho - texto.length();
        for (int i = 0; i < tamanhoDaBarra / 2; i++) {
            System.out.print(' ');
        }
        System.out.print(texto);
        for (int i = 0; i < tamanhoDaBarra - tamanhoDaBarra / 2; i++) {
            System.out.print(' ');
        }
    }

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

    private static void textoSimples(int tamanho, String texto, boolean centralizar, boolean pulaLinha) {
        int tamanhoDaBarra = tamanho - texto.length();
        int espacosDaEsquerda, espacosDaDireita;
        espacosDaEsquerda = centralizar ? tamanhoDaBarra / 2 : 1;
        espacosDaDireita = tamanhoDaBarra - espacosDaEsquerda;
        fazTraco(espacosDaEsquerda, ' ', true, false);
        System.out.print(texto);
        fazTraco(espacosDaDireita, ' ', false, pulaLinha);
    }

    public static void textoDuplo(int tamanho, String txt1, boolean cTxt1, String txt2, boolean cTxt2) {
        textoSimples(tamanho / 2 - 1, txt1, cTxt1, false);
        textoSimples(tamanho / 2, txt2, cTxt2, true);
    }

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

    private static void mensagem(int tamanho, String mensagem, boolean poemEscolha) {
        barra(tamanho, true);
        textoSimples(tamanho, mensagem, true, true);
        if (poemEscolha) {
            textoDuplo(tamanho, "Sim__(01)", true, "Não__(02)", true);
        }
        barra(tamanho, true);

    }

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
                        System.out.print("Digite um valor entre: " + min + " e " + max + ": ");
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
