package org.example;

import java.util.HashMap;

public class P2P_Template {

    HashMap<Integer, String> dictionary = new HashMap<>();

    P2P_Template(){
        dictionary.put(0,"Payload Format Indicator");
        dictionary.put(1,"Point of Initiation Method");
        dictionary.put(2,"Scheme Identifier");
        dictionary.put(3,"FI Name");
        dictionary.put(4,"IBAN");
        dictionary.put(5,"Amount");
        dictionary.put(6,"Particulars");
        dictionary.put(7,"Reserved for Future Use");
        dictionary.put(8,"Reserved for Future Use");
        dictionary.put(9,"Reserved for Future Use");
        dictionary.put(10,"CRC");
    }
}
