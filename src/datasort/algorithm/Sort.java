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
public interface Sort {
    public <T extends Comparable<T>> T[] execute(T[] array);
    public <T extends Comparable<T>> void print(T[] array);
}
