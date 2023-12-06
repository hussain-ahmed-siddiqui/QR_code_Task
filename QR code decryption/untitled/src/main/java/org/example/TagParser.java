package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class TagParser {

    private Map<Integer, String> tagInfoMap;
    private Map<Integer, String> foundTags;
    public TagParser() {
        this.tagInfoMap = new HashMap<>();
        this.foundTags = new LinkedHashMap<>();
        initializeTagInfoMap();

    }

    private void initializeTagInfoMap() {
        // Map tag IDs to information types

        tagInfoMap.put(0, "Payload Format Indicator");
        tagInfoMap.put(1, "Point of Initiation");
        tagInfoMap.put(2, "Merchant Account Info for Visa");
        tagInfoMap.put(3, "Merchant Account Info for Visa"); // another tag for the same info
        tagInfoMap.put(4, "Merchant Account Info for Mastercard");
        tagInfoMap.put(5, "Merchant Account Info for Mastercard");
        tagInfoMap.put(15, "Merchant Account Info for UnionPay");
        tagInfoMap.put(16, "Merchant Account Info for UnionPay");
        tagInfoMap.put(28, "Merchant Account Info for Raast");
        tagInfoMap.put(52, "Merchant Category Code");
        tagInfoMap.put(53, "Transaction Currency");
        tagInfoMap.put(54, "Transaction Amount");
        tagInfoMap.put(55, "Tip or convenience fee indicator");
        tagInfoMap.put(56, "Value of Convenience fee");
        tagInfoMap.put(57, "Value of convenience fee (%)");
        tagInfoMap.put(58, "Country Code");
        tagInfoMap.put(59, "Merchant Name");
        tagInfoMap.put(60, "Merchant City");
        tagInfoMap.put(62, "Additional Data Field Template");
        tagInfoMap.put(64, "Merchant Information Language Template");
        tagInfoMap.put(80, "Context of transaction");
        tagInfoMap.put(81, "Discounts & Loyalty Programs");
        tagInfoMap.put(82, "O2O");
        tagInfoMap.put(83, "e-Commerce");
        tagInfoMap.put(84, "Scheme Specific");
        tagInfoMap.put(85, "Scheme Specific");
        tagInfoMap.put(86, "Scheme Specific");
        tagInfoMap.put(87, "Acquirer Specific");
        tagInfoMap.put(88, "Acquirer Specific");
        tagInfoMap.put(89, "RFU- SBP");
        tagInfoMap.put(90, "RFU- SBP");
        tagInfoMap.put(91, "RFU- SBP");
        tagInfoMap.put(92, "RFU- SBP");
        tagInfoMap.put(93, "RFU- SBP");
        tagInfoMap.put(94, "RFU- SBP");
        tagInfoMap.put(95, "RFU- SBP");
        tagInfoMap.put(96, "RFU- SBP");
        tagInfoMap.put(97, "RFU- SBP");
        tagInfoMap.put(98, "RFU- SBP");
        tagInfoMap.put(99, "RFU- SBP");
        tagInfoMap.put(63, "CRC");
    }

    private void evaluateSubTags(){
        /*For Raast*/
        String raast = foundTags.get(28);

        if(raast!=null){
            String [] subTags = new String[5];
        int s=0,e=2;
        int tagId, taglength;
        HashMap<Integer,Boolean> foundTagslocal = new HashMap<>();

        for(int i=0;e<raast.length();i++){
            tagId = Integer.parseInt(raast.substring(s,e));
            foundTagslocal.put(tagId, true);
            s=e;
            e+=2;
            taglength = Integer.parseInt(raast.substring(s,e));
            s=e;
            e=e+taglength;
            subTags[tagId]= raast.substring(s,e);
            s=e;
            e+=2;
        }
            raast=raast.concat("\nSub Tags:");
            for (Map.Entry<Integer,Boolean>keys:
                    foundTagslocal.entrySet()) {
                raast=raast.concat("\n"+subTags[keys.getKey()]);
            }

        foundTags.put(28,raast);
        }
        /*For Additional Data*/
            String additionalData = foundTags.get(62);
            if(additionalData!=null){
                String [] subTags = new String[100];
                int s=0,e=2;
                int tagId, tagLength;
                HashMap<Integer,Boolean> foundTagslocal = new HashMap<>();
                try{
                for (int i = 0; e<additionalData.length(); i++) {
                    tagId = Integer.parseInt(additionalData.substring(s, e));
                    foundTagslocal.put(tagId, true);
                    s = e;
                    e += 2;
                    tagLength = Integer.parseInt(additionalData.substring(s, e));
                    s = e;
                    e = e + tagLength;
                    subTags[tagId] = additionalData.substring(s, e);
                    s=e;
                    e+=2;
                }
                additionalData=additionalData.concat("\nSub Tags:");
                for (Map.Entry<Integer,Boolean>keys:
                     foundTagslocal.entrySet()) {
                    additionalData=additionalData.concat("\n"+subTags[keys.getKey()]);
                }
                foundTags.put(62,additionalData);
            }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }}



    }

    public void showInfo(){
        for (Map.Entry<Integer, String> keys :
             foundTags.entrySet()) {
            System.out.println(tagInfoMap.get(keys.getKey())+": "+foundTags.get(keys.getKey()));
        }
    }

    public void parseString(String input) {
        int i = 0;
        while (i < input.length()) {
            int tagId = Integer.parseInt(input.substring(i, i + 2));
            i += 2;
            int tagLength = Integer.parseInt(input.substring(i, i + 2));
            i += 2;
            String tagValue = input.substring(i, i + tagLength);
            i += tagLength;

            foundTags.put(tagId,tagValue);
        }
        evaluateSubTags();
    }



//    public static void main(String[] args) {
//        TagParser parser = new TagParser();
//        parser.parseString("00020101021202164000123456789012041653451234567890121516634512345678901227420010A0000007360124PK73FAYS35553900000000005204599953035865802PK5916AZIZGeneralStore6007KARACHI62890117234567854321234560211032400000000316AZIZGeneralStore05131238765432123071245678909876563045376"); // Example input
//        parser.showInfo();
//    }
}
