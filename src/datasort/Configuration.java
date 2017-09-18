/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.data.ElementPair;
import javax.management.InvalidAttributeValueException;

/**
 *
 * @author willi
 */
public class Configuration {

    public static final OrderBy orderBy = OrderBy.BOTH; // configurar a ordenação dos dados
    public boolean firstElementIsInteger = false; // configurar se os dados serão inteiros ou strings
    public boolean secondElementIsInteger = false; // configurar se os dados serão inteiros ou strings
    private static Configuration config;
    public static final String splitTag = ",";
    private String filePath = "C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\DataSort\\src\\datasort\\data.txt";
    public static int sortType = 1;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static Configuration getInstance() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    private Configuration() {

    }

    void setElementsType(ElementPair first) throws InvalidAttributeValueException {
        if (first == null) {
            throw new InvalidAttributeValueException();
        }

        Object element1 = first.getElement1();
        if (element1 instanceof Integer) {
            firstElementIsInteger = true;
        }

        Object element2 = first.getElement2();
        if (element2 instanceof Integer) {
            secondElementIsInteger = true;
        }
    }

    public void setVariableType(String firstElement, String secondElement) {
        if (checkIfInteger(firstElement)) {
            firstElementIsInteger = true;
        }
        if (checkIfInteger(secondElement)) {
            firstElementIsInteger = true;
        }
    }

    public boolean checkIfInteger(String ele1) {
        for (char c : ele1.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
