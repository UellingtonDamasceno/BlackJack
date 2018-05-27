package br.uefs.ecomp.blackjack.util;

/**
 * Classe responsável criar objetos que irão conter baralhos usados em partidas.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class Pilha implements IStack{
    private Celula top;
    private int tamanho;
    /**
     * Construtor da classe pilha, sem parametro.
     */
    public Pilha(){
        this.top = null;
        this.tamanho = 0;
    }
    
    private class Celula{
        private Object dados;
        private Celula proximo;
        
        public Celula(Object dados){
            this.dados = dados;
        }
        public Object getDados(){
            return dados;
        }
        public Celula getProximo(){
            return proximo;
        }
        public void setDados(Object dados){
            this.dados = dados;
        }
        public void setProximo(Celula proximo){
            this.proximo = proximo;
        }
    }
    /**
     * Método responsavel por inserir um elemento no topo da pilha.
     * @param o Objeto que vai ser inserido. 
     */
    @Override
    public void push(Object o) {
       Celula novaCelula = new Celula(o);
       novaCelula.setProximo(top);
       top = novaCelula;
       tamanho++;
    }
    /**
     * Método responsavel por remover o topo da Pilha.
     * @return Conteudo que está contido na celula do topo.
     */
    @Override
    public Object pop() {
        if(isEmpty()){
            return null;
        }
        else{
            Object dadoRemovido = top.getDados();
            top = top.getProximo();
            tamanho--;
            return dadoRemovido;
        }
    }
    /**
     * Método responsavel por devolver o dado que está contido no topo da pilha (Sem Remover).
     * @return Se pilha vazia: null, caso contrario dado.
     */
    @Override
    public Object peek() {
        return isEmpty() ? null: top.getDados();
    }
    /**
     * Método responsavel por verificar se a pilha está vazia.
     * @return Se pilha vazia: true, caso contrario false.
     */
    @Override
    public boolean isEmpty() {
       return top == null;
    }
    /**
     * @return Tamanho da pilha.
     */
    @Override
    public int size() {
        return tamanho;
    }
}
