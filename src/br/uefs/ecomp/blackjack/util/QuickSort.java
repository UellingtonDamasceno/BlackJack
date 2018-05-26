/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.blackjack.util;

/**
 *
 * @author Uellington Damasceno
 */
public class QuickSort {
    
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
    public void swap(Object[] c, int posUm, int posDois) {
        Object carta = c[posUm];
        c[posUm] = c[posDois];
        c[posDois] = carta;
    }
}
