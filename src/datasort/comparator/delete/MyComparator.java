/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.comparator.delete;

import datasort.data.ElementPair;
import java.util.Comparator;

/**
 *
 * @author willi
 */
public interface MyComparator extends Comparator<ElementPair>{

    public int compareAsInt(ElementPair o1, ElementPair o2);

    public int compareAsStr(ElementPair o1, ElementPair o2);

}
