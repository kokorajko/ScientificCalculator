/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Michu
 */
public class Values {

    //to jest wyświetlane działanie w kalkulatorze
    private String value = "";

    //a to jest działanie ze spacjami, żeby móc wyciągnąć liczby
    private String value2 = "";
    
    private String substringFromBrackets = "";

    public void setValue2(String s) {
        value2 = s;
    }

    public String getValue2() {
        return value2;
    }
    
    public void setSubstringFromBrackets(String s) {
        substringFromBrackets = s;
    }
    
    public String getSubstringFromBrackets() {
        return substringFromBrackets;
    }

    public void setValue(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    //metoda wyświetlająca cyfry w textField
    public void print(JButton b, JTextField t, Values o) {
        String valueSpace = o.getValue2() + b.getText();
        o.setValue2(valueSpace);
        String value2 = o.getValue() + b.getText();
        o.setValue(value2);
        t.setText(o.getValue());

        System.out.println(valueSpace);
    }

    //metoda usuwająca z textFielda (ze zmiennej value)
    public void delete(JTextField t, Values o) {
        int length = o.getValue().length();
        String parametr = o.getValue().substring(0, length - 1);
        o.setValue(parametr);
        t.setText(o.getValue());
    }

    //metoda usuwająca spację ze zmiennej value2
    public void deleteSpace(Values o) {
        boolean result;
        result = o.getValue2().endsWith(" ");
        if (result == true) {
            int length = o.getValue2().length();
            String nValue = o.getValue2().substring(0, length - 3);
            o.setValue2(nValue);
        } else {
            int length = o.getValue2().length();
            String nValue = o.getValue2().substring(0, length - 1);
            o.setValue2(nValue);
        }
        System.out.println(value2);
    }

    //metoda porównująca indeksy symboli działania
    public int compareIndexOfSymbol(int f, int s) {
        if (s > f) {
            return f;
        } else {
            return s;
        }
    }

    //metoda blokująca wprowadzanie nieprawidłowych działań (widoczne w tablicy symbolArray)
    public void symbolControl(Values o) {
        String[] symbolArray = {"..", "+.", "-.", "*.", "/.", "(.", ").", "**", ".*", "//", "./", "++", ".+", "--", ".-", ")(", ".(", ".)", "()", "+*", "+/"};
        for (String symbolArray1 : symbolArray) {
            boolean result;
            result = o.getValue().endsWith(symbolArray1);
            if (result == true) {
                int length = o.getValue().length();
                String nValue = o.getValue().substring(0, length - 1);
                o.setValue(nValue);
            }
        }
    }

    //metoda sprawdzająca, czy w działaniu jest nawias
    public boolean ifBracket() {
        // Declaring the search parameter
        CharSequence searchString = "(";
        // Test if value2 contains the searchString
        if (value2.contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy w działaniu jest mnożenie lub dzielenie
    public boolean ifMultiplyOrDivide(String s) {
        CharSequence searchString1 = "*";
        CharSequence searchString2 = "/";
        // Test if value2 contains the searchString
        if (s.contains(searchString1) || s.contains(searchString2)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy jest w wdziałaniu mnożenie
    public boolean ifMultiply(String s) {
        // Declaring the search parameter
        CharSequence searchString = "*";
        // Test if value2 contains the searchString
        if (s.contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy w działaniu jest dzielenie
    public boolean ifDivide(String s) {
        // Declaring the search parameter
        CharSequence searchString = "/";
        // Test if value2 contains the searchString
        if (s.contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy w działaniu jest odejmowanie
    public boolean ifMinus() {
        // Declaring the search parameter
        CharSequence searchString = "-";
        // Test if value2 contains the searchString
        if (value2.contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy w działaniu jest dodawanie
    public boolean ifPlus() {
        // Declaring the search parameter
        CharSequence searchString = "+";
        // Test if value2 contains the searchString
        if (value2.contains(searchString)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdzająca, czy w działaniu jest dodawanie lub odejmowanie
    public boolean ifPlusOrMinus() {
        CharSequence searchString1 = "+";
        CharSequence searchString2 = "-";
        // Test if value2 contains the searchString
        if (value2.contains(searchString1) || value2.contains(searchString2)) {
            return true;
        } else {
            return false;
        }
    }

    //metoda wyciągająca liczby z ostatniego nawiasu
    public void harvestNumbersFromLastBracket(ArrayList part, ArrayList whole, Values o) {
        int lastLBracket = value2.lastIndexOf('(');
        System.out.println("int lastbracket: " + lastLBracket);
        int postLastLRBracket = value2.indexOf(')', lastLBracket);
        System.out.println("int postlastbracket: " + postLastLRBracket);
        String substring = value2.substring(lastLBracket + 1, postLastLRBracket);
        o.setSubstringFromBrackets(substring);
        System.out.println("substring: " + substring);
        Scanner scanner = new Scanner(substring);
        // assign locale as US to recognize double numbers in a string
        scanner.useLocale(Locale.US);
        //clear ArrayList
        part.clear();
        while (scanner.hasNext()) {
            String token = scanner.next();
            // print what is scanned
            System.out.println(token);
            // add what is being scanned to ArrayList
            part.add(token);
        }
        // close the scanner
        scanner.close();
    }

    //metoda wyciągająca liczby i znaki działania z działania (value2) w przypadku braku obecności nawiasów
    public void harvestNumberWithoutBracket(ArrayList a, String s) {
        Scanner scanner = new Scanner(s);
        // assign locale as US to recognize double numbers in a string
        scanner.useLocale(Locale.US);
        //clear ArrayList
        a.clear();
        
        while (scanner.hasNext()) {
            String token = scanner.next();
            // print what is scanned
            System.out.println(token);
            // add what is being scanned to ArrayList
            a.add(token);
            System.out.println("liczba tokenów: " + a.size());
            
        }
        
        
        // close the scanner
        scanner.close();
    }

    public double add(double a, double b) {
        double result = (a + b);
        return result;
    }

    public double subtract(double a, double b) {
        double result = (a - b);
        return result;
    }

    public double multiply(double a, double b) {
        double result = (a * b);
        return result;
    }

    public double divide(double a, double b) {
        double result = (a / b);
        return result;
    }

    public void count(String symbol, ArrayList a) {
        int indexOfSymbol = a.indexOf(symbol);
        double first = Double.parseDouble((String) a.get(indexOfSymbol - 1));
        double second = Double.parseDouble((String) a.get(indexOfSymbol + 1));

        String sign = symbol;

        String addSymbol = "+";
        String subtractSymbol = "-";
        String multiplySymbol = "*";

        double result;
        if (sign.equals(addSymbol)) {
            result = add(first, second);
        } else if (sign.equals(subtractSymbol)) {
            result = subtract(first, second);
        } else if (sign.equals(multiplySymbol)) {
            result = multiply(first, second);
        } else {
            result = divide(first, second);
        }

        String total2 = String.valueOf(result);
        a.remove(indexOfSymbol + 1);
        a.remove(indexOfSymbol);
        a.remove(indexOfSymbol - 1);
        a.add(indexOfSymbol - 1, total2);
        System.out.println("wynik: " + result);
    }
    
    public String countPlus(ArrayList<String> a) {
        double first = Double.parseDouble(a.get(0));
        System.out.println("double first: " + first);
        double second = Double.parseDouble(a.get(1));
        System.out.println("double second: " + second);
        double result = add(first, second);
        System.out.println("double result: " + result);
        String total2 = String.valueOf(result);
        System.out.println("String result: " + total2);
        a.remove(1);
        a.remove(0);
        a.add(0, total2);
        System.out.println("wynik: " + result);
        String sResult = String.valueOf(result);
        System.out.println(sResult);
        return sResult;
        
    }
    
    public boolean checkDoubleMinuses(ArrayList<String> a, String s) {
        Scanner scanner = new Scanner(s);
        String minus = "-";
        boolean containsMinus = a.contains(minus);
        System.out.println("1. boolean containsMinus: " + containsMinus);
        boolean isNegative = false;
        if (containsMinus == true) {
            double second = Double.parseDouble((String) a.get(2));
            if (second < 0) {
                isNegative = true;
            }
        }
        if (isNegative == true) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkSingleMinus(ArrayList<String> a, String s) {
        Scanner scanner = new Scanner(s);
        String minus = "-";
        boolean containsMinus = a.contains(minus);
        System.out.println("1. boolean containsMinus: " + containsMinus);
        boolean isNegative = false;
        
            double second = Double.parseDouble((String) a.get(2));
            if (second < 0) {
                isNegative = true;
            }
        
        if (isNegative == true) {
            return false;
        } else {
            return true;
        }
    }
    
    public String countSingleMinus(Values o, ArrayList<String> a, String s) {
                o.deleteMinus(a, s);
        double first = Double.parseDouble(a.get(0));
        System.out.println("double first: " + first);
        double second = Double.parseDouble(a.get(1));
        System.out.println("double second: " + second);
        double result = subtract(first, second);
        System.out.println("double result: " + result);
        String total2 = String.valueOf(result);
        System.out.println("String result: " + total2);
        a.remove(1);
        a.remove(0);
        a.add(0, total2);
        System.out.println("wynik: " + result);
        String sResult = String.valueOf(result);
               
        return sResult;
        
    }
    
    public String countMinus(Values o, ArrayList<String> a, String s) {
                
        o.deleteMinus(a, s);
        double first = Double.parseDouble(a.get(0));
        System.out.println("double first: " + first);
        double second = Math.abs(Double.parseDouble(a.get(1)));
        System.out.println("double second: " + second);
        double result = add(first, second);
        System.out.println("double result: " + result);
        String total2 = String.valueOf(result);
        System.out.println("String result: " + total2);
        a.remove(1);
        a.remove(0);
        a.add(0, total2);
        System.out.println("wynik: " + result);
        String sResult = String.valueOf(result);
               
        return sResult;
    }
    
    public void deleteMinus(ArrayList<String> a, String s) {
        Scanner scanner = new Scanner(s);
        String minus = "-";
        boolean containsMinus = a.contains(minus);
        System.out.println("1. boolean containsMinus: " + containsMinus);
        while (containsMinus == true) {
        a.remove(minus);
        containsMinus = a.contains(minus);
        }
        System.out.println("2. boolean containsMinus: " + containsMinus);
        String[] e2Array = a.toArray(new String[a.size()]);
        System.out.println(Arrays.deepToString(e2Array));
    }
    
    public void deletePlus(ArrayList<String> a, String s) {
        Scanner scanner = new Scanner(s);
        String plus = "+";
        boolean containsPlus = a.contains(plus);
        System.out.println("1. boolean containsPlus: " + containsPlus);
        while (containsPlus == true) {
        a.remove(plus);
        containsPlus = a.contains(plus);
        }
        System.out.println("2. boolean containsPlus: " + containsPlus);
        String[] e2Array = a.toArray(new String[a.size()]);
        System.out.println(Arrays.deepToString(e2Array));
    }

    public void fromArrayListToValue2(ArrayList<String> a, Values o) {
        /*ArrayList to Array Conversion */
        String[] preValue2Array = a.toArray(new String[a.size()]);
        String comma = ",";
        String nothing = "";

        String preValue2 = Arrays.deepToString(preValue2Array);
        int length = preValue2.length();
        preValue2 = preValue2.substring(1, length - 1);
        preValue2 = preValue2.replaceAll(comma, nothing);
        o.setValue2(preValue2);
        System.out.println(o.getValue2());
    }
    
    public void fromArrayListToSubstringFromBrackets(ArrayList<String> a, Values o) {
        /*ArrayList to Array Conversion */
        String[] preValue2Array = a.toArray(new String[a.size()]);
        String comma = ",";
        String nothing = "";

        String preValue2 = Arrays.deepToString(preValue2Array);
        int length = preValue2.length();
        preValue2 = preValue2.substring(1, length - 1);
        preValue2 = preValue2.replaceAll(comma, nothing);
        o.setSubstringFromBrackets(preValue2);
        System.out.println(o.getValue2());
    }
    
    public void twoIntoOne(String insert, Values o) {
        String test = o.getValue2();
        boolean ifLastBracket = test.contains("(");
        boolean ifPostLastBracket = test.contains(")");
        if(ifLastBracket == true && ifPostLastBracket == true) {
            int lastLBracket = test.lastIndexOf('(');
        System.out.println("int lastbracket: " + lastLBracket);
        int postLastLRBracket = test.indexOf(')', lastLBracket);
        System.out.println("int postlastbracket: " + postLastLRBracket);
        String substring = test.substring(lastLBracket, postLastLRBracket + 1);
        System.out.println(substring);
        
        StringBuilder str = new StringBuilder(test);
        str.replace(lastLBracket, postLastLRBracket + 1, insert);
        o.setValue2(str.toString());
        System.out.println(str.toString());
        }
          
    }

}
