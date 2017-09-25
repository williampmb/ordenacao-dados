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
public class SelectionSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {

        Configuration config = Configuration.getInstance();
        if (config.debugModeOn) {
            return executeWithDebug(array);
        } else {
            //Sort the i..n unsorted elements
            for (int i = 0; i < array.length - 1; i++) {
                int posMin = i;
                //find the minimum element in unsorted part
                for (int j = i + 1; j < array.length; j++) {
                    //To make the alg stable, we must use just < instead of <= 
                    //otherwise it would change the equals elements 
                    if (array[j].compareTo(array[posMin]) < 0) {
                        posMin = j;
                    }
                }
                //swap elements if the current posMin element is less than element[i]
                // and it is not the same element
                if (posMin != i) {
                    swap(array, i, posMin);
                }
            }
        }

        return array;
    }

    public <T extends Comparable<T>> T[] executeWithDebug(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int posMin = i;
            System.out.println("-------------");
            System.out.println("Para a posicao " + i + " procurar o menor até "+ (array.length - 1) );
            for (int j = i + 1; j < array.length; j++) {

                if (array[j].compareTo(array[posMin]) < 0) {
                    
                    posMin = j;
                }
            }
            if (posMin != i) {
                System.out.println("O menor está na posicao: " + posMin + " tocar posicao com o i: " + i);
                swap(array, i, posMin);
            }else{
                System.out.println("Nao foi achado um menor que i");
            }
             print(array);
        }
        return array;
    }

}
