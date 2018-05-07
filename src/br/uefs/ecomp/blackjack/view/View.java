/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.view;
import br.uefs.ecomp.blackjack.controller.BlackJackController;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.Iterador;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Uellington Damasceno
 */


public class View {
    static BlackJackController controller = new BlackJackController();
    public static void main(String[] args) throws IOException {
        controller.carregarUsers("Logins.txt");
        int numDeBaralho;
        menuRegras(30);
        switch(lerInt(true, 1, 2)){
            case 1:{
                numDeBaralho = 8;
                break;
            }
            case 2:{
                numDeBaralho = 4;
                break;
            }
        }
        boolean repetirMenuPrincipal;
        do{
            repetirMenuPrincipal = false;
            menuPrincipal(30);
            switch(lerInt(true, 1, 5)){
                case 1:{
                    repetirMenuPrincipal = cadastrarUser(30);
                    break;
                }
                case 2:{
                    iniciarPartida(30);
                    break;
                }
                case 3:{
                    Iterador itPontuacao = controller.verPontuacao();
                    while(itPontuacao.hasNext()){
                        Jogador jogadorObtido = (Jogador) itPontuacao.next();
                        System.out.println(jogadorObtido);
                    }
                
                    break;
                }
                case 4:{
                    repetirMenuPrincipal = true;
                }
                case 5:{
                    mensagem(30, "Realmente deseja sair?", true);
                    switch(lerInt(true, 1, 2)){
                        case 1:{
                            mensagem(30, "Até a proxima", false);
                            System.exit(0);
                            break;
                        }
                        case 2:{
                            repetirMenuPrincipal = true;
                            break;
                        }
                    }
                    break;
                }
            }
        }while(repetirMenuPrincipal);
        
        
    }
    private static void menuPrincipal(int tamanho){
        barra(tamanho);
        textoSimples(tamanho, "Menu Principal - BlackJack", true);
        separador(tamanho, true);
        novoItem(tamanho, "Novo jogador", 1);
        novoItem(tamanho, "Nova Partida", 2);
        novoItem(tamanho, "Pontuação", 3);
        novoItem(tamanho, "Regras", 4);
        separador(tamanho, true);
        novoItem(tamanho,"Sair", 5);
        barra(tamanho);
    }
    private static boolean cadastrarUser(int tamanho){
        String user, senha, confirmaSenha;
        boolean repetirCadastro;
        Scanner input = new Scanner(System.in);
        do{
            repetirCadastro = false;
            System.out.print("Usuario: ");
            user = input.nextLine();
            System.out.print("Senha: ");
            senha = input.nextLine();
            System.out.print("Confirma Senha: ");
            confirmaSenha = input.nextLine();
            if(senha.equals(confirmaSenha)){
                try {
                    if(controller.cadastrarNovoJogador(user, senha)){
                        mensagem(tamanho, "Deseja cadastar novo jogador?", true);
                        switch(lerInt(true, 1, 2)){
                            case 1:{
                                repetirCadastro = true;
                                break;
                            }
                            case 2:{
                                return true;
                            }
                        }
                    }else{
                        barra(tamanho);
                        textoSimples(tamanho, "Jogador não cadastrado!", true);
                        textoSimples(tamanho, "Jogador já existe!", true);
                        barra(tamanho);
                        repetirCadastro = true;
                    }
                } catch (IOException ex) {
                    return false;
                }
            }else{
                mensagem(tamanho, "Senha não confere!", false);
                repetirCadastro = true;
            }
        }while(repetirCadastro);
        return false;
    }
    private static void iniciarPartida(int tamanho){
        boolean repetirPartida, repetir;
        String user, senha;
        Scanner input = new Scanner(System.in);
        do{
            repetirPartida = false;
            barra(tamanho);
            textoSimples(tamanho, "Quantos jogadores vai jogar?", false);
            textoSimples(tamanho, "Min >1< :: Max >5<", true);
            barra(tamanho);
            int qtdJogadores = lerInt(true, 1, 5);
            for(int i = 0; i < qtdJogadores; i++){
                do{
                    repetir = false;
                    System.out.print("User: ");
                    user = input.nextLine();
                    System.out.print("Senha: ");
                    senha = input.nextLine();
                    switch(controller.inserirJogadorEmPartida(user, senha)){
                        case 0:{
                            barra(tamanho);
                            textoSimples(tamanho, "Usuario ou senha invalido!", true);
                            textoSimples(tamanho, "Tente novamente!", true);
                            barra(tamanho);
                            repetir = true;
                            break;
                        }
                        case 1:{
                            barra(tamanho);
                            textoSimples(tamanho, ("Usuario: " + user + " irá jogar!"), true);
                            barra(tamanho);
                            break;
                        }case 2:{
                            barra(tamanho);
                            textoSimples(tamanho, user + " Já está cadastrado(a)!", true);
                            textoSimples(tamanho, "Cadastre outro jogador!", true);
                            barra(tamanho);
                            repetir = true;
                            break;
                        }
                        
                    }
                }while(repetir);
            }
        }while(repetirPartida);
    }
    private static void menuRegras(int tamanho){
        barra(tamanho);
        textoSimples(tamanho, "Menu de regras", true);
        separador(tamanho, true);
        novoItem(tamanho, "Las Vegas", 1);
        novoItem(tamanho, "Italiano", 2);
        separador(tamanho, true);
        novoItem(tamanho, "Voltar", 3);
        barra(tamanho);
    }
    
    private static void barra(int tamanho) {
        System.out.print("+");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("=");
        }
        System.out.println("+");
    }
    private static void textoSimples(int tamanho, String texto, boolean centralizar) {
        int tamanhoDaBarra = tamanho - texto.length();
        int espacosDaEsquerda, espacosDaDireita;
        espacosDaEsquerda = centralizar ? tamanhoDaBarra / 2 : 1;   
        espacosDaDireita = tamanhoDaBarra - espacosDaEsquerda;
        fazTraco(espacosDaEsquerda, ' ', true, false);
        System.out.print(texto);
        fazTraco(espacosDaDireita, ' ', false, true);
    }
    private static void textoDuplo(int tamanho, String texto01, String texto02){
        int totalDeTexto = texto01.length() + texto02.length();
        int novoTamanhoDoMenu = tamanho - totalDeTexto;
        int espacosLaterais = novoTamanhoDoMenu/3;
        
        fazTraco(espacosLaterais, ' ', true, false);
        System.out.print(texto01);
        fazTraco(novoTamanhoDoMenu-(espacosLaterais*2), ' ', false, false);
        System.out.print(texto02);
        fazTraco(espacosLaterais, ' ', false, true);
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
    private static void novoItem(int tamanho, String item, int pesoDoItem) {
        String strItem = Integer.toString(pesoDoItem);
        int qtdDeTracos = ((tamanho - item.length()) - (strItem.length() + 4));
        if(strItem.length() == 1){
           qtdDeTracos--;
        }
        System.out.print("| " + item);
        fazTraco(qtdDeTracos, '.', false, false);
        strItem = (strItem.length() >= 2) ? "(" + pesoDoItem + ")" : "(0" + pesoDoItem + ")";
        System.out.print(strItem);
        System.out.println(" |");
    }
    private static void mensagem(int tamanho, String mensagem, boolean poemEscolha){
        barra(tamanho); 
        textoSimples(tamanho, mensagem, true);
        if(poemEscolha){
            textoDuplo(tamanho, "Sim__(01)", "Não__(02)");
            barra(tamanho);
        }
        else{
            barra(tamanho);
        }
    }
    private static void fazTraco(int tamanho, char tipoDeTraco, boolean bordaE, boolean bordaD) {
        if(bordaE){
            System.out.print("|");
        }
        for (int i = 0; i < tamanho; i++) {
            System.out.print(tipoDeTraco);
        }
        if(bordaD){
            System.out.println("|");
        }
    }
    private static int lerInt(boolean limite, int min, int max){
        Scanner input = new Scanner(System.in);
        int valor;
        boolean repetir;
        do{
            try {
                valor = input.nextInt();
                input.nextLine();
                if(limite){
                    if(min <= valor && max >= valor){
                        return valor;
                    }else{
                        repetir = true;
                    }
                }else{
                    return valor;
                }

            } catch (Exception e) {
                input.nextLine();
                repetir = true;
            }
        }while(repetir);
        return 0;
    }
}
