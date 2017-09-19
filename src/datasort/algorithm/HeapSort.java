/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.algorithm;

import datasort.Configuration;
import datasort.structure.MyHeap;

/**
 *
 * @author willi
 */
public class HeapSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {

        Configuration config = Configuration.getInstance();
        if (config.debugModeOn) {
            return executeWithDebug(array);
        } else {
            MyHeap myHeap = MyHeap.buildHeapFromArray(array);

            /*Heapsort in-place. O objeto MyHeap compartilha o mesmo array que o
        * array de entrada. Neste array, dentro da classe o MyHeap, para cada 
        * elemento de 0 até array.length-1, ele retira o elemento do array e
        * insere no array ajustando o heap conforme necesseário. No fim, para manter
        * a ordem, ele chama N vezes o min() do heap retirando em ordem crescente
        * os elementos e organizando numa ordem invertida do array para não
        * sobrescrever os elementos do array.
             */
            for (int i = 0; i < array.length; i++) {
                array[array.length - 1 - i] = (T) myHeap.min();
            }
            //Como o array está em ordem decrescente, é necessário inverter a ordem
            // do array.
            reverseArray(array);

        }

        return array;
    }

    public <T extends Comparable<T>> T[] executeWithDebug(T[] array) {

        MyHeap myHeap = MyHeap.buildHeapFromArray(array);

        System.out.println("Ao montar o Min MyHeap: ");
        print(array);
        System.out.println("------------------------------------------------");

        System.out.println("Agora devemos retirar o min e adicionalo no final para nao sobrescrever elementos: ");
        for (int i = 0; i < array.length; i++) {
            array[array.length - 1 - i] = (T) myHeap.min();
            print(array);
        }

        System.out.println("------------------------------------------------");
        System.out.println("Para ter o array na ordem crescente e preciso inverte-lo");
        int size = array.length;
        for (int i = 0; i < size / 2; i++) {
            swap(array, i, size - 1 - i);
            print(array);
        }
        return array;
    }

}
