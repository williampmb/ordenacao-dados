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
public class ShellSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        Configuration config = Configuration.getInstance();

        if (config.debugModeOn) {
            return executeWithDebug(array);
        } else {
            int size = array.length;
            for (int gap = size / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < size; i++) {
                    for (int j = i; j - gap >= 0 && array[j].compareTo(array[j - gap]) > 0; j -= gap) {
                        T tmp = array[j];
                        array[j] = array[j - gap];
                        array[j - gap] = tmp;
                    }
                }
            }
        }

        return array;
    }

    public <T extends Comparable<T>> T[] executeWithDebug(T[] array) {
        int size = array.length;
        for (int gap = size / 2; gap > 0; gap /= 2) {
            System.out.println("Distancia de comparacao: " + gap);
            for (int i = gap; i < size; i++) {
                for (int j = i; j - gap >= 0 && array[j].compareTo(array[j - gap]) < 0; j -= gap) {
                    System.out.println("Swap index: " + j + " with : " + (j - gap));
                    print(array);
                    T tmp = array[j];
                    array[j] = array[j - gap];
                    array[j - gap] = tmp;
                    print(array);
                }
            }
        }
        return array;
    }

}
