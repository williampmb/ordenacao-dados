/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.algorithm;

import datasort.Configuration;

/**
 *
 * @author willi
 */
public class QuickSort extends Sort {

    Configuration config;

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        config = Configuration.getInstance();

        quicksort(array, 0, array.length - 1);
        return array;
    }

    public <T extends Comparable<T>> void quicksort(T[] array, int left, int right) {
        // Ao reduzir a partição a um único elemento, o elemento já está ordenado
        if (left >= right) {
            return;
        }

        if (config.debugModeOn) {
            System.out.println("");
            System.out.println("Ordenar a particao do index " + left + " ate o index " + right);
            printSubArray(array, left, right);
        }

        int pivot = pickThePivot(array, left, right);
        int wall = left; //apenas pra ficar mais legível
        int current; //index do objeto que estou comparando com o pivot

        //Comparar cada elemento com o pivot, caso seja menor, colocar a esquerda
        // do wall e empurrar a wall para o próximo index
        for (current = wall; current <= right; current++) {
            if (current != pivot && array[current].compareTo(array[pivot]) < 0) {
                if (config.debugModeOn) {
                    System.out.println("Swap: " + wall + " with " + current);
                    print(array);
                }
                swap(array, wall, current);
                wall++;
                if (config.debugModeOn) {
                    print(array);
                }
            }
        }

        if (config.debugModeOn) {
            System.out.println("Swap the wall at: " + wall + " with pivot at:" + pivot);
            print(array);
        }
        //Depois de comparar todos da partição com o Pivot é preciso colocar o 
        // pivot logo após do wall para que todos os elementos a esquerda sejam
        // menores que ele e todos a direita sejam maiores
        swap(array, pivot, wall);

        if (config.debugModeOn) {
            print(array);
        }
        //ordernar os elementos do lado esquerdo do wall sem contar com o pivot
        quicksort(array, left, wall - 1);
        //ordernar os elementos do lado direito do wall sem contar com o pivot
        quicksort(array, wall + 1, right);
    }

    //Metodo para pegar o pivot, pode ser feito diversas formas para minimizar
    // a probabilidade de pegar um ruim pivot. Mas para esse exercício estou
    // apenas pegando o ultimo elemento da partição que está sendo ordenada
    // outra form apoderia pegar o elemento do meio da partiçao = left+right/2
    public <T extends Comparable<T>> int pickThePivot(T[] array, int left, int right) {
        return right;
    }

}
