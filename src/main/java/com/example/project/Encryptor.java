package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
    int columns = 0;
    
    if (messageLen == 0){
        return 1;
    }
    if (messageLen % rows == 0){
        columns = messageLen / rows;
    }
    else{
        columns = (messageLen / rows) + 1;
    }

    return columns;
        
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
    String[][] newArr = new String[rows][determineColumns(message.length(), rows)];
        for (int i = 0; i < newArr.length; i++){
            for (int j = 0; j < newArr[0].length; j++){
                if (message.length() > 0){
                    String str = message.substring(0, 1);
                    newArr[i][j] = str;
                    message = message.substring(1);
                }
                else{
                    newArr[i][j] = "=";
                }
            }
        }
        return newArr;
    }

    public static String encryptMessage(String message, int rows){
        String[][] newArr = generateEncryptArray(message, rows);
        String encryptedStr = "";

        for (int j = newArr[0].length - 1; j >= 0; j--){
            for (int i = 0; i < newArr.length; i++){
                encryptedStr += newArr[i][j];
            }
        }

        return encryptedStr;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] newArr = new String[rows][determineColumns(encryptedMessage.length(), rows)];
        String decryptedStr = "";
        int idx = 0;

        for (int j = (determineColumns(encryptedMessage.length(), rows)) - 1; j >= 0; j--){
            for (int i = 0; i < rows; i++){
                if (idx < encryptedMessage.length()) {
                    newArr[i][j] = String.valueOf(encryptedMessage.charAt(idx));
                    idx++;
                }
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < determineColumns(encryptedMessage.length(), rows); j++){
                if (newArr[i][j] != null && !newArr[i][j].equals("=")) {
                    decryptedStr += newArr[i][j];
                }
            }
        }

        return decryptedStr;
        }
    }