package com.intertek.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Collection;

public class StringUtil {
    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);

        for (int i = 0; i < b.length; i++) {
            // look up high nibble char
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

            // look up low nibble char
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    // table to convert a nibble to a hex char.
    static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', 
                              '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String mingleStr(String input) {
        if (input == null)
            return null;

        return input.trim().replaceAll("\\s+", "_");
    }

    public static boolean emptyStr(String input) {
        if (input == null)
            return true;

        if ("".equals(input.trim()))
            return true;

        return false;
    }

    public static String forHTML(String aText) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("&lt;");
            }
            else if (character == '>') {
                result.append("&gt;");
            }
            else if (character == '&') {
                result.append("&amp;");
            }
            else if (character == '\"') {
                result.append("\\&quot;");
            }
            else if (character == '\'') {              
                result.append("\\&#039;");              
            }
            else if (character == '(') {
                result.append("&#040;");
            }
            else if (character == ')') {
                result.append("&#041;");
            }
            else if (character == '#') {
                result.append("&#035;");
            }
            else if (character == '%') {
                result.append("&#037;");
            }
            else if (character == ';') {
                result.append("&#059;");
            }
            else if (character == '+') {
                result.append("&#043;");
            }
            else if (character == '-') {
                result.append("&#045;");
            }
            else {
                // the char is not a special one
                // add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    public static String replaceSpecialChars(String sysFileName) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(sysFileName);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("lt;");
            }
            else if (character == '>') {
                result.append("gt;");
            }
            else if (character == '&') {
                result.append("amp;");
            }
            else if (character == '\"') {
                result.append("quot;");
            }
            else if (character == '#') {
                result.append("035;");
            }
            else if (character == '%') {
                result.append("037;");
            }
            else if (character == '\'') {
                result.append("039;");               
            }
            else if (character == '(') {
                result.append("040;");
            }
            else if (character == ')') {
                result.append("041;");
            }
            else if (character == '+') {
                result.append("043;");
            }
            else if (character == '-') {
                result.append("045;");
            }
            else if (character == '~') {
                result.append("046;");
            }
            else if (character == '!') {
                result.append("047;");
            }
            else if (character == '@') {
                result.append("048;");
            }
            else if (character == '^') {
                result.append("050;");
            }
            else if (character == '*') {
                result.append("051;");
            }
            else if (character == '?') {
                result.append("052;");
            }
            else if (character == '{') {
                result.append("053;");
            }
            else if (character == '=') {
                result.append("054;");
            }
            else if (character == '}') {
                result.append("055;");
            }
            else if (character == '[') {
                result.append("056;");
            }
            else if (character == ']') {
                result.append("057;");
            }
            else if (character == ',') {
                result.append("058;");
            }
            else if (character == ';') {
                result.append("059;");
            }
            else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    public static String replaceSpecialCodes(String fileName) {

        String[] multiSpecialCodes = { "amp;", "lt;", "gt;", "quot;", "035;", "037;", "039;", "040;", "041;", "043;", "045;", "046;", "047;", "048;", "049;",
                                      "050;", "051;", "052;", "053;", "054;", "055;", "056;", "057;", "058;", "059;" };
        for (int i = 0; i < multiSpecialCodes.length; i++) {
            if (fileName.indexOf(multiSpecialCodes[i]) != -1) {
                if (multiSpecialCodes[i].equals("amp;")) {
                    fileName = fileName.replaceAll("amp;", "&");
                }
                else if (multiSpecialCodes[i].equals("gt;")) {
                    fileName = fileName.replaceAll("gt;", ">");
                }
                else if (multiSpecialCodes[i].equals("quot;")) {
                    fileName = fileName.replaceAll("quot;", "\"");
                }
                else if (multiSpecialCodes[i].equals("lt;")) {
                    fileName = fileName.replaceAll("lt;", "<");
                }
                else if (multiSpecialCodes[i].equals("035;")) {
                    fileName = fileName.replaceAll("035;", "#");
                }
                else if (multiSpecialCodes[i].equals("037;")) {
                    fileName = fileName.replaceAll("037;", "%");
                }
                else if (multiSpecialCodes[i].equals("039;")) {
                    fileName = fileName.replaceAll("039;", "\'");
                }
                else if (multiSpecialCodes[i].equals("040;")) {
                    fileName = fileName.replaceAll("040;", "(");
                }
                else if (multiSpecialCodes[i].equals("041;")) {
                    fileName = fileName.replaceAll("041;", ")");
                }
                else if (multiSpecialCodes[i].equals("043;")) {
                    fileName = fileName.replaceAll("043;", "+");
                }
                else if (multiSpecialCodes[i].equals("045;")) {
                    fileName = fileName.replaceAll("045;", "-");
                }
                else if (multiSpecialCodes[i].equals("046;")) {
                    fileName = fileName.replaceAll("046;", "~");
                }
                else if (multiSpecialCodes[i].equals("047;")) {
                    fileName = fileName.replaceAll("047;", "!");
                }
                else if (multiSpecialCodes[i].equals("048;")) {
                    fileName = fileName.replaceAll("048;", "@");
                }
                else if (multiSpecialCodes[i].equals("050;")) {
                    fileName = fileName.replaceAll("050;", "^");
                }
                else if (multiSpecialCodes[i].equals("051;")) {
                    fileName = fileName.replaceAll("051;", "*");
                }
                else if (multiSpecialCodes[i].equals("052;")) {
                    fileName = fileName.replaceAll("052;", "?");
                }
                else if (multiSpecialCodes[i].equals("053;")) {
                    fileName = fileName.replaceAll("053;", "{");
                }
                else if (multiSpecialCodes[i].equals("054;")) {
                    fileName = fileName.replaceAll("054;", "=");
                }
                else if (multiSpecialCodes[i].equals("055;")) {
                    fileName = fileName.replaceAll("055;", "}");
                }
                else if (multiSpecialCodes[i].equals("056;")) {
                    fileName = fileName.replaceAll("056;", "[");
                }
                else if (multiSpecialCodes[i].equals("057;")) {
                    fileName = fileName.replaceAll("057;", "]");
                }
                else if (multiSpecialCodes[i].equals("058;")) {
                    fileName = fileName.replaceAll("058;", ",");
                }
                else if (multiSpecialCodes[i].equals("059;")) {
                    fileName = fileName.replaceAll("059;", ";");
                }
            }
        }
        return fileName;
    }
    
    public static String forSpecialCharacters(String aText) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("&lt;");
            }
            else if (character == '>') {
                result.append("&gt;");
            }
            else if (character == '&') {
                result.append("&amp;");
            }
            else if (character == '\"') {
                result.append("&quot;");
            }            
            else if (character == '(') {
                result.append("&#040;");
            }
            else if (character == ')') {
                result.append("&#041;");
            }
            else if (character == '#') {
                result.append("&#035;");
            }
            else if (character == '%') {
                result.append("&#037;");
            }
            else if (character == ';') {
                result.append("&#059;");
            }
            else if (character == '+') {
                result.append("&#043;");
            }
            else if (character == '-') {
                result.append("&#045;");
            }
            else {
                // the char is not a special one
                // add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    } 
}
