/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

/**
 *
 * @author willi
 */
 public enum OrderBy {
        FIRST("First"),
        SECOND("Second"),
        BOTH("Both");

        OrderBy(String type) {
            this.value = type;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        static OrderBy getEnum(String type) {
            for (OrderBy orderType : OrderBy.values()) {
                if (orderType.getValue().equals(type)) {
                    return orderType;
                }
            }
            return null;
        }

    }
