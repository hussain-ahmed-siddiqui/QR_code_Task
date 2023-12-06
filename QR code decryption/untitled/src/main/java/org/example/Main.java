package org.example;

import java.io.PrintStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        P2P_Template template = new P2P_Template();
        String QR_code = "0002020102120202000424PK38SONE002422000942359205042500071225102023161010043071";
        boolean []tag_ids = new boolean[11];
        int [] mandatory_ids={0,1,2,4,10};
        int startIndex = 0;
        int endIndex =2;
        int length;
        int key;
        try{
        for(int i=0;endIndex<QR_code.length();i++){
            key = Integer.parseInt(QR_code.substring(startIndex,endIndex));
            System.out.print(template.dictionary.get(key)+": ");
            tag_ids[key]=true;
            startIndex=endIndex;
            endIndex+=2;
            length=Integer.parseInt( QR_code.substring(startIndex,endIndex));
            startIndex=endIndex;
            System.out.println(QR_code.substring(startIndex,endIndex+length));
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