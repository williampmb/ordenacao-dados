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
 * @author william
 */
public class FirstElementComparator  implements MyComparator {

    @Override
    public int compare(ElementPair o1, ElementPair o2) {
       return (Integer)o1.getElement1() - (Integer)o2.getElement2();
    }

    @Override
    public int compareAsInt(ElementPair o1, ElementPair o2) {
        return (Integer)o1.getElement1() - (Integer)o2.getElement2();
    }

    @Override
    public int compareAsStr(ElementPair o1, ElementPair o2) {
        return ((String)o1.getElement1()).compareTo((String)o2.getElement2());
    }
    
}
