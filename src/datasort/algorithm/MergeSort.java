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
public class MergeSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        mergeSort(array, 0, array.length - 1);

        return array;

//       int size = array.length;
//        //At the size one, it is already sorted and it can't be divided again.
//        if (size < 2) {
//            return array;
//        }
//        //Divide the array in half - left and right - looking for odd and even lenghts
//        int mid;
//        T[] left, right;
//        if (size % 2 == 0) {
//            mid = size / 2;
//            right = (T[]) new Comparable[mid];
//        } else {
//            mid = (size / 2) + 1;
//            right =(T[]) new Comparable[mid - 1];
//        }
//
//        left = (T[]) new Comparable[mid];
//
//        for (int i = 0; i < right.length; i++) {
//            right[i] = (T) array[mid + i];
//        }
//        for (int i = 0; i < left.length; i++) {
//            left[i] = (T) array[i];
//        }
//        //Send the new arrays to be divided
//        left = (T[]) execute(left);
//        right = (T[]) execute(right);
//        
//        T[] merged = merge(left, right);
//        return merged;
    }

//    public static <T extends Comparable<T>> T[] merge(T[] array1, T[] array2) {
//        T[] merged = ((T[]) new Comparable[array1.length + array2.length]);
//        int posA2 = 0, posMerged = 0;
//
//        //Compare the elements in Array 1 with Array 2 and place it
//        for (int posA1 = 0; posA1 < array1.length; posA1++) {
//            if (posA2 == array2.length) {
//                merged[posMerged] = (T) array1[posA1];
//                posMerged++;
//            } else if (array1[posA1].compareTo(array2[posA2]) <= 0) {
//                merged[posMerged] = (T) array1[posA1];
//                posMerged++;
//            } else {
//                merged[posMerged] = (T) array2[posA2];
//                posA2++;
//                posMerged++;
//                posA1--;
//            }
//        }
//
//        //if it stills elements on Array 2
//        for (int j = posA2; j < array2.length; j++) {
//            merged[posMerged] = (T) array2[j];
//            posMerged++;
//        }
//
//        return (T[]) merged;
//    }

    //Divide-Conquer 
    public static <T extends Comparable<T>> void mergeSort(T[] array, int posIni, int posFin) {
        if (posIni < posFin) {

            int mid = (posIni + posFin) / 2;

            //Divide the prolem
            mergeSort(array, posIni, mid);
            mergeSort(array, mid + 1, posFin);

            //Conquer - merge the two sorted parts
            merge(array, posIni, mid, posFin);

        }
    }

    public static <T extends Comparable<T>> void merge(T[] array, int posIni, int mid, int posFin) {
        //first array starts at posIni ends in mid
        //second array start at mid+1 and ends in posFin
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
        for(int i = 0; i < tmp.length; i++){
            array[posIni++] = tmp[i];
        }

    }

}
