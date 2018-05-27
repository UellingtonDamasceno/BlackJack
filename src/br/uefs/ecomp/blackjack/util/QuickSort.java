package br.uefs.ecomp.blackjack.util;

/**
 * Classe responsável por criar objetos capazes de ordenar dados.
 * 
 * @author Uellington Damasceno e Anésio Sousa
 */
public class QuickSort {
    
    /**
     * 
     * @param vetor
     * @param inicio
     * @param fim 
     */
    public void quickSort(Comparable[] vetor, int inicio, int fim){
        if (inicio < fim) {
            int pe = inicio;
            int pivo = fim;
            int pd = fim - 1;
            while (pe <= pd) {
                while (pe <= pd && vetor[pe].compareTo(vetor[pivo]) > 0) {
                    pe++;
                }
                while (pe <= pd && vetor[pd].compareTo(vetor[pivo]) < 0) {
                    pd--;
                }
                if (pe <= pd) {
                    swap(vetor, pe, pd);
                    pe++;
                    pd--;
                }
            }
            swap(vetor, pe, pivo);
            quickSort(vetor, inicio, pe - 1);
            quickSort(vetor, pe + 1, fim);
        }
    }
    
    /**
     * 
     * @param c
     * @param posUm
     * @param posDois 
     */
    public void swap(Object[] c, int posUm, int posDois) {
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }
}
