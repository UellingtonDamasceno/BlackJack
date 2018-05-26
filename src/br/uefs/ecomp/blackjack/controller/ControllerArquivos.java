package br.uefs.ecomp.blackjack.controller;

import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.*;

/**
 *
 * @author Uellington Damasceno
 */
public class ControllerArquivos {

    private ListaEncadeada users;

    /**
     *
     */
    public ControllerArquivos() {
        this.users = new ListaEncadeada();
    }

    /**
     *
     * @param users
     */
    public void setUsers(ListaEncadeada users) {
        this.users = users;
    }

    /**
     *
     * @return
     */
    public ListaEncadeada getUsers() {
        return users;
    }

    /**
     * Dado um arquivo de layout (String : String : int : int) este método irá ler pegando as informações linha a linha e em seguida inserindo em uma lista encadeada.
     *
     * @param arquivo Nome do arquivo que deve ser lido.
     * @return return true caso a leitura tenha sido efetuada com sucesso e false caso contrario.
     * @throws IOException Exceções geradas com entrada e saidas de dados.
     */
    public boolean carregarUsers(File arquivo) throws IOException {
        if (!arquivo.exists() || !arquivo.isFile() || !arquivo.canRead()) {
            return false;
        } else {
            FileInputStream arq = new FileInputStream(arquivo);
            InputStreamReader input = new InputStreamReader(arq);
            BufferedReader bf = new BufferedReader(input);
            String linha;
            do {
                linha = bf.readLine();
                if (linha != null) {
                    String palavras[] = linha.split(" : ");
                    for (int i = 0; i < palavras.length; i += 4) {
                        int pontos = Integer.parseInt(palavras[i + 2]);
                        int partidas = Integer.parseInt(palavras[i + 3]);
                        Jogador jogador = new Jogador(palavras[i], palavras[i + 1], pontos, partidas);
                        users.insereFinal(jogador);
                    }
                }
            } while (linha != null);
            input.close();
            arq.close();
            bf.close();
        }
        return true;
    }

    /**
     * Método responsavel por cadastrar um novo jogador inserido-o em uma lista e ao final colocando no arquivo de usuarios cadastrados.
     *
     * @param arquivo
     * @param user Nome do usuario a ser cadastado.
     * @param senha Senha do usuario.
     * @return true para cadastro efetuado com sucesso e false caso contrario.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean cadastrarNovoJogador(File arquivo, String user, String senha) throws FileNotFoundException, IOException {
        Jogador jogador = new Jogador(user, senha, 100);       
        if(!arquivo.isFile() || users.contem(jogador)){
            return false;
        } else {
            FileOutputStream arq = new FileOutputStream(arquivo, true);
            PrintWriter pr = new PrintWriter(arq);
            pr.print(jogador.getUser() + " : ");
            pr.print(jogador.getSenha() + " : ");
            pr.print(100 + " : ");
            pr.println(jogador.getPartidas());
            users.insereFinal(jogador);
            pr.close();
            arq.close();
        }
        return true;
    }
    
    /**
     *
     * @param user
     * @param senha
     * @return
     */
    public Jogador obterJogador(String user, String senha){
        Iterador lJogadores = users.iterador();
        while(lJogadores.hasNext()){
            Jogador obtido = (Jogador) lJogadores.next();
            if(obtido.getUser().equals(user) && obtido.getSenha().equals(senha)){
                return obtido;
            }
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    public Iterador listaDeUsers() {
        return users.iterador();
    }

    /**
     *
     * @param users
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean gravarEmArquivo(ListaEncadeada temp) throws FileNotFoundException, IOException {
       /* Comparable suporte[] = new Comparable[temp.tamanho()];
        for(int i = 0; !temp.estaVazia(); i++){
            suporte[i] = (Comparable) temp.removeInicio();
        }
        QuickSort q = new QuickSort();
        q.quickSort(suporte, 0, suporte.length-1);
        for(int i = 0; i < suporte.length; i++){
            temp.insereFinal(suporte[i]);
        }*/
        FileOutputStream arquivo = new FileOutputStream("Pontuação Ordenada.txt", true);
        PrintWriter pr = new PrintWriter(arquivo);
        Iterador lJogadoresOrdenados = temp.iterador();
        while (lJogadoresOrdenados.hasNext()) {
            Jogador jogadorObtido = (Jogador) lJogadoresOrdenados.next();
            pr.println(jogadorObtido);
        }
        pr.close();
        arquivo.close();
        return true;
    }
}
