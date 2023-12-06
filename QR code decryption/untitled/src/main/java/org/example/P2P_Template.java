package org.example;

import java.util.HashMap;
import java.util.Map;

public class P2P_Template {

    Map<Integer, String> dictionary;

    public P2P_Template(){
        this.dictionary = new HashMap<>();
        initializeTagInfoMap();
    }

    private void initializeTagInfoMap() {
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

    public void parse(String input ) {
        P2P_Template template = new P2P_Template();
        boolean []tag_ids = new boolean[11];
        int [] mandatory_ids={0,1,2,4,10};
        int startIndex = 0;
        int endIndex =2;
        int length;
        int key;
        try{
            for(int i=0;endIndex<input.length();i++){
                key = Integer.parseInt(input.substring(startIndex,endIndex));
                System.out.print(template.dictionary.get(key)+": ");
                tag_ids[key]=true;
                startIndex=endIndex;
                endIndex+=2;
                length=Integer.parseInt( input.substring(startIndex,endIndex));
                startIndex=endIndex;
                System.out.println(input.substring(startIndex,endIndex+length));
                startIndex=endIndex+length;
                endIndex=startIndex+2;
            }
        }catch (StringIndexOutOfBoundsException e){

            System.out.println("\nQR code is corrupted or incomplete!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        boolean flag = true;
        for(int i=0;i<mandatory_ids.length;i++){
            if(!tag_ids[mandatory_ids[i]]){
                flag=false;
                System.out.println("\nQR code is corrupted or incomplete!");
                break;
            }
        }

    }

}
