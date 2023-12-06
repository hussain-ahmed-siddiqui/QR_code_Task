package org.example;


public class Main {


    public static void main(String[] args) {
        System.out.println("*************P2P QR_code Decoding*************\n");
        P2P_Template p2pParser = new P2P_Template();
        p2pParser.parse("0002020102120202000424PK38SONE002422000942359205042500071225102023161010043071");

        System.out.println("\n*************P2M QR_code Decoding*************\n");
        TagParser p2mParser = new TagParser();
        p2mParser.parseString(
                "00020101021128760032a07f06c996f7422782191c733971e63e0108GPAYFAST0224PK93MPBL99431771401273395204893153035865802PK5913DEMO MERCHANT6007Karachi62940032DQR4B068L78N249N1DXD23RVABOJBU9Q0125PAYFASTQR-NKET5SMD61W96W00525PAYFASTQR-NKET5SMD61W96W089360032ee6bec8a2eef4920835d91c4abb95d7f63044C49"); // Example input
        p2mParser.showInfo();


    }
}
