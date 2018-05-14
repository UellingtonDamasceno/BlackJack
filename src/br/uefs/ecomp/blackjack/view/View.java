/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.view;

import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.Iterador;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Uellington Damasceno
 */
public class View {

    static ControllerArquivo contollerArquivo = new ControllerArquivo();
    static ControllerPartida controllerPartida = new ControllerPartida();
    
    public static void main(String[] args) {
        boolean repetirMenuPrincipal, repetirMenuSalas, repetirCarregarArquivo;
        int TAMANHO_MENU = 30;
    
        //Caso queira fazer o teste do novo metodo;
        Baralho b = new Baralho(3);
        Croupier c = new Croupier("asf", "asdf");
        try {
            contollerArquivo.carregarUsers("Logins.txt");
        } catch (IOException ex) {
            do {
                repetirCarregarArquivo = false;
                erroCarregarArquivo(TAMANHO_MENU);
                switch (lerInt(true, 1, 2)) {
                    case 1: {
                        mensagem(TAMANHO_MENU, "Digite o nome do arquivo", false);
                        Scanner input = new Scanner(System.in);
                        try {
                            contollerArquivo.carregarUsers(input.nextLine());
                        } catch (IOException erro) {
                            repetirCarregarArquivo = true;
                        }
                        break;
                    }
                    case 2: {
                        mensagem(TAMANHO_MENU, "Até a proxima", false);
                        System.exit(0);
                        break;
                    }
                }
            } while (repetirCarregarArquivo);
        }

        int numDeBaralho = 0;
        
        do {
            repetirMenuPrincipal = false;
            menuPrincipal(30);
            switch (lerInt(true, 1, 5)) {
                case 1: {
                    repetirMenuPrincipal = cadastrarUser(TAMANHO_MENU);
                    break;
                }
                case 2: {
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
                                repetirMenuPrincipal = true;
                                break;
                            }
                        }
                        if(!repetirMenuPrincipal){
                            repetirMenuSalas = !escolherJogadores(TAMANHO_MENU);
                        }
                    } while (repetirMenuSalas);
                    break;
                }
                case 3: {
                    Iterador lJogadores = contollerArquivo.listaDeUsers();
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
        barra(tamanho);
        textoSimples(tamanho, "BlackJack", true);
        separador(tamanho, true);
        novoItem(tamanho, "Novo jogador", 1);
        novoItem(tamanho, "Nova Partida", 2);
        novoItem(tamanho, "Pontuação", 3);
        novoItem(tamanho, "Regras", 4);
        separador(tamanho, true);
        novoItem(tamanho, "Sair", 5);
        barra(tamanho);
    }

    private static boolean cadastrarUser(int tamanho) {
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
                    if (contollerArquivo.cadastrarNovoJogador(user, senha)) {
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
                        barra(tamanho);
                        textoSimples(tamanho, "Jogador não cadastrado!", true);
                        textoSimples(tamanho, "Jogador já existe!", true);
                        barra(tamanho);
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
    private static boolean escolherJogadores(int tamanho) {
        boolean repetirPartida, repetirInserirUser;
        String user, senha;
        Scanner input = new Scanner(System.in);
        Iterador listaDeUser = contollerArquivo.listaDeUsers();
        do {
            repetirPartida = false;
            barra(tamanho);
            textoSimples(tamanho, "Quantos jogadores vão jogar?", false);
            textoDuplo(tamanho, "Min {01}", "Max {05}");
            separador(tamanho, true);
            novoItem(tamanho, "Voltar", 6);
            barra(tamanho);
            int qtdJogadores = lerInt(true, 1, 6);
            if(qtdJogadores == 6){
                return false;
            }else{
                for (int i = 0; i < qtdJogadores; i++) {
                    do {
                        repetirInserirUser = false;
                        System.out.print("User: ");
                        user = input.nextLine();
                        System.out.print("Senha: ");
                        senha = input.nextLine();
                        switch (controllerPartida.inserirJogadorEmPartida(user, senha, listaDeUser)) {
                            case 0: {
                                barra(tamanho);
                                textoSimples(tamanho, "Usuario ou senha invalido!", true);
                                textoSimples(tamanho, "Tente novamente!", true);
                                barra(tamanho);
                                repetirInserirUser = true;
                                break;
                            }
                            case 1: {
                                barra(tamanho);
                                textoSimples(tamanho, ("Usuario: " + user + " irá jogar!"), true);
                                barra(tamanho);
                                break;
                            }
                            case 2: {
                                barra(tamanho);
                                textoSimples(tamanho, user + " Já está cadastrado(a)!", true);
                                textoSimples(tamanho, "Cadastre outro jogador!", true);
                                barra(tamanho);
                                repetirInserirUser = true;
                                break;
                            }
                        }
                    } while (repetirInserirUser);
                }
            }
        } while (repetirPartida);
        return true;
    }

    private static void menuRegras(int tamanho) {
        barra(tamanho);
        textoSimples(tamanho, "Menu de regras", true);
        separador(tamanho, true);
        novoItem(tamanho, "2 Baralhos", 1);
        novoItem(tamanho, "4 Baralhos", 2);
        novoItem(tamanho, "8 Baralhos", 3);
        novoItem(tamanho, "Personalizado", 4);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", 5);
        barra(tamanho);
    }

    private static void erroCarregarArquivo(int tamanho) {
        barra(tamanho);
        textoSimples(tamanho, "!!!ERRO!!!", true);
        separador(tamanho, true);
        textoSimples(tamanho, "Problema ao abrir o arquivo", true);
        textoSimples(tamanho, "Inserir arquivo manualmente?", true);
        separador(tamanho, true);
        textoDuplo(tamanho, "Sim__(01)", "Não__(02)");
        barra(tamanho);
    }

    /**
     * Método responsavel por inserir uma barra de inicialização ou finalização de menu e ou mensagem.
     *
     * @param tamanho Define o tamanho da barra.
     */
    private static void barra(int tamanho) {
        System.out.print("+");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("=");
        }
        System.out.println("+");
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
    private static void novoItem(int tamanho, String item, int pesoDoItem) {
        String strItem = Integer.toString(pesoDoItem);
        int qtdDeTracos = ((tamanho - item.length()) - (strItem.length() + 4));
        if (strItem.length() == 1) {
            qtdDeTracos--;
        }
        System.out.print("| " + item);
        fazTraco(qtdDeTracos, '.', false, false);
        strItem = (strItem.length() >= 2) ? "(" + pesoDoItem + ")" : "(0" + pesoDoItem + ")";
        System.out.print(strItem);
        System.out.println(" |");
    }

    /**
     * Método responsavel por exibir uma mensagem simples ou composta com duas escolhas (Sim ou Não) formatados.
     *
     * @param tamanho Determina o tamanho do limite das bordas.
     * @param mensagem Mensagem a ser exibida.
     * @param poemEscolha Determina se deve exitir opções na mensagem.
     */
    private static void mensagem(int tamanho, String mensagem, boolean poemEscolha) {
        barra(tamanho);
        textoSimples(tamanho, mensagem, true);
        if (poemEscolha) {
            textoDuplo(tamanho, "Sim__(01)", "Não__(02)");
            barra(tamanho);
        } else {
            barra(tamanho);
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
