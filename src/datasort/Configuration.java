/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.algorithm.HeapSort;
import datasort.algorithm.InsertionSort;
import datasort.algorithm.MergeSort;
import datasort.algorithm.SelectionSort;
import datasort.algorithm.Sort;
import datasort.data.ElementPair;
import javax.management.InvalidAttributeValueException;

/**
 *
 * @author willi
 */
public class Configuration {

    public OrderBy orderBy = OrderBy.BOTH; // configurar a ordenação dos dados
    public boolean firstElementIsInteger = false; // configurar se os dados serão inteiros ou strings
    public boolean secondElementIsInteger = false; // configurar se os dados serão inteiros ou strings
    private static Configuration config;
    public static final String splitTag = ",";
    private String filePath = "C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\DataSort\\src\\datasort\\data.txt";
    public static int sortType = 1;
    private Sort sort;
    public boolean debugModeOn;

    public Sort getSort() {
        return sort;
    }

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

    void setElementsType(ElementPair pair) throws InvalidAttributeValueException {
        if (pair == null) {
            throw new InvalidAttributeValueException();
        }

        Object element1 = pair.getElement1();
        if (element1 instanceof Integer) {
            firstElementIsInteger = true;
        }

        Object element2 = pair.getElement2();
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

    void setOrderBy(int option) {
        String orderByStr;
        if (option == 1) {
            orderByStr = "First";
        } else if (option == 2) {
            orderByStr = "Second";
        } else if (option == 3) {
            orderByStr = "Both";
        } else {
            this.orderBy = OrderBy.FIRST;
            System.out.println("Opcao nao reconhecida");
            System.out.println("Ordernacao feita pelo primeiro elemento por padrao");
            return;
        }
        this.orderBy = OrderBy.getEnum(orderByStr);
    }

    void setSortType(int option) {
        switch (option) {
            case 1:
                sort = new InsertionSort();
                break;
            case 2:
                sort = new SelectionSort();
                break;
            case 3:
                sort = new HeapSort();
                break;
            case 4:
                sort = new MergeSort();
                break;
            default:
                System.out.println("Opcao nao reconhecida. Por padrao, foi setado Insertion Sort");
                sort = new InsertionSort();
        }
    }

}
