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
public class MergeSort extends Sort {

    static Configuration config = null;

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        config = Configuration.getInstance();
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    //Divide-Conquer 
    public <T extends Comparable<T>> void mergeSort(T[] array, int posIni, int posFin) {
        if (posIni < posFin) {

            int mid = (posIni + posFin) / 2;

            //Divide the prolem
            mergeSort(array, posIni, mid);
            mergeSort(array, mid + 1, posFin);
            System.out.println("");
            if (config.debugModeOn && posIni <= mid) {
                System.out.println("Array Esquerda");
                printSubArray(array, posIni, mid);
            }
            if (config.debugModeOn && mid + 1 <= posFin) {
                System.out.println("Array Direita");
                printSubArray(array, mid + 1, posFin);
            }
            //Conquer - merge the two sorted parts
            merge(array, posIni, mid, posFin);
            if (config.debugModeOn && posFin - posIni > 0) {
                System.out.println("Merge dos dois arrays");
                printSubArray(array, posIni, posFin);
            }
            System.out.println("");
        }
    }

    public <T extends Comparable<T>> void merge(T[] array, int posIni, int mid, int posFin) {
        //Fazer o merge de dois pedacos de array ordenados
        //O array da esquerda inicia na posIni e termina na posicao min
        //O array da direita inicia no mid+1 e termina no posFin
        int left = posIni;
        int right = mid + 1;
        int posTmp = 0;
        T[] tmp = (T[]) new Comparable[posFin - posIni + 1];

        while (left < mid + 1 && right < posFin + 1) {
            if (array[left].compareTo(array[right]) < 0) {
                tmp[posTmp++] = array[left++];
            } else {
                tmp[posTmp++] = array[right++];
            }
        }

        while (right < posFin + 1) {
            tmp[posTmp++] = array[right++];
        }
        while (left < mid + 1) {
            tmp[posTmp++] = array[left++];
        }

        left = posIni;
        for (int i = 0; i < tmp.length; i++) {
            array[posIni++] = tmp[i];
        }

    }

}
