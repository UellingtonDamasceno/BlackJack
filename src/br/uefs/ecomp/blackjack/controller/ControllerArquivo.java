/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.controller;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.*;

/**
 *
 * @author Uellington Damasceno
 */
public class ControllerArquivo {
    private ListaEncadeada users;
    
    public ControllerArquivo(){
        this.users = new ListaEncadeada();
    }
    public void setUsers(ListaEncadeada users){
        this.users = users;
    }
    public ListaEncadeada getUsers(){
        return users;
    }
   
    /**
     * Dado um arquivo de layout (String : String : int : int) este método irá ler pegando as informações 
     * linha a linha e em seguida inserindo em uma lista encadeada. 
     * @param arquivo Nome do arquivo que deve ser lido. 
     * @return return true caso a leitura tenha sido efetuada com sucesso e false caso contrario.
     * @throws IOException Exceções geradas com entrada e saidas de dados. 
     */
    public boolean carregarUsers(String arquivo) throws IOException{
        FileInputStream arq = new FileInputStream(arquivo);
        InputStreamReader input = new InputStreamReader(arq);
        BufferedReader bf = new BufferedReader(input);
        String linha;
        //Fazer verificações relacionadas ao arquivo. 
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
    
    public Iterador listaDeUsers(){
        return users.iterador();
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
