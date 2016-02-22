/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Week3 {

    public static boolean isPalindrome(String word) {
        /**
         * Pri posudzovaní, či ide o rovnaký význam sa obvykle neberú do úvahy
         * medzery medzi slovami a diakritika (ak je použitá).
         * https://sk.wikipedia.org/wiki/Palindr%C3%B3m
         */
        word = word.replaceAll("\\W", ""); //ignore ./**+6+--*?<></
        word = word.toLowerCase();
        word = Util.removeSpaces(word);
        return Util.reverse(word).equals(word); // :) hehe
    }

    public static String sentenceOperation(String sentence, char operator) {
        String result = "";
        switch (operator) {
            case 'l':
            case 'L':
                //-l -L skonvertovať všetky písmena vo vete na male 
                return sentence.toLowerCase().trim();
            case 'u':
            case 'U':
//                -u -U skonvertovať všetky písmena vo vete na veľké 
                return sentence.toUpperCase().trim();
            case 'r':
            case 'R':
//                -r -R skonvertovať všetky písmena vo vete na opačnej veľkosti než boli v pôvodnej vete 
                for (char ch : sentence.toCharArray()) {
                    if (Character.isLowerCase(ch)) {
                        result += Character.toUpperCase(ch);
                    } else if (Character.isUpperCase(ch)) {
                        result += Character.toLowerCase(ch);
                    } else {
                        result += ch;
                    }
                }
                return result.trim();
            case 'c':
            case 'C':
                //-c -C skonvertovať všetky začiatočné písmená slov vo vete na veľké 
                // I split whole string at every \s into array then into arraylist
                // and now every word's first letter is at words[x][0]
                String[] split = sentence.trim().split("\\s");
                ArrayList<String> words = new ArrayList<>(Arrays.asList(split));
                for (String word : words) {
                    char capLetter = Character.toUpperCase(word.charAt(0));
                    result += " " + capLetter + word.substring(1);
                }
                return result.trim();
        }
        return "fail";
    }

    public static String baseConverter(int numberToConvert, int base1, int base2) {
        String number = numberToConvert + ""; //:) parseInt takes string as paramter.
        return Integer.toString(Integer.parseInt(number, base1), base2);
    }

    /**
     * Na vstupe je zadaný jednoduchý matematický výraz, napr. 12.45 * 45.8
     * Vyčíslite ho a výsledok vypíšte na konzolu.
     *
     */
    public static double simpleCalc(String expression) {
        expression = Util.removeSpaces(expression);
        String[] numbers = expression.split("[+\\*/-]");            //separate /*+- from digits
        String operator = expression.replaceAll("[^+\\*/-]", "");   // separate digits from /*+- :)
        double a = Double.parseDouble(numbers[0]);
        double b = Double.parseDouble(numbers[1]);
        switch (operator) {
            case "/":
                return a / b;
            case "*":
                return a * b;
            case "+":
                return a + b;
            case "-":
                return a - b;
        }

        return Integer.MIN_VALUE; //sometimes this calculator is correct.
    }

    public static double simpleCalc2(String expression) {
        //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return (double) engine.eval(expression);
        } catch (ScriptException ex) {
            Logger.getLogger(Week3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Vytvorte jednoduchú aplikáciu, podobnú príkazu grep. Vytvorte jednoduchú
     * aplikáciu, podobnú príkazu grep. Parametrom príkazu je vzorka vo forme
     * regulárneho výrazu a zoznam názvov súborov, na ktorých sa má aplikovať
     * hľadanie. Aplikácia vypíše všetky riadky, ktoré sa zhodujú so zadaným
     * regulárnym výrazom. Implementujte aj nasledujúce prepínače: -c - spočíta
     * počet riadkov zhodných so zadaným regulárnym výrazom a pre každý súbor
     * vypíše tento údaj -v - pri spracovaní budeme uvažovať riadky, ktoré sa
     * nezhodujú so zadaným regulárnym výrazom
     *
     * @param regex
     * @param option
     */
    public static void grep(String regex, char option) {

        ArrayList<String> sample = new ArrayList<>();
        sample.add("__init__");
        sample.add("hudba");
        sample.add("filmy");
        sample.add("start.exe");
        sample.add("gaga.mp3");
        sample.add("korben dallas.mp3");
        sample.add("billy barman.mp3");
        sample.add("Interstellar.mkv");
        sample.add("nusb3xhc    ");
        sample.add("nusb3hub.sys");
        sample.add("test movie.avi");
        sample.add("ruby on rails.pdf");
        sample.add("where the streets have no name.mp3");
        sample.add("jojojojoj");
        sample.add("95+52659+52++-");
        int count = 0;
        for (String s : sample) {
            if (option == 'v') {
                if (!s.matches(regex)) {
                    System.out.println(s.toString());
                    count++;
                }
            } else if (s.matches(regex)) {
                System.out.println(s.toString());
                count++;
            }
        }
        System.out.println("Regex matches :" + count + " expressiosn.");
    }
}
