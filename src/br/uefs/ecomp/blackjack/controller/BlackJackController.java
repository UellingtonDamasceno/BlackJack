/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.controller;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Uellington Damasceno
 */
public class BlackJackController {
    private ListaEncadeada users;
    private ListaEncadeada jogadoresEmPartida;
    
    public BlackJackController(){
        this.users = new ListaEncadeada();
        this.jogadoresEmPartida = new ListaEncadeada();
    }
    public void setUsers(ListaEncadeada users){
        this.users = users;
    }
    public void setJogadoresEmPartida(ListaEncadeada jogadoresEmPartida){
        this.jogadoresEmPartida = jogadoresEmPartida;
    }
    public ListaEncadeada getUsers(){
        return users;
    }
    public ListaEncadeada getJogadoresEmPartida(){
        return jogadoresEmPartida;
    }
    /**
     * Dado um arquivo de layout (String : String : int : int) este método irá ler pegando as informações 
     * linha a linha e em seguida inserindo em uma lista encadeada. 
     * @param arquivo Nome do arquivo que deve ser lido. 
     * @return return true caso a leitura tenha sido efetuada com sucesso e false caso contrario.
     * @throws IOException Exeções geradas com entrada e saidas de dados. 
     */
    public boolean carregarUsers(String arquivo) throws IOException{
        FileInputStream arq = new FileInputStream(arquivo);
        InputStreamReader input = new InputStreamReader(arq);
        BufferedReader bf = new BufferedReader(input);
        String linha;
        do{
            linha = bf.readLine();
            if(linha != null){
                String palavras[] = linha.split(" : ");
                for(int i = 0; i < palavras.length; i+=4){
                    int pontos = Integer.parseInt(palavras[i+2]);
                    int partidas = Integer.parseInt(palavras[i+3]);
                    Jogador jogador = new Jogador(palavras[i], palavras[i+1], pontos, partidas);
                    users.insereFinal(jogador);
                }
            }                
        }while(linha != null);
        arq.close();
        bf.close();
        return false;
    }
    /**
     * Método responsavel por cadastrar um novo jogador inserido-o em uma lista e ao final 
     * colocando no arquivo de usuarios cadastrados. 
     * @param user Nome do usuario a ser cadastado. 
     * @param senha Senha do usuario.
     * @return true para cadastro efetuado com sucesso e false caso contrario. 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public boolean cadastrarNovoJogador(String user, String senha) throws FileNotFoundException, IOException{
        Jogador jogador = new Jogador(user, senha);
        if(users.estaVazia()){
            users.insereInicio(jogador);
        }
        else if(users.contem(jogador)){
            return false;
        }else{
            FileOutputStream arquivo = new FileOutputStream("Logins.txt", true);
            PrintWriter pr = new PrintWriter(arquivo);
            pr.print(jogador.getUser()+" : ");
            pr.print(jogador.getSenha()+" : ");
            pr.print(jogador.getPontos() + " : ");
            pr.println(jogador.getPartidas());
            users.insereFinal(jogador);
            pr.close();
            arquivo.close();
        }
        return true;
    }
    
    public int inserirJogadorEmPartida(String user, String senha){
        Iterador lJogadores = users.iterador();
        while(lJogadores.hasNext()){
            Jogador jogadorObtido = (Jogador) lJogadores.next();
            if(jogadorObtido.getUser().equals(user) && jogadorObtido.getSenha().equals(senha)){
                if(jogadoresEmPartida.contem(jogadorObtido)){
                    return 2;
                }else{
                    jogadoresEmPartida.insereFinal(jogadorObtido);
                    return 1;
                }
            }
        }
        return 0;
    }
    public void partida(){
        
    }
    public Iterador verPontuacao(){
        return users.iterador();
    }
    public Iterador verJogadoresEmPartida(){
        return jogadoresEmPartida.iterador();
    }
    public boolean gerarArquivoOrdenado(ListaEncadeada jogadoresOrdenados) throws FileNotFoundException, IOException{
        FileOutputStream arquivo = new FileOutputStream("Pontuação Ordenada.txt");
        PrintWriter pr = new PrintWriter(arquivo);  
        Iterador lJogadoresOrdenados = jogadoresOrdenados.iterador();
        while(lJogadoresOrdenados.hasNext()){
            Jogador jogadorObtido = (Jogador) lJogadoresOrdenados.next();
            pr.print(jogadorObtido);
        }
        pr.close();
        arquivo.close();
        return true;
    }
}
