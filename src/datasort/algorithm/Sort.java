/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.algorithm;

/**
 *
 * @author willi
 */
public abstract class Sort {

    public abstract <T extends Comparable<T>> T[] execute(T[] array);

    public <T extends Comparable<T>> void print(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i].toString() + " - ");
        }
    }

    public <T extends Comparable<T>> void swap(T[] array, int posObj1, int posObj2) {
        T tmp = array[posObj1];
        array[posObj1] = array[posObj2];
        array[posObj2] = tmp;
    }
    
    public <T extends Comparable<T>> void reverseArray(T[] array){
        int size = array.length;
        for(int i = 0 ; i < size/2;i++){
            swap(array, i, size-1-i);
        }
        
    }
}
