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
public class BubbleSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        Configuration config = Configuration.getInstance();
        /* No Bubble Sort, precisamos trocar dois elementos que não estão em 
        * ordem até que nenhuma troca for feita ao pecorrer todo o array. Isso
        * quer dizer que na troca, empurramos os maiores pra direita e os menores
        * para a esquerda.
         */
        if (config.debugModeOn) {
            return executeWithDebug(array);
        } else {
            int swap = -1;
            int size = array.length - 1;
            while (swap != 0) {
                swap = 0;
                for (int i = 0; i < size; i++) {
                    if (array[i].compareTo(array[i + 1]) > 0) {
                        T tmp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = tmp;
                        swap++;
                    }
                }
            }
        }
        return array;
    }

    public <T extends Comparable<T>> T[] executeWithDebug(T[] array) {
        int swap = -1;
        int size = array.length - 1;
        System.out.println("Posição de 0 até " + size);
        while (swap != 0) {
            swap = 0;
            System.out.println("");

            for (int i = 0; i < size; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    print(array);
                    T tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    swap++;
                    print(array);
                    System.out.println("Swap: " + (i) + " e " + (i + 1));
                }
            }
        }
        return array;
    }

}
