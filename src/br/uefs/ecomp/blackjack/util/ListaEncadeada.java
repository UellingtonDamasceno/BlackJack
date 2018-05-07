/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.util;

/**
 *Classe responsavel por gerenciar os métodos de construção de uma Lista encadeada.
 * Está classe tem seus métodos baseados na interface IList. ou seja, ListaEncadeada
 * implementou os metodos abstratos de IList.
 * 
 * @author Uellington Damasceno
 */

public class ListaEncadeada implements ILista{
    
    private Celula inicioDaLista;
    private Celula fimDaLista;
    private int tamanhoDaLista = 0;
    
    private class Celula{
        private Object dados;
        private Celula Proximo;
        /**
         * Consturtor da classe Celula que possui um argumento to dipo Object.
         * @param dados Corresponde ao objeto que será encapsulado e inserido 
         * posteriomente na lista.
         */
        public Celula(Object dados) {
            this.dados = dados;
        }
        /**
         * Responsável por devolver o objeto que está envolvido na celula.
         * @return Objeto da celula.
         */
        public Object getDados() {
            return dados;
        }
        /**
         * Responsável por permitir/alterar os valores que estão dentro de deter-
         * minada instância da classe Celula;
         * @param dados Valor que será utilizado para substituido/armazenado na
         * celula.
         */
        public void setDados(Object dados) {
            this.dados = dados;
        }
        /**
         * Responsavel por devolver endereço do proximo elemento na lista. 
         * @return O endereço do proximo elemento da lista. 
         */
        public Celula getProximo() {
            return Proximo;
        }
        /**
         * Responsavel por indicar/alterar o endereço do proximo elemento da lista;
         *
         * @param Proximo Endereço do proximo elemento da lista.
         */
        public void setProximo(Celula Proximo) {
            this.Proximo = Proximo;
        }
    }
    /**
     * Verifica se a lista está vazia;
     * @return true se lista vazia e false caso possua algum elemento na lista.
     */
    @Override
    public boolean estaVazia() {
        return tamanho() == 0;
    }
    /**
     * 
     * @return O tamanho da lista encadeada a qual ele foi submetido. 
     */
    @Override
    public int tamanho() {
        return tamanhoDaLista;
    }
    /**
     * Metodo responsavel por inserir uma nova celula no inicio da Lista encadeada.
     * @param objeto Objeto que será encapsulado e inserido na lista. 
     */
    @Override
    public void insereInicio(Object objeto) {
        Celula novaCelula = new Celula(objeto);
        if(estaVazia()){
            inicioDaLista = novaCelula;
            fimDaLista = inicioDaLista;
        }
        else{
            novaCelula.setProximo(inicioDaLista);
            inicioDaLista = novaCelula;
        }
        tamanhoDaLista++;
    }
    /**
     * Método responsável por inserir um elemento na ultima posição da Lista encadeada
     * 
     * @param objeto Objeto que será encapsulado e inserido na lista. 
     */
    @Override
    public void insereFinal(Object objeto) {
        Celula novaCelula = new Celula(objeto);
        if(estaVazia()){
            fimDaLista = novaCelula;
            inicioDaLista = fimDaLista;
        }
        else{
            fimDaLista.setProximo(novaCelula);
            fimDaLista = novaCelula;
        }
        tamanhoDaLista++;
    }
    /**
     * Método responsavel por remover o primeiro item da lista(Caso exista).
     * 
     * @return O objeto removido. 
     */
    @Override
    public Object removeInicio() {
        if(estaVazia()){
            return null;
        }
        else{
            Celula excluida = inicioDaLista;
            inicioDaLista = inicioDaLista.getProximo();
            tamanhoDaLista--;
            return excluida.getDados();
        }
    }
    /**
     * Responsavel por remover o ultimo elemento da lista. 
     * @return O objeto removido.
     */
    @Override
    public Object removeUltimo() {
        if(estaVazia()){
            return null;
        }
        else{
            Celula atual = inicioDaLista;
            Celula ant = atual;
            while(atual != fimDaLista){
                ant = atual;
                atual = atual.getProximo();
            }
            ant.setProximo(null);
            fimDaLista = ant;
            tamanhoDaLista--;
            return atual.getDados();
        }
        
    }

    @Override
    public boolean contem(Object o) {
        Celula temp = inicioDaLista;
        while(temp != null){
            if(temp.getDados().equals(o)){
                return true;
            }
            temp = temp.getProximo();
        }
        return false;
    }
    /**
     * @return novo iterador;
     */
    @Override
    public Iterador iterador() {
        return new MyIterador();
    }
    
 
    public class MyIterador implements Iterador {
        private Celula elemento = inicioDaLista;
        /**
         * Verifica se existe um proximo elemento na lista a qual foi submetido.
         * @return true caso exista e false caso contrario.
         */
        @Override
        public boolean hasNext() {
            return elemento != null;
        }
        /**
         * Responsavel por pegar o conteudo do Objeto que está na posição a qual 
         * ele está apontando.
         * @return Os dados de um determinado Objeto de uma dada esturtura.
         */
        @Override
        public Object next() {
            Celula dados = elemento;
            elemento = elemento.getProximo();
            return dados.getDados();
        }
    }
}
