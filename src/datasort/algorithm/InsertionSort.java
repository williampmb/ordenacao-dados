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
public class InsertionSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        Configuration config = Configuration.getInstance();
        //O primeiro elemento estará sempre ordenado, na primeira iteração, pois
        //a parte ordenada estará vazia. Assim, podemos começar pelo segundo 
        // elemento
        if (config.debugModeOn) {
            return executeWithDebug(array);
        } else {
            for (int i = 1; i < array.length; i++) {
                int pos = i;
                while (pos - 1 >= 0) {
                    if (array[pos].compareTo(array[pos - 1]) < 0) {
                        swap(array, pos, pos - 1);
                        pos--;
                    } else {
                        break;
                    }
                }
            }
        }

        return array;
    }

    public <T extends Comparable<T>> T[] executeWithDebug(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int pos = i;
            System.out.println("-------------");
            System.out.println("Comparando elemento na posicao " + i + " com a parte ordenada 0 ate "+ (i-1) );
            print(array);
            while (pos - 1 >= 0) {
                if (array[pos].compareTo(array[pos - 1]) < 0) {
                    swap(array, pos, pos - 1);
                    pos--;
                } else {
                    break;
                }
                print(array);
            }
        }
        return array;
    }
}
