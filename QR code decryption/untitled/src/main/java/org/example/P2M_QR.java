package org.example;


public class P2M_QR {


    public static void main(String[] args) {
        System.out.println("*************P2P QR_code Decoding*************\n");
        P2P_Template p2pParser = new P2P_Template();
        p2pParser.parse("0002020102120202000424PK38SONE002422000942359205042500071225102023161010043071");

        System.out.println("\n*************P2M QR_code Decoding*************\n");
        TagParser p2mParser = new TagParser();
        p2mParser.parseString("00020101021202164000123456789012041653451234567890121516634512345678901227420010A0000007360124PK73FAYS35553900000000005204599953035865802PK5916AZIZGeneralStore6007KARACHI62890117234567854321234560211032400000000316AZIZGeneralStore05131238765432123071245678909876563045376"); // Example input
        p2mParser.showInfo();


    }
}
