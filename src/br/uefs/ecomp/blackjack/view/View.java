/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.view;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.Iterador;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Uellington Damasceno
 */
public class View {

    static ControllerArquivos controllerArquivos = new ControllerArquivos();
    static ControllerPartida controllerPartida = new ControllerPartida();

    public static void main(String[] args) throws IOException {
        final int TAMANHO_MENU = 30;
        boolean repetirMenuPrincipal, repetirMenuSalas, repetirCarregarArquivo;
        boolean repetirQtdJogador, querCadastrar = false;
        File arquivo = new File("Logins.txt");
        do {
            repetirCarregarArquivo = false;
            try {
                if (!controllerArquivos.carregarUsers(arquivo)) {
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
        int numDeBaralho;
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
                        if (controllerArquivos.getUsers().estaVazia() || querCadastrar) {
                            barra(TAMANHO_MENU, true);
                            textoSimples(TAMANHO_MENU, "!!!ERRO!!!", true);
                            textoSimples(TAMANHO_MENU, "Nº Jogadores insuficiente", true);
                            mensagem(TAMANHO_MENU, "Cadastar novo jogador?", true);
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
                            } else if (controllerArquivos.getUsers().tamanho() < qtdJogadores) {
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
                                        escolherJogadores(TAMANHO_MENU, qtdJogadores);
                                        round(TAMANHO_MENU, 1, qtdJogadores);
                                    }
                                } while (repetirMenuSalas);
                            }
                        }
                    } while (repetirQtdJogador);

                    break;
                }
                case 3: {
                    Iterador lJogadores = controllerArquivos.listaDeUsers();
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
        textoSimples(tamanho, "BlackJack", true);
        separador(tamanho, true);
        novoItem(tamanho, "Novo jogador", "1", true);
        novoItem(tamanho, "Nova Partida", "2", true);
        novoItem(tamanho, "Pontuação", "3", true);
        novoItem(tamanho, "Regras", "4", true);
        separador(tamanho, true);
        novoItem(tamanho, "Sair", "5", true);
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
                    if (controllerArquivos.cadastrarNovoJogador(nomeArq, user, senha)) {
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
                        textoSimples(tamanho, "Jogador não cadastrado!", true);
                        textoSimples(tamanho, "Jogador já existe!", true);
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
        textoSimples(tamanho, "Quantos jogadores vão jogar?", false);
        textoDuplo(tamanho, "Min_(01)", "Max_(05)");
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", "6", true);
        barra(tamanho, true);
    }

    private static boolean escolherJogadores(int tamanho, int qtdJogadores) {
        boolean repetirInserirUser;
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < qtdJogadores; i++) {
            do {
                repetirInserirUser = false;
                Iterador listaDeUser = controllerArquivos.listaDeUsers();
                System.out.print("User: ");
                String user = input.nextLine();
                System.out.print("Senha: ");
                String senha = input.nextLine();
                switch (controllerPartida.inserirJogadorEmPartida(user, senha, listaDeUser)) {
                    case -1: {
                        barra(tamanho, true);
                        textoSimples(tamanho, "Limite alcançado!", true);
                        barra(tamanho, true);
                        return true;
                    }
                    case 0: {
                        barra(tamanho, true);
                        textoSimples(tamanho, "Usuario ou senha invalido!", true);
                        textoSimples(tamanho, "Tente novamente!", true);
                        barra(tamanho, true);
                        repetirInserirUser = true;
                        break;
                    }
                    case 1: {
                        barra(tamanho, true);
                        textoSimples(tamanho, ("Usuario: " + user + " irá jogar!"), true);
                        barra(tamanho, true);
                        repetirInserirUser = false;
                        break;
                    }
                    case 2: {
                        barra(tamanho, true);
                        textoSimples(tamanho, user + " Já está cadastrado(a)!", true);
                        textoSimples(tamanho, "Cadastre outro jogador!", true);
                        barra(tamanho, true);
                        repetirInserirUser = true;
                        break;
                    }
                }
            } while (repetirInserirUser);
        }
        return true;
    }

    private static void menuRegras(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "Menu de regras", true);
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
        textoSimples((tamanho * numDeJogadores), "Rodada Nº: " + numRound, true);
        for (int info = 0; info < 5; info++) {
            Iterador lJogadores = controllerPartida.verJogadoresEmPartida();
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
                        novoItem(tamanho, "Senha", jogadorAtual.getSenha(), false);
                        break;
                    case 3:
                        novoItem(tamanho, "Pontos em mãos", Integer.toString(jogadorAtual.pontosEmMao()), false);
                        break;
                    case 4:
                        barra(tamanho, false);
                }
            }
            System.out.println();
        }
    }

    private static void erroCarregarArquivo(int tamanho) {
        barra(tamanho, true);
        textoSimples(tamanho, "!!!ERRO!!!", true);
        separador(tamanho, true);
        textoSimples(tamanho, "Problema ao abrir o arquivo", true);
        textoSimples(tamanho, "Inserir arquivo manualmente?", true);
        separador(tamanho, true);
        textoDuplo(tamanho, "Sim__(01)", "Não__(02)");
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
    private static void textoSimples(int tamanho, String texto, boolean centralizar) {
        int tamanhoDaBarra = tamanho - texto.length();
        int espacosDaEsquerda, espacosDaDireita;
        espacosDaEsquerda = centralizar ? tamanhoDaBarra / 2 : 1;
        espacosDaDireita = tamanhoDaBarra - espacosDaEsquerda;
        fazTraco(espacosDaEsquerda, ' ', true, false);
        System.out.print(texto);
        fazTraco(espacosDaDireita, ' ', false, true);
    }

    /**
     * Método responsavel por inserir exibir uma mensagem com dois conteudos separados de forma que o conteudo não utrapasse o espaço disponivel.
     *
     * PS: O espaço deve ser maior do que as mensagens.
     *
     * @param tamanho Determina o espaço disponivel para a adequação dos textos.
     * @param texto01 Mensagem que será exibida do lado direito.
     * @param texto02 Mensagem que será exibida do lado esquerdo.
     */
    private static void textoDuplo(int tamanho, String texto01, String texto02) {
        int totalDeTexto = texto01.length() + texto02.length();
        int novoTamanhoDoMenu = tamanho - totalDeTexto;
        int espacosLaterais = novoTamanhoDoMenu / 3;

        fazTraco(espacosLaterais, ' ', true, false);
        System.out.print(texto01);
        fazTraco(novoTamanhoDoMenu - (espacosLaterais * 2), ' ', false, false);
        System.out.print(texto02);
        fazTraco(espacosLaterais, ' ', false, true);
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
        //String pesoDoItem = Integer.toString(pesoDoItem);
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

    /**
     * Método responsavel por exibir uma mensagem simples ou composta com duas escolhas (Sim ou Não) formatados.
     *
     * @param tamanho Determina o tamanho do limite das bordas.
     * @param mensagem Mensagem a ser exibida.
     * @param poemEscolha Determina se deve exitir opções na mensagem.
     */
    private static void mensagem(int tamanho, String mensagem, boolean poemEscolha) {
        barra(tamanho, true);
        textoSimples(tamanho, mensagem, true);
        if (poemEscolha) {
            textoDuplo(tamanho, "Sim__(01)", "Não__(02)");
            barra(tamanho, true);
        } else {
            barra(tamanho, true);
        }
    }

    /**
     * Método responsavel por fazer um traço.
     *
     * @param tamanho Determina o tamanho do traço.
     * @param estilo Determina o traço que deve ser utilizado.
     * @param bordaE Determina se o traço deve ser inicado com uma "borda".
     * @param bordaD Determina se o traço deve ser finalizado com uma "borda".
     */
    private static void fazTraco(int tamanho, char estilo, boolean bordaE, boolean bordaD) {
        if (bordaE) {
            System.out.print("|");
        }
        for (int i = 0; i < tamanho; i++) {
            System.out.print(estilo);
        }
        if (bordaD) {
            System.out.println("|");
        }
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
