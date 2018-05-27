package br.uefs.ecomp.blackjack.controller;

import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uellington Damasceno
 */
public class ControllerArquivos {

    private ListaEncadeada users;
    private File logins;
    private File pontuacao;

    /**
     *
     */
    public ControllerArquivos() {
        this.users = new ListaEncadeada();
        this.logins = new File("Logins.bin");
        this.pontuacao = new File("Pontuação.txt");
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
     * @return return true caso a leitura tenha sido efetuada com sucesso e false caso contrario.
     * @throws IOException Exceções geradas com entrada e saidas de dados.
     */
    public boolean carregarUsers() throws IOException {
        if (!logins.exists()) {
            boolean createNewFile = logins.createNewFile();
            if (createNewFile) {
                lerArquivo(logins);
            } else {
                return false;
            }
        } else {
            lerArquivo(logins);
        }
        return true;
    }

    private void lerArquivo(File nomeArquivo) throws FileNotFoundException, IOException {
        FileInputStream arquivo = new FileInputStream(nomeArquivo);
        InputStreamReader leitor = new InputStreamReader(arquivo);
        BufferedReader buffer = new BufferedReader(leitor);
        String linha;
        do {
            linha = buffer.readLine();
            if (linha != null) {
                String info[] = linha.split(" : ");
                for (int i = 0; i < info.length; i += 4) {
                    int pontos = Integer.parseInt(info[i + 2]);
                    int partidas = Integer.parseInt(info[i + 3]);
                    Jogador jogador = new Jogador(info[i], info[i + 1], pontos, partidas);
                    users.insereFinal(jogador);
                }
            }
        } while (linha != null);
        leitor.close();
        arquivo.close();
        buffer.close();
    }

    /**
     * Método responsavel por cadastrar um novo jogador inserido-o em uma lista e ao final colocando no arquivo de usuarios cadastrados.
     *
     * @param user Nome do usuario a ser cadastado.
     * @param senha Senha do usuario.
     * @return true para cadastro efetuado com sucesso e false caso contrario.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean cadastrarNovoJogador(String user, String senha) throws FileNotFoundException, IOException {
        Jogador jogador = new Jogador(user, senha, 100);
        if (users.contem(jogador)) {
            return false;
        } else {
            escreverEmArquivo(logins, jogador, true, true);
            users.insereFinal(jogador);
        }
        return true;
    }

    private void escreverEmArquivo(File nomeArq, Jogador jogador, boolean senha, boolean sobEscreve) throws FileNotFoundException, IOException {
        FileOutputStream arquivo = new FileOutputStream(nomeArq, sobEscreve);
        PrintWriter pr = new PrintWriter(arquivo);
        if (senha) {
            pr.print(jogador.getUser() + " : ");
            pr.print(jogador.getSenha() + " : ");
            pr.print(jogador.getScore() + " : ");
            pr.println(jogador.getPartidasVencidas());
        } else {
            pr.println(jogador);
        }
        pr.close();
        arquivo.close();
    }

    /**
     *
     * @param user
     * @param senha
     * @return
     */
    public Jogador obterJogador(String user, String senha) {
        if (users.estaVazia()) {
            return null;
        } else {
            Iterador lJogadores = users.iterador();
            while (lJogadores.hasNext()) {
                Jogador obtido = (Jogador) lJogadores.next();
                if (obtido.getUser().equals(user) && obtido.getSenha().equals(senha)) {
                    return obtido;
                }
            }
        }
        return null;
    }

    public boolean atualizarArquivos() {
        Comparable suporte[] = new Comparable[users.tamanho()];
        for (int i = 0; !users.estaVazia(); i++) {
            suporte[i] = (Comparable) users.removeInicio();
        }
        QuickSort q = new QuickSort();
        q.quickSort(suporte, 0, suporte.length - 1);
        try {
            deletaRegistros(logins);
            deletaRegistros(pontuacao);
        for (Comparable jogadorObtido : suporte) {
            escreverEmArquivo(logins, (Jogador) jogadorObtido, true, true);
            escreverEmArquivo(pontuacao, (Jogador) jogadorObtido, false, true);
            users.insereFinal(jogadorObtido);
        }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param registros
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void deletaRegistros(File registros) throws FileNotFoundException, IOException {
        Writer clean = new BufferedWriter(new FileWriter(registros));
        clean.close();
    }

    /**
     *
     * @return
     */
    public Iterador listaDeUsers() {
        return users.iterador();
    }

}
