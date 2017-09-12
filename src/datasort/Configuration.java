/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.OrderBy;


/**
 *
 * @author willi
 */
public class Configuration{

    public static final OrderBy orderBy = OrderBy.BOTH; // configurar a ordenação dos dados
    public static final boolean isInteger = false; // configurar se os dados serão inteiros ou strings
    private static Configuration config;
    
    public static Configuration getInstance(){
        if(config == null){
            config = new Configuration();
        }
        return config;
    }
    
    private Configuration() {
       
    }

}
