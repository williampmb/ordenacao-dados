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
        T[] clone = array.clone();
        config = Configuration.getInstance();
        mergeSort(array, 0, array.length - 1);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        mergesortInPlace(clone, 0, clone.length);
        System.out.print("Jyrki Katajainen MergeSort Inplace: ");
        print(clone);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return array;
    }

    // Divide-Conquer
    // Minha implementação de MergeSort usando O(N) extra memória, pois precisa
    // ordernar em um segundo array. Mais embaixo segue a implementação inplace
    // do mergesort
    public <T extends Comparable<T>> void mergeSort(T[] array, int posIni, int posFin) {
        if (posIni < posFin) {

            int mid = (posIni + posFin) / 2;

            //Divide the prolem
            mergeSort(array, posIni, mid);
            mergeSort(array, mid + 1, posFin);
            if (config.debugModeOn && posIni <= mid) {
                System.out.println("Array Esquerda");
                printSubArray(array, posIni, mid);
                System.out.println("");
            }
            if (config.debugModeOn && mid + 1 <= posFin) {
                System.out.println("Array Direita");
                printSubArray(array, mid + 1, posFin);
                System.out.println("");
            }
            //Conquer - merge the two sorted parts
            merge(array, posIni, mid, posFin);
            if (config.debugModeOn && posFin - posIni > 0) {
                System.out.println("Merge dos dois arrays");
                printSubArray(array, posIni, posFin);
                System.out.println("");
            }

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

    /*
    * MergeSort in-place proposto por Jyrki Katajainen, Tomi Pasanen,
    * Jukka Teuhola. ``Practical in-place mergesort''.
    * Nordic Journal of Computing, 1996.
    * A ideia é dividir o array em 2 partes e ordenar uma das partes em uma
    * área de trabalho trocando os elementos ordenandos na area de trabalho..
    * http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.22.5514&rep=rep1&type=pdf
    * source code adaptado para Java. Fonte:
    * https://github.com/liuxinyu95/AlgoXY/blob/algoxy/sorting/merge-sort/src/mergesort.c
     */
    public <T extends Comparable<T>> void mergesortInPlace(T[] array, int posIni, int lenght) {
        int mid, n, workingArea;
        if (lenght - posIni > 1) {
            mid = posIni + (lenght - posIni) / 2;
            workingArea = posIni + lenght - mid;
            workingAreaSort(array, posIni, mid, workingArea);
            /* a segunda metade está ordenada */
            while (workingArea - posIni > 2) {
                n = workingArea;
                workingArea = posIni + (n - posIni + 1) / 2;
                workingAreaSort(array, workingArea, n, posIni);
                /* A primeira metade da area de trabalho anterior contém elementos ordenados */
                workingAreaMerge(array, posIni, posIni + n - workingArea, n, lenght, workingArea);
            }
            for (n = workingArea; n > posIni; --n) /* utiliza insertion sort */ {
                for (mid = n; mid < lenght && array[mid].compareTo(array[mid - 1]) < 0; ++mid) {
                    swap(array, mid, mid - 1);
                }
            }
        }
    }

    /*
    * ordena posIni até lenght-1 e coloca o resultado em working area
     */
    public <T extends Comparable<T>> void workingAreaSort(T[] array, int posIni, int length, int workingArea) {
        int mid;
        if (length - posIni > 1) {
            mid = posIni + (length - posIni) / 2;
            mergesortInPlace(array, posIni, mid);
            mergesortInPlace(array, mid, length);
            workingAreaMerge(array, posIni, mid, mid, length, workingArea);
        } else {
            while (posIni < length) {
                swap(array, posIni++, workingArea++);
            }
        }
    }

    public <T extends Comparable<T>> void workingAreaMerge(T[] array, int posIni1, int posFin1, int posIni2, int posFin2, int workingArea) {

        while (posIni1 < posFin1 && posIni2 < posFin2) {
            swap(array, workingArea++, array[posIni1].compareTo(array[posIni2]) < 0 ? posIni1++ : posIni2++);
        }
        while (posIni1 < posFin1) {
            swap(array, workingArea++, posIni1++);
        }
        while (posIni2 < posFin2) {
            swap(array, workingArea++, posIni2++);
        }
    }

}
